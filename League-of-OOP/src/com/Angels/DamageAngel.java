package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Rogue;
import com.Heroes.Pyromancer;
import com.Heroes.Wizard;
import com.Observer.LegalOperations;
import com.Utils.Constants;

public class DamageAngel extends AngelAbstract {
    public DamageAngel(final int row, final int column) {
        super(row, column);
    }

    @Override
    public final void help(final Knight knight) {
        if (knight.getHp() == 0) {
            return;
        }

        knight.setBonus(knight.getBonus() + Constants.Angels.DamageAngel.KNIGHT_BONUS);
        notify(knight, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Pyromancer pyromancer) {
        if (pyromancer.getHp() == 0) {
            return;
        }

        pyromancer.setBonus(pyromancer.getBonus() + Constants.Angels.DamageAngel.PYROMANCER_BONUS);
        notify(pyromancer, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Rogue rogue) {
        if (rogue.getHp() == 0) {
            return;
        }

        rogue.setBonus(rogue.getBonus() + Constants.Angels.DamageAngel.ROGUE_BONUS);
        notify(rogue, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Wizard wizard) {
        if (wizard.getHp() == 0) {
            return;
        }

        wizard.setBonus(wizard.getBonus() + Constants.Angels.DamageAngel.WIZARD_BONUS);
        notify(wizard, LegalOperations.helpFromAngel);
    }

    @Override
    public final String toString() {
        return "DamageAngel";
    }
}
