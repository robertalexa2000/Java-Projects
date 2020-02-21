package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Rogue;
import com.Heroes.Wizard;
import com.Heroes.Pyromancer;
import com.Observer.LegalOperations;
import com.Utils.Constants;

public class XPAngel extends AngelAbstract {
    public XPAngel(final int row, final int column) {
        super(row, column);
    }

    @Override
    public final void help(final Knight knight) {
        if (knight.getHp() == 0) {
            return;
        }

        notify(knight, LegalOperations.helpFromAngel);
        knight.setXp(knight.getXp() + Constants.Angels.XPAngel.KNIGHT);
        knight.levelUp(null);
    }

    @Override
    public final void help(final Pyromancer pyromancer) {
        if (pyromancer.getHp() == 0) {
            return;
        }

        notify(pyromancer, LegalOperations.helpFromAngel);
        pyromancer.setXp(pyromancer.getXp() + Constants.Angels.XPAngel.PYROMANCER);
        pyromancer.levelUp(null);
    }

    @Override
    public final void help(final Rogue rogue) {
        if (rogue.getHp() == 0) {
            return;
        }

        notify(rogue, LegalOperations.helpFromAngel);
        rogue.setXp(rogue.getXp() + Constants.Angels.XPAngel.ROGUE);
        rogue.levelUp(null);
    }

    @Override
    public final void help(final Wizard wizard) {
        if (wizard.getHp() == 0) {
            return;
        }

        notify(wizard, LegalOperations.helpFromAngel);
        wizard.setXp(wizard.getXp() + Constants.Angels.XPAngel.WIZARD);
        wizard.levelUp(null);
    }

    @Override
    public final String toString() {
        return "XPAngel";
    }
}
