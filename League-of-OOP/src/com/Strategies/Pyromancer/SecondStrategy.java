package com.Strategies.Pyromancer;

import com.Heroes.Pyromancer;
import com.Strategies.Strategy;
import com.Utils.Constants;

public class SecondStrategy implements Strategy {
    private Pyromancer pyromancer;

    public SecondStrategy(final Pyromancer pyromancer) {
        this.pyromancer = pyromancer;
    }

    @Override
    public final void strategy() {
        pyromancer.setBonus(pyromancer.getBonus()
                + Constants.Heroes.Pyromancer.BONUS_SECOND_STRATEGY);
        pyromancer.setHp(Math.round(pyromancer.getHp()
                * (1 + Constants.Heroes.Pyromancer.HP_SECOND_STRATEGY)));
    }
}
