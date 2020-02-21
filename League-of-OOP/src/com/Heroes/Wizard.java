package com.Heroes;

import com.Angels.Angel;
import com.Map.LandTypes;
import com.Observer.LegalOperations;
import com.Strategies.StrategyNotFoundException;
import com.Strategies.Wizard.FirstStrategy;
import com.Strategies.Wizard.SecondStrategy;
import com.Utils.Constants;
import com.Visitor.Visitor;

public class Wizard extends Hero {
    public Wizard(final int row, final int column, final int index) {
        super(row, column, index);
        setHp(Constants.Heroes.Wizard.WIZARD_HP);
        setMaxHP(Constants.Heroes.Wizard.WIZARD_HP);
    }

    public final int drain(final Hero h, final LandTypes landType) {
        // count the base percentage
        float percentage = Constants.Heroes.Wizard.WIZARD_DRAIN_BASE_PERCENTAGE;
        int level = getLevel();
        while (level > 0) {
            percentage += Constants.Heroes.Wizard.WIZARD_DRAIN_PERCENTAGE_PER_LEVEL;
            level--;
        }

        // apply land modifier to it
        if (landType == LandTypes.Desert) {
            percentage *= (1 + Constants.Heroes.Wizard.WIZARD_LAND_MODIFIER);
        }

        // apply race modifier
        float modifier = h.wizardDrainRaceModifier();
        if (modifier != 0) {
            percentage *= (1 + modifier + super.getBonus());
        }

        int baseHp = Math.min(Math.round(
                Constants.Heroes.Wizard.WIZARD_DRAIN_BASE_HP_CONSTANT * h.getMaxHP()), h.getHp()
        );
        return Math.round(percentage * baseHp);
    }

    public final int deflect(final Hero h, final LandTypes landType, final float receivedDamage) {
        // count the base percentage
        float percentage = Constants.Heroes.Wizard.WIZARD_DEFLECT_BASE_PERCENTAGE;
        int level = getLevel();
        while (percentage <= Constants.Heroes.Wizard.WIZARD_DEFLECT_MAX_PERCENTAGE && level > 0) {
            percentage += Constants.Heroes.Wizard.WIZARD_DEFLECT_LEVEL_PERCENTAGE;
            level--;
        }

        // apply land modifier
        if (landType == LandTypes.Desert) {
            percentage *= (1 + Constants.Heroes.Wizard.WIZARD_LAND_MODIFIER);
        }

        // apply race modifier
        float modifier = h.wizardDeflectRaceModifier();
        if (modifier != 0) {
            percentage *= (1 + modifier + super.getBonus());
        }
        return Math.round(percentage * receivedDamage);
    }

    @Override
    public final void levelUp(final Hero deadHero) {
        int level = getLevel();
        int newLevel = auxLevelUp(deadHero);

        if (newLevel != level) {
            for (int i = getLevel() + 1; i <= newLevel; i++) {
                setLevel(i);
                notify(null, LegalOperations.levelUp);
            }

            setLevel(level);
            setMaxHP(getMaxHP()
                    + Constants.Heroes.Wizard.WIZARD_HP_PER_LEVEL * (newLevel - getLevel()));
            setHp(getMaxHP());
            setLevel(newLevel);
        }
    }

    @Override
    public final int accept(final Visitor v, final Hero h, final LandTypes landType,
                            final int baseDamage1, final int baseDamage2,
                            final int oppDamage1, final int oppDamage2) {
        return v.visit(this, h, landType, baseDamage1, baseDamage2, oppDamage1, oppDamage2);
    }

    @Override
    public final void chooseStrategy() throws StrategyNotFoundException {
        if (getHp() == 0) {
            throw new StrategyNotFoundException();
        }

        int lowerLimit = Math.round(Constants.Heroes.Wizard.STRATEGY_LOWER_LIMIT * getMaxHP());
        int higherLimit = Math.round(Constants.Heroes.Wizard.STRATEGY_HIGHER_LIMIT * getMaxHP());

        if (getHp() < lowerLimit) {
            setStrategy(new SecondStrategy(this));
        } else if (getHp() < higherLimit) {
            setStrategy(new FirstStrategy(this));
        } else {
            throw new StrategyNotFoundException();
        }
    }

    @Override
    public final void acceptAngel(final Angel angel) {
        angel.help(this);
    }

    @Override
    public final String toString() {
        return "Wizard " + getIndex();
    }

    @Override
    public final float wizardDeflectRaceModifier() {
        return -1;
    }

    @Override
    public final float wizardDrainRaceModifier() {
        return Constants.Heroes.Wizard.WIZARD_DRAIN_WIZARD_MODIFIER;
    }

    @Override
    public final float rogueBackstabRaceModifier() {
        return Constants.Heroes.Rogue.ROGUE_BACKSTAB_WIZARD_MODIFIER;
    }

    @Override
    public final float rogueParalysisRaceModifier() {
        return Constants.Heroes.Rogue.ROGUE_PARALYSIS_WIZARD_MODIFIER;
    }

    @Override
    public final float pyromancerFireblastRaceModifier() {
        return Constants.Heroes.Pyromancer.PYROMANCER_FIREBLAST_WIZARD_MODIFIER;
    }

    @Override
    public final float pyromancerIgniteRaceModifier() {
        return Constants.Heroes.Pyromancer.PYROMANCER_IGNITE_WIZARD_MODIFIER;
    }

    @Override
    public final float knightExecuteRaceModifier() {
        return Constants.Heroes.Knight.KNIGHT_EXECUTE_WIZARD_MODIFIER;
    }

    @Override
    public final float knightSlamRaceModifier() {
        return Constants.Heroes.Knight.KNIGHT_SLAM_WIZARD_MODIFIER;
    }
}















