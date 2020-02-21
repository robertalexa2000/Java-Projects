package com.Strategies.Pyromancer;

import com.Heroes.Pyromancer;
import com.Strategies.Strategy;
import com.Utils.Constants;

public class FirstStrategy implements Strategy {
    private Pyromancer pyromancer;

    public FirstStrategy(final Pyromancer pyromancer) {
        this.pyromancer = pyromancer;
    }

    @Override
    public final void strategy() {
        pyromancer.setBonus(pyromancer.getBonus()
                + Constants.Heroes.Pyromancer.BONUS_FIRST_STRATEGY);
        pyromancer.setHp(Math.round(pyromancer.getHp()
                * (1 + Constants.Heroes.Pyromancer.HP_FIRST_STRATEGY)));
    }
}
