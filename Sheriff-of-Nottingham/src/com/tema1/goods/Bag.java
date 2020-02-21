package com.tema1.goods;

import java.util.List;
import java.util.ArrayList;

public class Bag {
    private List<Goods> assets;
    private int declaredType;
    private int bribery;

    public Bag() {
        assets = null;
        bribery = -1;
        declaredType = -1;
    }

    // constructor for BasePlayers' bag
    public Bag(final Goods asset, final int declaredType) {
        assets = new ArrayList<>();
        assets.add(asset);
        this.declaredType = declaredType;
        bribery = 0;
    }

    // constructor for BasePlayers' bag
    public Bag(final List<Goods> assets, final int declaredType) {
        this.assets = assets;
        bribery = 0;
        this.declaredType = declaredType;
    }

    // constructor for BribePlayer's bag
    public Bag(final List<Goods> assets, final int declaredType, final int bribery) {
        this.assets = assets;
        this.declaredType = declaredType;
        this.bribery = bribery;
    }

    public final List<Goods> getAssets() {
        return assets;
    }

    public final int getBribery() {
        return bribery;
    }

    public final int getDeclaredType() {
        return declaredType;
    }
}
