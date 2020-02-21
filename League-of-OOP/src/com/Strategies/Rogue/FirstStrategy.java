package com.Strategies.Rogue;

import com.Heroes.Rogue;
import com.Strategies.Strategy;
import com.Utils.Constants;

public class FirstStrategy implements Strategy {
    private Rogue rogue;

    public FirstStrategy(final Rogue rogue) {
        this.rogue = rogue;
    }

    @Override
    public final void strategy() {
        rogue.setBonus(rogue.getBonus() + Constants.Heroes.Rogue.BONUS_FIRST_STRATEGY);
        rogue.setHp(Math.round(rogue.getHp() * (1 + Constants.Heroes.Rogue.HP_FIRST_STRATEGY)));
    }
}
