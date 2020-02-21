package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Pyromancer;
import com.Heroes.Rogue;
import com.Heroes.Wizard;
import com.Observer.LegalOperations;
import com.Utils.Constants;

public class DarkAngel extends AngelAbstract {
    public DarkAngel(final int row, final int column) {
        super(row, column);
    }

    @Override
    public final void help(final Knight knight) {
        if (knight.getHp() == 0) {
            return;
        }

        knight.setHp(knight.getHp() + Constants.Angels.DarkAngel.KNIGHT);
        notify(knight, LegalOperations.hitFromAngel);
    }

    @Override
    public final void help(final Pyromancer pyromancer) {
        if (pyromancer.getHp() == 0) {
            return;
        }

        pyromancer.setHp(pyromancer.getHp() + Constants.Angels.DarkAngel.PYROMANCER);
        notify(pyromancer, LegalOperations.hitFromAngel);
    }

    @Override
    public final void help(final Rogue rogue) {
        if (rogue.getHp() == 0) {
            return;
        }

        rogue.setHp(rogue.getHp() + Constants.Angels.DarkAngel.ROGUE);
        notify(rogue, LegalOperations.hitFromAngel);
    }

    @Override
    public final void help(final Wizard wizard) {
        if (wizard.getHp() == 0) {
            return;
        }

        wizard.setHp(wizard.getHp() + Constants.Angels.DarkAngel.WIZARD);
        notify(wizard, LegalOperations.hitFromAngel);
    }

    @Override
    public final String toString() {
        return "DarkAngel";
    }
}
