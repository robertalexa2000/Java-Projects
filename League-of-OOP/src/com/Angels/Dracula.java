package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Pyromancer;
import com.Heroes.Rogue;
import com.Heroes.Wizard;
import com.Observer.LegalOperations;
import com.Utils.Constants;

public class Dracula extends AngelAbstract {
    public Dracula(final int row, final int column) {
        super(row, column);
    }

    @Override
    public final void help(final Knight knight) {
        if (knight.getHp() == 0) {
            return;
        }

        knight.setBonus(knight.getBonus() + Constants.Angels.Dracula.KNIGHT_BONUS);
        knight.setHp(knight.getHp() + Constants.Angels.Dracula.KNIGHT_HP);
        notify(knight, LegalOperations.hitFromAngel);

        if (knight.getHp() <= 0) {
            knight.killHero(null);
            notify(knight, LegalOperations.killedHero);
        }
    }

    @Override
    public final void help(final Pyromancer pyromancer) {
        if (pyromancer.getHp() == 0) {
            return;
        }

        pyromancer.setBonus(pyromancer.getBonus() + Constants.Angels.Dracula.PYROMANCER_BONUS);
        pyromancer.setHp(pyromancer.getHp() + Constants.Angels.Dracula.PYROMANCER_HP);
        notify(pyromancer, LegalOperations.hitFromAngel);

        if (pyromancer.getHp() <= 0) {
            pyromancer.killHero(null);
            notify(pyromancer, LegalOperations.killedHero);
        }
    }

    @Override
    public final void help(final Rogue rogue) {
        if (rogue.getHp() == 0) {
            return;
        }

        rogue.setBonus(rogue.getBonus() + Constants.Angels.Dracula.ROGUE_BONUS);
        rogue.setHp(rogue.getHp() + Constants.Angels.Dracula.ROGUE_HP);
        notify(rogue, LegalOperations.hitFromAngel);

        if (rogue.getHp() <= 0) {
            rogue.killHero(null);
            notify(rogue, LegalOperations.killedHero);
        }
    }

    @Override
    public final void help(final Wizard wizard) {
        if (wizard.getHp() == 0) {
            return;
        }

        wizard.setBonus(wizard.getBonus() + Constants.Angels.Dracula.WIZARD_BONUS);
        wizard.setHp(wizard.getHp() + Constants.Angels.Dracula.WIZARD_HP);
        notify(wizard, LegalOperations.hitFromAngel);

        if (wizard.getHp() <= 0) {
            wizard.killHero(null);
            notify(wizard, LegalOperations.killedHero);
        }
    }

    @Override
    public final String toString() {
        return "Dracula";
    }
}
