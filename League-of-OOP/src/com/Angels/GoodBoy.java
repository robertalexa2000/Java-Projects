package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Pyromancer;
import com.Heroes.Rogue;
import com.Heroes.Wizard;
import com.Observer.LegalOperations;
import com.Utils.Constants;

public class GoodBoy extends AngelAbstract {
    public GoodBoy(final int row, final int column) {
        super(row, column);
    }

    @Override
    public final void help(final Knight knight) {
        if (knight.getHp() == 0) {
            return;
        }

        knight.setBonus(knight.getBonus() + Constants.Angels.GoodBoy.KNIGHT_BONUS);
        knight.setHp(knight.getHp() + Constants.Angels.GoodBoy.KNIGHT_HP);
        notify(knight, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Pyromancer pyromancer) {
        if (pyromancer.getHp() == 0) {
            return;
        }

        pyromancer.setBonus(pyromancer.getBonus() + Constants.Angels.GoodBoy.PYROMANCER_BONUS);
        pyromancer.setHp(pyromancer.getHp() + Constants.Angels.GoodBoy.PYROMANCER_HP);
        notify(pyromancer, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Rogue rogue) {
        if (rogue.getHp() == 0) {
            return;
        }

        rogue.setBonus(rogue.getBonus() + Constants.Angels.GoodBoy.ROGUE_BONUS);
        rogue.setHp(rogue.getHp() + Constants.Angels.GoodBoy.ROGUE_HP);
        notify(rogue, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Wizard wizard) {
        if (wizard.getHp() == 0) {
            return;
        }

        wizard.setBonus(wizard.getBonus() + Constants.Angels.GoodBoy.WIZARD_BONUS);
        wizard.setHp(wizard.getHp() + Constants.Angels.GoodBoy.WIZARD_HP);
        notify(wizard, LegalOperations.helpFromAngel);
    }

    @Override
    public final String toString() {
        return "GoodBoy";
    }
}
