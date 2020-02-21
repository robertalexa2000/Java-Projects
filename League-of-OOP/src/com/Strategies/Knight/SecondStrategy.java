package com.Strategies.Knight;

import com.Heroes.Knight;
import com.Strategies.Strategy;
import com.Utils.Constants;

public class SecondStrategy implements Strategy {
    private Knight knight;

    public SecondStrategy(final Knight knight) {
        this.knight = knight;
    }

    @Override
    public final void strategy() {
        knight.setBonus(knight.getBonus() + Constants.Heroes.Knight.BONUS_SECOND_STRATEGY);
        knight.setHp(Math.round(
                knight.getHp() * (1 + Constants.Heroes.Knight.HP_SECOND_STRATEGY))
        );
    }
}
