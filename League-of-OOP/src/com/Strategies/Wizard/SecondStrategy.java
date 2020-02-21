package com.Strategies.Wizard;

import com.Heroes.Wizard;
import com.Strategies.Strategy;
import com.Utils.Constants;

public class SecondStrategy implements Strategy {
    private Wizard wizard;

    public SecondStrategy(final Wizard wizard) {
        this.wizard = wizard;
    }

    @Override
    public final void strategy() {
        wizard.setHp(Math.round(wizard.getHp() * (1 + Constants.Heroes.Wizard.HP_SECOND_STRATEGY)));
        wizard.setBonus(wizard.getBonus() + Constants.Heroes.Wizard.BONUS_SECOND_STRATEGY);
    }
}
