package com.tema1.players;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.main.GameInput;
import com.tema1.goods.Bag;
import com.tema1.common.Constants;

import java.util.ArrayList;
import java.util.List;

public abstract class Players {
    private int coins;
    private List<Goods> assetsList, stand;
    private Bag bag;
    private GoodsFactory goodsFactory = GoodsFactory.getInstance();


    public Players() {
        coins = Constants.STARTING_COINS;
        stand = new ArrayList<>();
        assetsList = null;
        bag = null;
    }

    public final void drawAssets(final List<Integer> assetsIds) {
        assetsList = new ArrayList<>();

        for (Integer id : assetsIds) {
            if (assetsList.size() < Constants.ASSETS_IN_HAND) {
                Goods good = goodsFactory.getGoodsById(id);
                assetsList.add(good);
            } else {
                break;
            }
        }

        assetsIds.subList(0, Constants.ASSETS_IN_HAND).clear();
    }

    public abstract void createBag();
    public abstract void sheriff(Players player, GameInput gameInput);
    public abstract void updateRound();

    public final void updateStand() {
        stand.addAll(bag.getAssets());
    }

    public final void updateCoins() {
        for (Goods good : stand) {
            coins += good.getProfit();
        }
    }

    public final int getCoins() {
        return coins;
    }

    public final void setCoins(final int coins) {
        this.coins = coins;
    }

    public final List<Goods> getAssetsList() {
        return assetsList;
    }

    public final Bag getBag() {
        return bag;
    }

    public final void setBag(final Bag bag) {
        this.bag = bag;
    }

    public final List<Goods> getStand() {
        return stand;
    }
}
