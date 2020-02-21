package com.tema1.players;

import com.tema1.common.Constants;
import com.tema1.goods.Bag;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.main.GameInput;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Collections;

public class BribePlayers extends BasePlayers {
    private Map<Integer, Integer> frequencyIllegalGoods;
    private Map<Integer, Integer> profitLegalGoods;
    private GoodsFactory goodsFactory = GoodsFactory.getInstance();

    public final void createBag() {
        setIllegalMap();
        if (getCoins() < Constants.BRIBE_PLAYER_MINIMUM_COINS
                || frequencyIllegalGoods.size() == 0) {
            super.createBag();
            return;
        }
        /*
        a list sorted in descending order by profit.
        the list has only ids(Integers), not Goods objects
         */
        List<Integer> illegalSortedList = setIllegalSortedList();

        List<Goods> bagGoods = new ArrayList<>();
        int penalty = 0, noGoods = 0;
        for (Integer idx : illegalSortedList) {
            if (penalty + Constants.ILLEGAL_PENALTY >= getCoins()
                    || noGoods == Constants.MAXIMUM_ASSETS_IN_BAG) {
                break;
            }

            Goods goodToAdd = goodsFactory.getGoodsById(idx);
            bagGoods.add(goodToAdd);
            noGoods++;
            penalty += Constants.ILLEGAL_PENALTY;
        }

        int bribery = 0;
        int coins = getCoins();
        if (noGoods == Constants.MAXIMUM_ASSETS_IN_BAG) {
            setBag(new Bag(bagGoods, 0, Constants.HIGH_BRIBE));
            setCoins(coins - Constants.HIGH_BRIBE);
            return;
        } else if (noGoods == 1 || noGoods == 2) {
            bribery = Constants.LOW_BRIBE;
            setCoins(coins - Constants.LOW_BRIBE);
        } else if (noGoods > 2) {
            bribery = Constants.HIGH_BRIBE;
            setCoins(coins - Constants.HIGH_BRIBE);
        }

        Map<Integer, Integer> sortedLegalMap = setLegalMap();
        for (Map.Entry<Integer, Integer> entry : sortedLegalMap.entrySet()) {
            int id = entry.getKey();

            int frequency = 0;
            List<Goods> assetsList = getAssetsList();
            for (Goods good : assetsList) {
                if (good.getId() == id) {
                    frequency++;
                }
            }

            Goods goodToAdd = goodsFactory.getGoodsById(id);
            for (int i = 0; i < frequency; i++) {
                if (penalty + Constants.LEGAL_PENALTY >= getCoins()
                        || noGoods == Constants.MAXIMUM_ASSETS_IN_BAG) {
                    break;
                }

                bagGoods.add(goodToAdd);
                penalty += Constants.LEGAL_PENALTY;
                noGoods++;
            }
        }

        setBag(new Bag(bagGoods, 0, bribery));
    }

    public void sheriff(final Players player, final GameInput gameInput) {
        super.sheriff(player, gameInput);
    }

    private void setIllegalMap() {
        List<Goods> assets = getAssetsList();
        frequencyIllegalGoods = new HashMap<>();

        for (int id = Constants.LOWEST_ILLEGAL_GOOD; id < Constants.HIGHEST_ILLEGAL_GOOD; id++) {
            int frequency = 0;

            for (Goods good : assets) {
                if (good.getId() == id) {
                    frequency++;
                }
            }

            if (frequency > 0) {
                frequencyIllegalGoods.put(id, frequency);
            }
        }
    }

    private List<Integer> setIllegalSortedList() {
        List<Integer> illegalSortedList = new ArrayList<>();
        for (int i = Constants.LOWEST_ILLEGAL_GOOD; i < Constants.HIGHEST_ILLEGAL_GOOD; i++) {
            if (frequencyIllegalGoods.get(i) == null) {
                continue;
            }

            int frequency = frequencyIllegalGoods.get(i);
            if (i == Constants.HIGHEST_ILLEGAL_GOOD - 1) {
                for (int j = 0; j < frequency; j++) {
                    illegalSortedList.add(0, i);
                }

                continue;
            }

            for (int j = 0; j < frequency; j++) {
                illegalSortedList.add(i);
            }
        }

        return illegalSortedList;
    }

    private Map<Integer, Integer> setLegalMap() {
        List<Goods> assets = getAssetsList();
        profitLegalGoods = new LinkedHashMap<>();

        for (int id = Constants.HIGHEST_LEGAL_GOOD - 1; id >= 0; id--) {
            boolean exists = false;

            for (Goods good : assets) {
                if (good.getId() == id) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                Goods good = goodsFactory.getGoodsById(id);
                profitLegalGoods.put(id, good.getProfit());
            }
        }


        List<Integer> profits = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : profitLegalGoods.entrySet()) {
            profits.add(entry.getValue());
        }
        Collections.sort(profits, Collections.reverseOrder());

        Map<Integer, Integer> sortedLegalMap = new LinkedHashMap<>();
        for (Integer profit : profits) {
            for (Map.Entry<Integer, Integer> entry : profitLegalGoods.entrySet()) {
                if (entry.getValue().equals(profit)) {
                    sortedLegalMap.put(entry.getKey(), profit);
                }
            }
        }

        return sortedLegalMap;
    }
}
