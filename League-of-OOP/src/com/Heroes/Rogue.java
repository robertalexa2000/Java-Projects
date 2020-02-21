package com.Heroes;

import com.Angels.Angel;
import com.Map.LandTypes;
import com.Observer.LegalOperations;
import com.Strategies.Rogue.FirstStrategy;
import com.Strategies.Rogue.SecondStrategy;
import com.Strategies.StrategyNotFoundException;
import com.Utils.Constants;
import com.Visitor.Visitor;

public class Rogue extends Hero {
    private int criticalRound;

    public Rogue(final int row, final int column, final int index) {
        super(row, column, index);
        setHp(Constants.Heroes.Rogue.ROGUE_HP);
        setMaxHP(Constants.Heroes.Rogue.ROGUE_HP);
        criticalRound = -1;
    }

    public final int backstabBaseDamage(final LandTypes landType) {
        // count base damage
        int damage = Constants.Heroes.Rogue.ROGUE_BACKSTAB_BASE_DAMAGE
                + Constants.Heroes.Rogue.ROGUE_BACKSTAB_DAMAGE_PER_LEVEL * getLevel();

        // check if to apply critical
        if (criticalRound == -1 && landType == LandTypes.Woods) {
            damage = Math.round(damage * Constants.Heroes.Rogue.ROGUE_BACKSTAB_CRITICAL_MULTIPLIER);
            damage = Math.round(damage * (1 + Constants.Heroes.Rogue.ROGUE_LAND_MODIFIER));
            criticalRound = 0;
            return damage;
        }
        if (criticalRound == -1) {
            criticalRound = 0;
            return damage;
        }
        if (criticalRound < Constants.Heroes.Rogue.ROGUE_BACKSTAB_CRITICAL_ROUND) {
            criticalRound++;
        }
        if (criticalRound == Constants.Heroes.Rogue.ROGUE_BACKSTAB_CRITICAL_ROUND
                && landType == LandTypes.Woods) {
            damage = Math.round(damage * Constants.Heroes.Rogue.ROGUE_BACKSTAB_CRITICAL_MULTIPLIER);
            criticalRound = 0;
        }
        if (criticalRound == Constants.Heroes.Rogue.ROGUE_BACKSTAB_CRITICAL_ROUND) {
            criticalRound = 0;
        }

        // apply land modifier
        if (landType == LandTypes.Woods) {
            damage = Math.round(damage * (1 + Constants.Heroes.Rogue.ROGUE_LAND_MODIFIER));
        }

        return damage;
    }

    public final int backstab(final Hero h, final int damage) {
        // apply race modifier
        float modifier = h.rogueBackstabRaceModifier();
        if (modifier == 0) {
            return damage;
        } else {
            return Math.round(damage * (1 + modifier + super.getBonus()));
        }
    }

    public final int paralysisBaseDamage(final LandTypes landType) {
        // count base damage
        int damage = Constants.Heroes.Rogue.ROGUE_PARALYSIS_BASE_DAMAGE
                + Constants.Heroes.Rogue.ROGUE_PARALYSIS_DAMAGE_PER_LEVEL * getLevel();

        // apply land modifier
        if (landType == LandTypes.Woods) {
            damage = Math.round(damage * (1 + Constants.Heroes.Rogue.ROGUE_LAND_MODIFIER));
        }

        return damage;
    }

    public final int paralysis(final Hero h, final LandTypes landType, final int damage) {
        // count base damage
        int overtimeDamage = Constants.Heroes.Rogue.ROGUE_PARALYSIS_BASE_DAMAGE
                + Constants.Heroes.Rogue.ROGUE_PARALYSIS_DAMAGE_PER_LEVEL * getLevel();

        // apply land modifier and set overtime no rounds
        if (landType == LandTypes.Woods) {
            overtimeDamage = Math.round(overtimeDamage
                    * (1 + Constants.Heroes.Rogue.ROGUE_LAND_MODIFIER));
            h.setOvertimeRounds(Constants.Heroes.Rogue.ROGUE_PARALYSIS_ENHANCED_OVERTIME);
            h.setOvertimeSlam(Constants.Heroes.Rogue.ROGUE_PARALYSIS_ENHANCED_OVERTIME);
        } else {
            h.setOvertimeRounds(Constants.Heroes.Rogue.ROGUE_PARALYSIS_BASIC_OVERTIME);
            h.setOvertimeSlam(Constants.Heroes.Rogue.ROGUE_PARALYSIS_BASIC_OVERTIME);
        }

        // set overtime damage
        float modifier = h.rogueParalysisRaceModifier();
        if (modifier == 0) {
            h.setOvertimeDamage(overtimeDamage);
            return damage;
        } else {
            overtimeDamage = Math.round(overtimeDamage
                    * (1 + modifier + super.getBonus() - Constants.Heroes.Rogue.ANGEL_BONUS_MODIFER)
            );
            h.setOvertimeDamage(overtimeDamage);
            return Math.round(damage
                    * (1 + modifier + super.getBonus() - Constants.Heroes.Rogue.ANGEL_BONUS_MODIFER)
            );
        }
    }

    @Override
    public final int accept(final Visitor v, final Hero h, final LandTypes landType,
                            final int baseDamage1, final int baseDamage2,
                            final int oppDamage1, final int oppDamage2) {
        return v.visit(this, h, landType, baseDamage1, baseDamage2, oppDamage1, oppDamage2);
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
                    + Constants.Heroes.Rogue.ROGUE_HP_PER_LEVEL * (newLevel - getLevel()));
            setHp(getMaxHP());
            setLevel(newLevel);
        }
    }

    @Override
    public final void chooseStrategy() throws StrategyNotFoundException {
        if (getHp() == 0) {
            throw new StrategyNotFoundException();
        }

        int lowerLimit = Math.round(Constants.Heroes.Rogue.STRATEGY_LOWER_LIMIT * getMaxHP());
        int higherLimit = Math.round(Constants.Heroes.Rogue.STRATEGY_HIGHER_LIMIT * getMaxHP());

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
        return "Rogue " + getIndex();
    }

    @Override
    public final float knightExecuteRaceModifier() {
        return Constants.Heroes.Knight.KNIGHT_EXECUTE_ROGUE_MODIFIER;
    }

    @Override
    public final float knightSlamRaceModifier() {
        return Constants.Heroes.Knight.KNIGHT_SLAM_ROGUE_MODIFIER;
    }

    @Override
    public final float pyromancerFireblastRaceModifier() {
        return Constants.Heroes.Pyromancer.PYROMANCER_FIREBLAST_ROGUE_MODIFIER;
    }

    @Override
    public final float pyromancerIgniteRaceModifier() {
        return Constants.Heroes.Pyromancer.PYROMANCER_IGNITE_ROGUE_MODIFIER;
    }

    @Override
    public final float rogueBackstabRaceModifier() {
        return Constants.Heroes.Rogue.ROGUE_BACKSTAB_ROGUE_MODIFIER;
    }

    @Override
    public final float rogueParalysisRaceModifier() {
        return Constants.Heroes.Rogue.ROGUE_PARALYSIS_ROGUE_MODIFIER;
    }

    @Override
    public final float wizardDrainRaceModifier() {
        return Constants.Heroes.Wizard.WIZARD_DRAIN_ROGUE_MODIFIER;
    }

    @Override
    public final float wizardDeflectRaceModifier() {
        return Constants.Heroes.Wizard.WIZARD_DEFLECT_ROGUE_MODIFIER;
    }
}
