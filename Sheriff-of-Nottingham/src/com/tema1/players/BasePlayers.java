package com.tema1.players;

import com.tema1.common.Constants;
import com.tema1.goods.Bag;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.main.GameInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class BasePlayers extends Players {
    private Map<Integer, Integer> frequencyOfLegalAssets;
    private GoodsFactory goodsFactory = GoodsFactory.getInstance();

    public void createBag() {
        setFrequencyOfAssets();
        List<Goods> assetsList = getAssetsList();

        // the player has no legal cards
        if (frequencyOfLegalAssets.size() == 0) {
            int maxProfit = 0, id = -1;

            for (Goods good : assetsList) {
                if (good.getProfit() > maxProfit) {
                    maxProfit = good.getProfit();
                    id = good.getId();
                }
            }

            // can't pay the penalty so he hands over an empty bag
            if (getCoins() < Constants.ILLEGAL_PENALTY) {
                List<Goods> emptyList = new ArrayList<>();
                setBag(new Bag(emptyList, 0));
                return;
            }

            Goods good = goodsFactory.getGoodsById(id);
            assetsList.remove(good);
            setBag(new Bag(good, 0));
            return;
        }

        // count the maximum frequency of the hashmap
        int maxFrequency = 0;
        for (int id = 0; id < Constants.NO_LEGAL_GOODS; id++) {
            if (frequencyOfLegalAssets.get(id) != null
                    && maxFrequency < frequencyOfLegalAssets.get(id)) {
                maxFrequency = frequencyOfLegalAssets.get(id);
            }
        }

        // noMaxFrequency: check if it is the only maximum frequency
        // if it is the only one, idMaxFrequency saves the corresponding id
        int noMaxFrequency = 0, idMaxFrequency = 0;
        for (int id = 0; id < Constants.NO_LEGAL_GOODS; id++) {
            if (frequencyOfLegalAssets.get(id) != null
                    && maxFrequency == frequencyOfLegalAssets.get(id)) {
                noMaxFrequency++;
                idMaxFrequency = id;
            }
        }

        // if there is only one type of asset, we add all of them to the bag
        if (noMaxFrequency == 1) {
            if (maxFrequency > Constants.MAXIMUM_ASSETS_IN_BAG) {
                maxFrequency = Constants.MAXIMUM_ASSETS_IN_BAG;
            }
            Goods good = goodsFactory.getGoodsById(idMaxFrequency);

            List<Goods> bagAssets = new ArrayList<>();
            for (int i = 0; i < maxFrequency; i++) {
                bagAssets.add(good);
                assetsList.remove(good);
            }

            setBag(new Bag(bagAssets, idMaxFrequency));
            return;
        }

        // more types of assets have the same maximum frequency
        // i created a HashMap <id, profit>
        HashMap<Integer, Integer> profitOfAssets = new HashMap<>();
        for (int id = 0; id < Constants.NO_LEGAL_GOODS; id++) {
            if (frequencyOfLegalAssets.get(id) != null
                    && maxFrequency == frequencyOfLegalAssets.get(id)) {
                Goods good = goodsFactory.getGoodsById(id);
                profitOfAssets.put(id, good.getProfit());
            }
        }
        // calculate the maximum profit
        int maxProfit = 0;
        for (Map.Entry<Integer, Integer> mapElement : profitOfAssets.entrySet()) {
            Integer profit = mapElement.getValue();
            if (maxProfit < profit) {
                maxProfit = profit;
            }
        }

        // check how many types of assets have the same maximum profit
        int noMaxProfit = 0, idMaxProfit = -1;
        for (Map.Entry<Integer, Integer> mapElement : profitOfAssets.entrySet()) {
            Integer profit = mapElement.getValue();
            if (maxProfit == profit) {
                noMaxProfit++;
                idMaxProfit = mapElement.getKey();
            }
        }

        // only one type of asset has the maximum profit
        if (noMaxProfit == 1) {
            if (maxFrequency > Constants.MAXIMUM_ASSETS_IN_BAG) {
                maxFrequency = Constants.MAXIMUM_ASSETS_IN_BAG;
            }
            Goods good = goodsFactory.getGoodsById(idMaxProfit);

            List<Goods> bagAssets = new ArrayList<>();
            for (int i = 0; i < maxFrequency; i++) {
                bagAssets.add(good);
                assetsList.remove(good);
            }

            setBag(new Bag(bagAssets, idMaxProfit));
            return;
        }

        // more types of assets have the same profit
        // choose the one with the maximum id
        int idMax = -1;
        for (Map.Entry<Integer, Integer> mapElement : profitOfAssets.entrySet()) {
            Integer id = mapElement.getKey();
            Integer profit = mapElement.getValue();

            if (profit == maxProfit && idMax < id) {
                idMax = id;
            }
        }

        if (maxFrequency > Constants.MAXIMUM_ASSETS_IN_BAG) {
            maxFrequency = Constants.MAXIMUM_ASSETS_IN_BAG;
        }
        Goods good = goodsFactory.getGoodsById(idMaxProfit);
        List<Goods> bagAssets = new ArrayList<>();
        for (int i = 0; i < maxFrequency; i++) {
            assetsList.remove(good);
            bagAssets.add(good);
        }

        setBag(new Bag(bagAssets, idMaxProfit));
    }

    public void sheriff(final Players player, final GameInput gameInput) {
        int coins = getCoins();
        int coinsPlayer = player.getCoins();

        Bag bag = player.getBag();
        List<Goods> bagAssets = bag.getAssets();
        int declaredType = bag.getDeclaredType();
        int bribery = bag.getBribery();

        // the bribery goes back to the merchant
        coinsPlayer += bribery;
        player.setCoins(coinsPlayer);
        coinsPlayer = player.getCoins();

        if (getCoins() < Constants.BASE_PLAYER_MINIMUM_COINS) {
            return;
        }

        // 1st step: check if the merchant is a liar
        boolean isLiar = false;
        for (Goods good : bagAssets) {
            if (good.getId() != declaredType) {
                isLiar = true;
                break;
            }
        }

        // 2nd step: the merchant was truthful
        if (!isLiar) {
            for (Goods good : bagAssets) {
                coins -= good.getPenalty();
                coinsPlayer += good.getPenalty();
            }
        }
        // the merchant was a liar
        List<Integer> assetsIds = gameInput.getAssetIds();
        List<Goods> goodToBeDeleted = new ArrayList<>();
        if (isLiar) {
            for (Goods good : bagAssets) {
                if (good.getId() != declaredType) {
                    coins += good.getPenalty();
                    coinsPlayer -= good.getPenalty();
                    assetsIds.add(good.getId());
                    goodToBeDeleted.add(good);
                }
            }
        }

        for (Goods good : goodToBeDeleted) {
            bagAssets.remove(good);
        }

        setCoins(coins);
        player.setCoins(coinsPlayer);
    }

    @Override
    public void updateRound() {

    }

    // creates a hashmap that sets the frequency of the assets
    private void setFrequencyOfAssets() {
        List<Goods> assetsList = getAssetsList();
        frequencyOfLegalAssets = new HashMap<>();

        for (int id = 0; id < Constants.NO_LEGAL_GOODS; id++) {
            int frequency = 0;

            for (Goods good : assetsList) {
                if (good.getId() == id) {
                    frequency++;
                }
            }

            if (frequency > 0) {
                frequencyOfLegalAssets.put(id, frequency);
            }
        }
    }
}
