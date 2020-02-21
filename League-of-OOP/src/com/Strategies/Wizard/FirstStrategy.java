package com.Strategies.Wizard;

import com.Heroes.Wizard;
import com.Strategies.Strategy;
import com.Utils.Constants;

public class FirstStrategy implements Strategy {
    private Wizard wizard;

    public FirstStrategy(final Wizard wizard) {
        this.wizard = wizard;
    }

    @Override
    public final void strategy() {
        wizard.setHp(Math.round(wizard.getHp() * (1 + Constants.Heroes.Wizard.HP_FIRST_STRATEGY)));
        wizard.setBonus(wizard.getBonus() + Constants.Heroes.Wizard.BONUS_FIRST_STRATEGY);
    }
}
