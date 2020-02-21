package com.Strategies.Rogue;

import com.Heroes.Rogue;
import com.Strategies.Strategy;
import com.Utils.Constants;

public class SecondStrategy implements Strategy {
    private Rogue rogue;

    public SecondStrategy(final Rogue rogue) {
        this.rogue = rogue;
    }

    @Override
    public final void strategy() {
        rogue.setBonus(rogue.getBonus() + Constants.Heroes.Rogue.BONUS_SECOND_STRATEGY);
        rogue.setHp(Math.round(rogue.getHp() + Constants.Heroes.Rogue.HP_SECOND_STRATEGY));
    }
}
