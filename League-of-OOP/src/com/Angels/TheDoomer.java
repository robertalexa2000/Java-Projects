package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Rogue;
import com.Heroes.Wizard;
import com.Heroes.Pyromancer;
import com.Observer.LegalOperations;

public class TheDoomer extends AngelAbstract {
    public TheDoomer(final int row, final int column) {
        super(row, column);
    }

    @Override
    public final void help(final Knight knight) {
        if (knight.getHp() == 0) {
            return;
        }

        knight.killHero(null);
        notify(knight, LegalOperations.hitFromAngel);
        notify(knight, LegalOperations.killedHero);
    }

    @Override
    public final void help(final Pyromancer pyromancer) {
        if (pyromancer.getHp() == 0) {
            return;
        }

        pyromancer.killHero(null);
        notify(pyromancer, LegalOperations.hitFromAngel);
        notify(pyromancer, LegalOperations.killedHero);
    }

    @Override
    public final void help(final Rogue rogue) {
        if (rogue.getHp() == 0) {
            return;
        }

        rogue.killHero(null);
        notify(rogue, LegalOperations.hitFromAngel);
        notify(rogue, LegalOperations.killedHero);
    }

    @Override
    public final void help(final Wizard wizard) {
        if (wizard.getHp() == 0) {
            return;
        }

        wizard.killHero(null);
        notify(wizard, LegalOperations.hitFromAngel);
        notify(wizard, LegalOperations.killedHero);
    }

    @Override
    public final String toString() {
        return "TheDoomer";
    }
}
