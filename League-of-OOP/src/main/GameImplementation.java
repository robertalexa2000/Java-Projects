package main;

import com.Angels.Angel;
import com.Factory.AngelFactory;
import com.Factory.HeroFactory;
import com.Heroes.Hero;
import com.Map.MapCell;
import com.Strategies.StrategyNotFoundException;
import com.Visitor.Visitor;
import com.Visitor.VisitorDamage;
import com.Visitor.VisitorFirstAbility;
import com.Visitor.VisitorSecondAbility;

import java.util.ArrayList;
import java.util.List;

public class GameImplementation {
    private GameInput gameInput;
    private List<Hero> heroes;
    private Output outputHelper;

    public GameImplementation(final GameInput gameInput, final Output outputHelper) {
        this.gameInput = gameInput;
        this.outputHelper = outputHelper;
    }

    final void implementGame() {
        createHeroes();
        for (int i = 0; i < gameInput.getNoRounds(); i++) {
            outputHelper.updateRound(i + 1);
            clearMap();
            moveHeroes(i);
            setMap();
            fight();
            spawnAngels(i);
        }

        outputHelper.displayResults(gameInput, heroes);
    }

    private void createHeroes() {
        char[] players = gameInput.getPlayers();
        int[][] positions = gameInput.getPositions();
        heroes = new ArrayList<>();

        int index = 0;
        HeroFactory heroFactory = HeroFactory.getInstance();
        for (int i = 0; i < players.length; i++) {
            heroes.add(heroFactory.getHero(players[i], positions[i][0], positions[i][1], index));
            index++;
        }
    }

    private void clearMap() {
        MapCell[][] mapCells = gameInput.getMap().getCells();
        for (int i = 0; i < mapCells.length; i++) {
            for (int j = 0; j < mapCells[i].length; j++) {
                mapCells[i][j].setHeroesList(new ArrayList<>());
            }
        }
    }

    private void moveHeroes(final int i) {
        String movement = gameInput.getMovements()[i];
        for (int j = 0; j < movement.length(); j++) {
            if (heroes.get(j).getOvertimeSlam() >= 1) {
                heroes.get(j).setOvertimeSlam(heroes.get(j).getOvertimeSlam() - 1);
                heroes.get(j).applyOvertime();
                continue;
            }
            heroes.get(j).applyOvertime();

            try {
                heroes.get(j).chooseStrategy();
                heroes.get(j).applyStrategy();
            } catch (StrategyNotFoundException e) {
            }

            switch (movement.charAt(j)) {
                case 'U':
                    heroes.get(j).move(heroes.get(j).getRow() - 1, heroes.get(j).getColumn());
                    break;
                case 'D':
                    heroes.get(j).move(heroes.get(j).getRow() + 1, heroes.get(j).getColumn());
                    break;
                case 'L':
                    heroes.get(j).move(heroes.get(j).getRow(), heroes.get(j).getColumn() - 1);
                    break;
                case 'R':
                    heroes.get(j).move(heroes.get(j).getRow(), heroes.get(j).getColumn() + 1);
                    break;
                default:
                    break;
            }
        }
    }

    private void setMap() {
        MapCell[][] mapCells = gameInput.getMap().getCells();
        for (int i = 0; i < gameInput.getNoPlayers(); i++) {
            int row = heroes.get(i).getRow();
            int column = heroes.get(i).getColumn();
            mapCells[row][column].getHeroesList().add(heroes.get(i));
        }
    }

    private void fight() {
        MapCell[][] mapCells = gameInput.getMap().getCells();
        for (int i = 0; i < mapCells.length; i++) {
            for (int j = 0; j < mapCells[i].length; j++) {
                if (mapCells[i][j].getHeroesList().size() < 2) {
                    continue;
                }

                // list of heroes in a node on the map
                List<Hero> heroesCell = mapCells[i][j].getHeroesList();
                Hero aux1 = null, aux2 = null;
                for (Hero h : heroesCell) {
                    if (h.getHp() > 0) {
                        if (aux1 == null) {
                            aux1 = h;
                        } else {
                            aux2 = h;
                        }
                    }
                }

                if (aux2 == null) {
                    continue;
                }


                int index1 = heroes.indexOf(aux1);
                int index2 = heroes.indexOf(aux2);

                // the list of heroes
                Hero h1 = heroes.get(index1);
                Hero h2 = heroes.get(index2);

                if (h1.getHp() == 0 || h2.getHp() == 0) {
                    continue;
                }


                Visitor v = new VisitorFirstAbility();
                int firstBaseDamage1 = h1.accept(v, h2, mapCells[i][j].getLandType(),
                        0, 0, 0, 0);
                int secondBaseDamage1 = h2.accept(v, h1, mapCells[i][j].getLandType(),
                        0, 0, 0, 0);

                v = new VisitorSecondAbility();
                int firstBaseDamage2 = h1.accept(v, h2, mapCells[i][j].getLandType(),
                        0, 0, 0, 0);
                int secondBaseDamage2 = h2.accept(v, h2, mapCells[i][j].getLandType(),
                        0, 0, 0, 0);

                v = new VisitorDamage();
                h1.accept(v, h2, mapCells[i][j].getLandType(), firstBaseDamage1,
                        firstBaseDamage2, secondBaseDamage1, secondBaseDamage2);
                h2.accept(v, h1, mapCells[i][j].getLandType(), secondBaseDamage1,
                        secondBaseDamage2, firstBaseDamage1, firstBaseDamage2);

                // they both die
                if (h1.getHp() <= 0 && h2.getHp() <= 0) {
                    h2.killHero(h1);
                    h1.killHero(h2);
                    continue;
                }

                // only h1 dies
                if (h1.getHp() <= 0) {
                    h1.killHero(h2);
                    h2.levelUp(h1);
                }

                // only h2 dies
                if (h2.getHp() <= 0) {
                    h2.killHero(h1);
                    h1.levelUp(h2);
                }
            }
        }
    }

    private void spawnAngels(final int round) {
        List<String> angelsToSpawn = gameInput.getAngelStrings().get(round);
        if (angelsToSpawn.size() == 0) {
            return;
        }

        for (String angelDescription : angelsToSpawn) {
            String[] angelStrings = angelDescription.split(",");
            String angelType = angelStrings[0];
            int row = Integer.parseInt(angelStrings[1]);
            int column = Integer.parseInt(angelStrings[2]);

            AngelFactory angelFactory = AngelFactory.getInstance();
            Angel angel = angelFactory.getAngel(angelType, row, column);

            List<Hero> localHeroes = gameInput.getMap().
                    getCells()[angel.getRow()][angel.getColumn()].getHeroesList();
            for (Hero hero : localHeroes) {
                hero.acceptAngel(angel);
            }
        }
    }
}
