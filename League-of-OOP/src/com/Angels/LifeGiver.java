package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Pyromancer;
import com.Heroes.Rogue;
import com.Heroes.Wizard;
import com.Observer.LegalOperations;
import com.Utils.Constants;

public class LifeGiver extends AngelAbstract {
    public LifeGiver(final int row, final int column) {
        super(row, column);
    }

    @Override
    public final void help(final Knight knight) {
        if (knight.getHp() == 0) {
            return;
        }

        // safety check for not increasing the life above the maximum level
        if (knight.getHp() + Constants.Angels.LifeGiver.KNIGHT >= knight.getMaxHP()) {
            knight.setHp(knight.getMaxHP());
        }
        if (knight.getHp() != knight.getMaxHP()) {
            knight.setHp(knight.getHp() + Constants.Angels.LifeGiver.KNIGHT);
        }
        notify(knight, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Pyromancer pyromancer) {
        if (pyromancer.getHp() == 0) {
            return;
        }

        // safety check for not increasing the life above the maximum level
        if (pyromancer.getHp() + Constants.Angels.LifeGiver.PYROMANCER >= pyromancer.getMaxHP()) {
            pyromancer.setHp(pyromancer.getMaxHP());
        }
        if (pyromancer.getHp() != pyromancer.getMaxHP()) {
            pyromancer.setHp(pyromancer.getHp() + Constants.Angels.LifeGiver.PYROMANCER);
        }
        notify(pyromancer, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Rogue rogue) {
        if (rogue.getHp() == 0) {
            return;
        }

        // safety check for not increasing the life above the maximum level
        if (rogue.getHp() + Constants.Angels.LifeGiver.ROGUE >= rogue.getMaxHP()) {
            rogue.setHp(rogue.getMaxHP());
        }
        if (rogue.getMaxHP() != rogue.getHp()) {
            rogue.setHp(rogue.getHp() + Constants.Angels.LifeGiver.ROGUE);
        }
        notify(rogue, LegalOperations.helpFromAngel);
    }

    @Override
    public final void help(final Wizard wizard) {
        if (wizard.getHp() == 0) {
            return;
        }

        // safety check for not increasing the life above the maximum level
        if (wizard.getHp() + Constants.Angels.LifeGiver.WIZARD >= wizard.getMaxHP()) {
            wizard.setHp(wizard.getMaxHP());
        }
        if (wizard.getHp() != wizard.getMaxHP()) {
            wizard.setHp(wizard.getHp() + Constants.Angels.LifeGiver.WIZARD);
        }
        notify(wizard, LegalOperations.helpFromAngel);
    }

    @Override
    public final String toString() {
        return "LifeGiver";
    }
}
