package com.tema1.players;

import com.tema1.common.Constants;
import com.tema1.goods.Bag;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsType;
import com.tema1.main.GameInput;

import java.util.List;

public class GreedyPlayers extends BasePlayers {
    private int noRound;

    public GreedyPlayers() {
        noRound = 1;
    }

    public final void createBag() {
        super.createBag();
        if (noRound % 2 == 1) {
            return;
        }

        Bag bag = getBag();
        List<Goods> bagAssets = bag.getAssets();
        if (bagAssets.size() == Constants.MAXIMUM_ASSETS_IN_BAG) {
            return;
        }

        int noIllegalGoods = 0;
        for (Goods good : getAssetsList()) {
            if (good.getType() == GoodsType.Illegal) {
                noIllegalGoods++;
            }
        }
        if (noIllegalGoods == 0) {
            return;
        }

        List<Goods> assets = getAssetsList();
        Goods illegalGood = assets.get(0);
        for (Goods good : assets) {
            if (good.getType() == GoodsType.Illegal && good.getProfit() > illegalGood.getProfit()) {
                illegalGood = good;
            }
        }
        bagAssets.add(illegalGood);
        assets.remove(illegalGood);
    }

    public final void sheriff(final Players player, final GameInput gameInput) {
        Bag bag = player.getBag();
        int coins = getCoins();

        if (bag.getBribery() > 0) {
            coins += bag.getBribery();
            setCoins(coins);
        } else {
            super.sheriff(player, gameInput);
        }
    }

    public final void updateRound() {
        setNoRound();
    }

    public final void setNoRound() {
        noRound++;
    }
}
