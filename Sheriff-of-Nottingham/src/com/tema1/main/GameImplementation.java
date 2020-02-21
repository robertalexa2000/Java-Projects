package com.tema1.main;

import com.tema1.common.Constants;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.goods.GoodsType;
import com.tema1.goods.LegalGoods;
import com.tema1.goods.IllegalGoods;
import com.tema1.players.BasePlayers;
import com.tema1.players.BribePlayers;
import com.tema1.players.GreedyPlayers;
import com.tema1.players.Players;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Arrays;

final class GameImplementation {

    private static class PlayerImplementation {
        private int id;
        private String strategy;
        private Players player;

        PlayerImplementation(final int id, final String strategy, final Players player) {
            this.id = id;
            this.strategy = strategy;
            this.player = player;
        }
    }

    private GameInput gameInput;
    private int noRounds, noPlayers;
    private List<String> playersOrder;
    private PlayerImplementation[] players;
    private GoodsFactory goodsFactory = GoodsFactory.getInstance();

    GameImplementation(final GameInput gameInput) {
        this.gameInput = gameInput;

        noRounds = gameInput.getRounds();
        playersOrder = gameInput.getPlayerNames();
        noPlayers = playersOrder.size();

        // the game ends after each player has been a sheriff for 5 times at most
        if (noRounds > Constants.MAX_ROUNDS) {
            noRounds = Constants.MAX_ROUNDS;
        }
    }

    public void implementGame() {
        for (int round = 0; round < noRounds; round++) {
            for (int subround = 0; subround < noPlayers; subround++) {

                for (int id = 0; id < noPlayers; id++) {

                    if (id == subround) {
                        continue;
                    }

                    players[id].player.drawAssets(gameInput.getAssetIds());
                    players[id].player.createBag();
                    if (!players[subround].strategy.equals("BRIBED")) {
                        players[subround].player.sheriff(players[id].player, gameInput);
                        players[id].player.updateStand();
                    }
                }

                // corner cases for bribed player
                if (players[subround].strategy.equals("BRIBED")) {
                    if (noPlayers == 2 && subround == 0) {
                        players[subround].player.sheriff(players[subround + 1].player, gameInput);
                        players[subround + 1].player.updateStand();
                    }

                    if (noPlayers == 2 && subround == 1) {
                        players[subround].player.sheriff(players[subround - 1].player, gameInput);
                        players[subround - 1].player.updateStand();
                    }

                    if (noPlayers > 2) {
                        players[subround].player.sheriff(players[((subround - 1) % noPlayers
                                + noPlayers) % noPlayers].player, gameInput);
                        players[subround].player.sheriff(players[((subround + 1) % noPlayers
                                + noPlayers) % noPlayers].player, gameInput);
                        players[((subround - 1) % noPlayers + noPlayers)
                                % noPlayers].player.updateStand();
                        players[((subround + 1) % noPlayers + noPlayers)
                                % noPlayers].player.updateStand();
                    }
                }
            }

            // updating the noRound field for greedy players
            for (int i = 0; i < noPlayers; i++) {
                players[i].player.updateRound();
            }
        }
        // obtaining the illegal bonus
        illegalBonus();

        // obtaining the king and queen bonus
        Map<Integer, Players> kingMap = kingMap();
        kingBonus(kingMap);
        Map<Integer, Players> queenMap = queenMap(kingMap);
        queenBonus(queenMap);

        // last step: selling the goods and updating the number of coins
        for (int i = 0; i < noPlayers; i++) {
            players[i].player.updateCoins();
        }
    }

    public void createPlayers() {
        players = new PlayerImplementation[noPlayers];
        for (int i = 0; i < noPlayers; i++) {
            switch (playersOrder.get(i)) {
                case "basic":
                    players[i] = new PlayerImplementation(i, "BASIC", new BasePlayers());
                    break;
                case "greedy":
                    players[i] = new PlayerImplementation(i, "GREEDY", new GreedyPlayers());
                    break;
                case "bribed":
                    players[i] = new PlayerImplementation(i, "BRIBED", new BribePlayers());
                    break;
                default:
                    break;
            }
        }
    }

    private void illegalBonus() {
        for (int id = 0; id < noPlayers; id++) {
            List<Goods> stand = players[id].player.getStand();
            List<Goods> toAdd = new ArrayList<>();

            for (Goods good : stand) {
                if (good.getType() == GoodsType.Illegal) {
                    IllegalGoods illegalGoods = (IllegalGoods) good;
                    Map<Goods, Integer> illegalBonus = illegalGoods.getIllegalBonus();

                    for (Map.Entry<Goods, Integer> entry : illegalBonus.entrySet()) {
                        Goods key = entry.getKey();
                        int quantity = entry.getValue();

                        for (int i = 0; i < quantity; i++) {
                            toAdd.add(key);
                        }
                    }
                }
            }

            stand.addAll(toAdd);
        }
    }

    private void kingBonus(final Map<Integer, Players> kingMap) {
        for (Map.Entry<Integer, Players> entry : kingMap.entrySet()) {
            Integer key = entry.getKey();
            Players value = entry.getValue();

            LegalGoods legalGoods = (LegalGoods) goodsFactory.getGoodsById(key);
            int coins = value.getCoins();
            coins += legalGoods.getKingBonus();
            value.setCoins(coins);
        }
    }

    private Map<Integer, Players> kingMap() {
        Map<Integer, Players> kingMap = new HashMap<>();

        for (int id = 0; id < Constants.NO_LEGAL_GOODS; id++) {
            int king = 0, idKing = -1;
            // king: used for counting the maximum amount of goods of a specifid id
            // idking: used for saving the player's id

            for (int i = 0; i < noPlayers; i++) {
                int noGoods = 0;

                List<Goods> stand = players[i].player.getStand();
                for (Goods good : stand) {
                    if (good.getId() == id) {
                        noGoods++;
                    }
                }

                if (king < noGoods) {
                    king = noGoods;
                    idKing = i;
                }
            }

            if (idKing == -1) {
                continue;
            }

            kingMap.put(id, players[idKing].player);
        }

        return kingMap;
    }

    private void queenBonus(final Map<Integer, Players> queenMap) {
        for (Map.Entry<Integer, Players> entry : queenMap.entrySet()) {
            Integer key = entry.getKey();
            Players value = entry.getValue();

            LegalGoods legalGoods = (LegalGoods) goodsFactory.getGoodsById(key);
            int coins = value.getCoins();
            coins += legalGoods.getQueenBonus();
            value.setCoins(coins);
        }
    }

    private Map<Integer, Players> queenMap(final Map<Integer, Players> kingMap) {
        Map<Integer, Players> queenMap = new HashMap<>();

        for (Map.Entry<Integer, Players> entry : kingMap.entrySet()) {
            Integer idGood = entry.getKey();
            Players playerKing = entry.getValue();
            int goodCount = 0;

            List<Goods> playerKingStand = playerKing.getStand();
            for (Goods good : playerKingStand) {
                if (good.getId() == idGood) {
                    goodCount++;
                }
            }

            int queen = 0, idQueen = -1;
            for (int i = 0; i < noPlayers; i++) {
                if (players[i].player == playerKing) {
                    continue;
                }

                int noGood = 0;
                List<Goods> stand = players[i].player.getStand();
                for (Goods good : stand) {
                    if (good.getId() == idGood) {
                        noGood++;
                    }
                }

                if (queen < noGood) {
                    queen = noGood;
                    idQueen = i;
                }
            }

            if (idQueen != -1) {
                queenMap.put(idGood, players[idQueen].player);
            }
        }

        return queenMap;
    }

    public void showRanking() {
        Arrays.sort(players, new Comparator<>() {
            public int compare(final PlayerImplementation o1, final PlayerImplementation o2) {
                if (o1.player.getCoins() == o2.player.getCoins()) {
                    return o1.id - o2.id;
                }
                return o2.player.getCoins() - o1.player.getCoins();
            }
        });
        // printing the array of players
        for (int i = 0; i < noPlayers; i++) {
            System.out.println(players[i].id + " "
                    + players[i].strategy + " " + players[i].player.getCoins());
        }
    }
}
