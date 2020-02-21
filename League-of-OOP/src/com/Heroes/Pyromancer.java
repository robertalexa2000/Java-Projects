package com.Heroes;

import com.Angels.Angel;
import com.Map.LandTypes;
import com.Observer.LegalOperations;
import com.Strategies.Pyromancer.FirstStrategy;
import com.Strategies.Pyromancer.SecondStrategy;
import com.Strategies.StrategyNotFoundException;
import com.Utils.Constants;
import com.Visitor.Visitor;

public class Pyromancer extends Hero {
    public Pyromancer(final int row, final int column, final int index) {
        super(row, column, index);
        setHp(Constants.Heroes.Pyromancer.HP);
        setMaxHP(Constants.Heroes.Pyromancer.HP);
    }

    public final int fireblastBaseDamage(final LandTypes landType) {
        // get the base damage
        int damage = Constants.Heroes.Pyromancer.PYROMANCER_FIREBLAST_BASE_DAMAGE
                + Constants.Heroes.Pyromancer.PYROMANCER_FIREBLAST_DAMAGE_PER_LEVEL * getLevel();

        // apply land modifier
        if (landType == LandTypes.Volcanic) {
            damage = Math.round(
                    damage * (1 + Constants.Heroes.Pyromancer.PYROMANCER_LAND_MODIFIER)
            );
        }

        return damage;
    }

    public final int fireblast(final Hero h, final int damage) {
        // apply race modifier
        float modifier = h.pyromancerFireblastRaceModifier();
        if (modifier == 0) {
            return damage;
        } else {
            return Math.round(damage * (1 + modifier + super.getBonus()));
        }
    }

    public final int igniteBaseDamage(final LandTypes landType) {
        // get the base damage
        int damage = Constants.Heroes.Pyromancer.PYROMANCER_IGNITE_BASE_DAMAGE
                + Constants.Heroes.Pyromancer.PYROMANCER_IGNITE_DAMAGE_PER_LEVEL * getLevel();

        // apply land modifier
        if (landType == LandTypes.Volcanic) {
            damage = Math.round(
                    damage * (1 + Constants.Heroes.Pyromancer.PYROMANCER_LAND_MODIFIER)
            );
        }

        return damage;
    }

    public final int ignite(final Hero h, final LandTypes landType, final int damage) {
        // count overtime damage
        h.setOvertimeSlam(0);
        h.setOvertimeRounds(Constants.Heroes.Pyromancer.PYROMANCER_OVERTIME_ROUNDS);
        int overtimeDamage = Constants.Heroes.Pyromancer.PYROMANCER_OVERTIME_DAMAGE
                + getLevel() * Constants.Heroes.Pyromancer.PYROMANCER_OVERTIME_DAMAGE_PER_LEVEL;

        // apply land modifier
        if (landType == LandTypes.Volcanic) {
            overtimeDamage = Math.round(overtimeDamage
                    * (1 + Constants.Heroes.Pyromancer.PYROMANCER_LAND_MODIFIER));
        }

        // apply overtime damage
        float modifier = h.pyromancerIgniteRaceModifier();
        if (modifier == 0) {
            h.setOvertimeDamage(overtimeDamage);
            return damage;
        } else {
            h.setOvertimeDamage(Math.round(
                    overtimeDamage * (1 + modifier + super.getBonus()))
            );
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
            setMaxHP(getMaxHP() + Constants.Heroes.Pyromancer.PYROMANCER_HP_PER_LEVEL
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

        int lowerLimit = Math.round(Constants.Heroes.Pyromancer.STRATEGY_LOWER_LIMIT * getMaxHP());
        int higherLimit = Math.round(Constants.Heroes.Pyromancer.STRATEGY_HIGHER_LIMIT
                * getMaxHP());

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
        return "Pyromancer " + getIndex();
    }

    @Override
    public final float pyromancerFireblastRaceModifier() {
        return Constants.Heroes.Pyromancer.PYROMANCER_FIREBLAST_PYROMANCER_MODIFIER;
    }

    @Override
    public final float pyromancerIgniteRaceModifier() {
        return Constants.Heroes.Pyromancer.PYROMANCER_IGNITE_PYROMANCER_MODIFIER;
    }

    @Override
    public final float knightExecuteRaceModifier() {
        return Constants.Heroes.Knight.KNIGHT_EXECUTE_PYROMANCER_MODIFIER;
    }

    @Override
    public final float knightSlamRaceModifier() {
        return Constants.Heroes.Knight.KNIGHT_SLAM_PYROMANCER_MODIFIER;
    }

    @Override
    public final float wizardDeflectRaceModifier() {
        return Constants.Heroes.Wizard.WIZARD_DEFLECT_PYROMANCER_MODIFIER;
    }

    @Override
    public final float wizardDrainRaceModifier() {
        return Constants.Heroes.Wizard.WIZARD_DRAIN_PYROMANCER_MODIFIER;
    }

    @Override
    public final float rogueBackstabRaceModifier() {
        return Constants.Heroes.Rogue.ROGUE_BACKSTAB_PYROMANCER_MODIFIER;
    }

    @Override
    public final float rogueParalysisRaceModifier() {
        return Constants.Heroes.Rogue.ROGUE_PARALYSIS_PYROMANCER_MODIFIER;
    }
}
