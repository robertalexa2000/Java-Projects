package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Pyromancer;
import com.Heroes.Rogue;
import com.Heroes.Wizard;
import com.Observer.LegalOperations;
import com.Utils.Constants;

public class LevelUpAngel extends AngelAbstract {
    public LevelUpAngel(final int row, final int column) {
        super(row, column);
    }

    @Override
    public final void help(final Knight knight) {
        if (knight.getHp() == 0) {
            return;
        }

        knight.setBonus(knight.getBonus() + Constants.Angels.LevelUpAngel.KNIGHT);
        int newXp = Constants.XP_LEVEL_UP + knight.getLevel() * Constants.XP_LEVEL_UP_MULTIPLIER;
        knight.setXp(newXp);
        knight.setLevel(knight.getLevel() + 1);
        knight.setMaxHP(knight.getMaxHP() + Constants.Heroes.Knight.KNIGHT_HP_PER_LEVEL);
        knight.setHp(knight.getMaxHP());
        notify(knight, LegalOperations.helpFromAngel);
        notify(knight, LegalOperations.levelUp);
    }

    @Override
    public final void help(final Pyromancer pyromancer) {
        if (pyromancer.getHp() == 0) {
            return;
        }

        pyromancer.setBonus(pyromancer.getBonus() + Constants.Angels.LevelUpAngel.PYROMANCER);
        int newXp = Constants.XP_LEVEL_UP
                + pyromancer.getLevel() * Constants.XP_LEVEL_UP_MULTIPLIER;
        pyromancer.setXp(newXp);
        pyromancer.setLevel(pyromancer.getLevel() + 1);
        pyromancer.setMaxHP(pyromancer.getMaxHP()
                + Constants.Heroes.Pyromancer.PYROMANCER_HP_PER_LEVEL);
        pyromancer.setHp(pyromancer.getMaxHP());
        notify(pyromancer, LegalOperations.helpFromAngel);
        notify(pyromancer, LegalOperations.levelUp);
    }

    @Override
    public final void help(final Rogue rogue) {
        if (rogue.getHp() == 0) {
            return;
        }

        rogue.setBonus(rogue.getBonus() + Constants.Angels.LevelUpAngel.ROUGE);
        int newXp = Constants.XP_LEVEL_UP + rogue.getLevel() * Constants.XP_LEVEL_UP_MULTIPLIER;
        rogue.setXp(newXp);
        rogue.setLevel(rogue.getLevel() + 1);
        rogue.setMaxHP(rogue.getMaxHP() + Constants.Heroes.Rogue.ROGUE_HP_PER_LEVEL);
        rogue.setHp(rogue.getMaxHP());
        notify(rogue, LegalOperations.helpFromAngel);
        notify(rogue, LegalOperations.levelUp);
    }

    @Override
    public final void help(final Wizard wizard) {
        if (wizard.getHp() == 0) {
            return;
        }

        wizard.setBonus(wizard.getBonus() + Constants.Angels.LevelUpAngel.WIZARD);
        int newXp = Constants.XP_LEVEL_UP + wizard.getLevel() * Constants.XP_LEVEL_UP_MULTIPLIER;
        wizard.setXp(newXp);
        wizard.setLevel(wizard.getLevel() + 1);
        wizard.setMaxHP(wizard.getMaxHP() + Constants.Heroes.Wizard.WIZARD_HP_PER_LEVEL);
        wizard.setHp(wizard.getMaxHP());
        notify(wizard, LegalOperations.helpFromAngel);
        notify(wizard, LegalOperations.levelUp);
    }

    @Override
    public final String toString() {
        return "LevelUpAngel";
    }
}
