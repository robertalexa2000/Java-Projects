package com.Strategies.Knight;

import com.Heroes.Knight;
import com.Strategies.Strategy;
import com.Utils.Constants;

public class FirstStrategy implements Strategy {
    private Knight knight;

    public FirstStrategy(final Knight knight) {
        this.knight = knight;
    }

    @Override
    public final void strategy() {
        knight.setHp(Math.round(
                knight.getHp() * (1 + Constants.Heroes.Knight.HP_FIRST_STRATEGY))
        );
        knight.setBonus(knight.getBonus() + Constants.Heroes.Knight.BONUS_FIRST_STRATEGY);
    }
}
