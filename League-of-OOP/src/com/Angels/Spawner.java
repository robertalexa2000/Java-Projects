package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Pyromancer;
import com.Heroes.Rogue;
import com.Heroes.Wizard;
import com.Observer.LegalOperations;
import com.Utils.Constants;

public class Spawner extends AngelAbstract {
    public Spawner(final int row, final int column) {
        super(row, column);
    }

    @Override
    public final void help(final Knight knight) {
        if (knight.getHp() != 0) {
            return;
        }

        knight.setHp(Constants.Angels.Spawner.KNIGHT);
        notify(knight, LegalOperations.helpFromAngel);
        notify(knight, LegalOperations.bringToLife);
    }

    @Override
    public final void help(final Pyromancer pyromancer) {
        if (pyromancer.getHp() != 0) {
            return;
        }

        pyromancer.setHp(Constants.Angels.Spawner.PYROMANCER);
        notify(pyromancer, LegalOperations.helpFromAngel);
        notify(pyromancer, LegalOperations.bringToLife);
    }

    @Override
    public final void help(final Rogue rogue) {
        if (rogue.getHp() != 0) {
            return;
        }

        rogue.setHp(Constants.Angels.Spawner.ROGUE);
        notify(rogue, LegalOperations.helpFromAngel);
        notify(rogue, LegalOperations.bringToLife);
    }

    @Override
    public final void help(final Wizard wizard) {
        if (wizard.getHp() != 0) {
            return;
        }

        wizard.setHp(Constants.Angels.Spawner.WIZARD);
        notify(wizard, LegalOperations.helpFromAngel);
        notify(wizard, LegalOperations.bringToLife);
    }

    @Override
    public final String toString() {
        return "Spawner";
    }
}
