package com.Heroes;

import com.Angels.Angel;
import com.Map.LandTypes;
import com.Observer.LegalOperations;
import com.Strategies.Knight.FirstStrategy;
import com.Strategies.Knight.SecondStrategy;
import com.Strategies.StrategyNotFoundException;
import com.Utils.Constants;
import com.Visitor.Visitor;

public class Knight extends Hero {
    public Knight(final int row, final int column, final int index) {
        super(row, column, index);
        setHp(Constants.Heroes.Knight.KNIGHT_HP);
        setMaxHP(Constants.Heroes.Knight.KNIGHT_HP);
    }

    public final int executeBaseDamage(final Hero h, final LandTypes landType) {
        // get base damage
        int damage = Constants.Heroes.Knight.KNIGHT_EXECUTE_BASE_DAMAGE
                + Constants.Heroes.Knight.KNIGHT_EXECUTE_DAMAGE_PER_LEVEL * getLevel();

        // apply land modifier
        if (landType == LandTypes.Land) {
            damage = Math.round(damage * (1 + Constants.Heroes.Knight.KNIGHT_LAND_MODIFIER));
        }

        // return damage after applying land modifier
        return damage;
    }

    public final int execute(final Hero h, final int damage) {
        // set overtime abilities and damage
        h.setOvertimeSlam(Constants.Heroes.Knight.KNIGHT_OVERTIME_ROUNDS);
        h.setOvertimeDamage(0);
        h.setOvertimeRounds(0);

        // do not apply race modifier if the hero is killed instantly
        if (damage == h.getHp()) {
            return damage;
        }

        // apply race modifier
        float modifier = h.knightExecuteRaceModifier();
        if (modifier == 0) {
            return damage;
        } else {
            return Math.round(damage * (1 + modifier + super.getBonus()));
        }
    }

    public final int slamBaseDamage(final LandTypes landType) {
        // count the base damage
        int damage = Constants.Heroes.Knight.KNIGHT_SLAM_BASE_DAMAGE
                + Constants.Heroes.Knight.KNIGHT_SLAM_PER_LEVEL * getLevel();

        // apply land modifier
        if (landType == LandTypes.Land) {
            damage = Math.round(damage * (1 + Constants.Heroes.Knight.KNIGHT_LAND_MODIFIER));
        }

        return damage;
    }

    public final int slam(final Hero h, final int damage) {
        // apply race modifier
        float modifier = h.knightSlamRaceModifier();
        if (modifier == 0) {
            return damage;
        } else {
            return Math.round(damage * (1 + modifier + super.getBonus()));
        }
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
            setMaxHP(getMaxHP() + Constants.Heroes.Knight.KNIGHT_HP_PER_LEVEL
                            * (newLevel - getLevel()));
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

        int lowerLimit = Math.round(Constants.Heroes.Knight.STRATEGY_LOWER_LIMIT * getMaxHP());
        int higherLimit = Math.round(Constants.Heroes.Knight.STRATEGY_HIGHER_LIMIT * getMaxHP());

        if (getHp() < lowerLimit) {
            setStrategy(new SecondStrategy(this));
        } else if (getHp() < higherLimit) {
            setStrategy(new FirstStrategy(this));
        } else {
            throw new StrategyNotFoundException();
        }
    }

    @Override
    public final String toString() {
        return "Knight " + getIndex();
    }

    @Override
    public final void acceptAngel(final Angel angel) {
        angel.help(this);
    }

    @Override
    public final float pyromancerFireblastRaceModifier() {
        return Constants.Heroes.Pyromancer.PYROMANCER_FIREBLAST_KNIGHT_MODIFIER;
    }

    @Override
    public final float pyromancerIgniteRaceModifier() {
        return Constants.Heroes.Pyromancer.PYROMANCER_IGNITE_KNIGHT_MODIFIER;
    }

    @Override
    public final float knightExecuteRaceModifier() {
        return Constants.Heroes.Knight.KNIGHT_EXECUTE_KNIGHT_MODIFIER;
    }

    @Override
    public final float knightSlamRaceModifier() {
        return Constants.Heroes.Knight.KNIGHT_SLAM_KNIGHT_MODIFIER;
    }

    @Override
    public final float wizardDrainRaceModifier() {
        return Constants.Heroes.Wizard.WIZARD_DRAIN_KNIGHT_MODIFIER;
    }

    @Override
    public final float wizardDeflectRaceModifier() {
        return Constants.Heroes.Wizard.WIZARD_DEFLECT_KNIGHT_MODIFIER;
    }

    @Override
    public final float rogueBackstabRaceModifier() {
        return Constants.Heroes.Rogue.ROGUE_BACKSTAB_KNIGHT_MODIFIER;
    }

    @Override
    public final float rogueParalysisRaceModifier() {
        return Constants.Heroes.Rogue.ROGUE_PARALYSIS_KNIGHT_MODIFIER;
    }
}
