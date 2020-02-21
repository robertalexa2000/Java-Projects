package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Rogue;
import com.Heroes.Pyromancer;
import com.Heroes.Wizard;
import com.Observer.LegalOperations;
import com.Utils.Constants;


public class SmallAngel extends AngelAbstract {
    public SmallAngel(final int row, final int column) {
        super(row, column);
    }

    @Override
    public final void help(final Knight knight) {
        if (knight.getHp() == 0) {
            return;
        }

        knight.setBonus(knight.getBonus() + Constants.Angels.SmallAngel.KNIGHT_BONUS);
        knight.setHp(knight.getHp() + Constants.Angels.SmallAngel.KNIGHT_HP);
        notify(knight, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Pyromancer pyromancer) {
        if (pyromancer.getHp() == 0) {
            return;
        }

        pyromancer.setBonus(pyromancer.getBonus() + Constants.Angels.SmallAngel.PYROMANCER_BONUS);
        pyromancer.setHp(pyromancer.getHp() + Constants.Angels.SmallAngel.PYROMANCER_HP);
        notify(pyromancer, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Rogue rogue) {
        if (rogue.getHp() == 0) {
            return;
        }

        rogue.setBonus(rogue.getBonus() + Constants.Angels.SmallAngel.ROGUE_BONUS);
        rogue.setHp(rogue.getHp() + Constants.Angels.SmallAngel.ROGUE_HP);
        notify(rogue, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Wizard wizard) {
        if (wizard.getHp() == 0) {
            return;
        }

        wizard.setBonus(wizard.getBonus() + Constants.Angels.SmallAngel.WIZARD_BONUS);
        wizard.setHp(wizard.getHp() + Constants.Angels.SmallAngel.WIZARD_HP);
        notify(wizard, LegalOperations.helpFromAngel);
    }

    @Override
    public final String toString() {
        return "SmallAngel";
    }
}
