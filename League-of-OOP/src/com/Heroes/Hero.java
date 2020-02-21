package com.Heroes;

import com.Angels.Angel;
import com.Observer.GreatMagician;
import com.Observer.LegalOperations;
import com.Observer.Observer;
import com.Observer.Subject;
import com.Strategies.Strategy;
import com.Strategies.StrategyNotFoundException;
import com.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero implements HeroElement, Subject {
    private int hp, maxHP;
    private int xp, level;
    private int row, column;
    private int overtimeSlam;
    private int overtimeRounds, overtimeDamage;
    private int index;
    private float bonus;
    private List<Observer> observers;
    private Strategy strategy;

    // default constructor
    public Hero(final int row, final int column, final int index) {
        this.row = row;
        this.column = column;
        this.index = index;
        xp = 0;
        level = 0;
        overtimeSlam = 0;
        overtimeDamage = 0;
        overtimeRounds = 0;
        bonus = 0f;
        observers = new ArrayList<>();
        attach(GreatMagician.getInstance());
    }

    // pattern for levelling up a player. deadHero is null for calls coming from Angels
    // levelUp is implemented in each subclass accordingly
    public abstract void levelUp(Hero deadHero);
    final int auxLevelUp(final Hero deadHero) {
        if (deadHero != null) {
            xp += Math.max(0, Constants.XP_CONSTANT
                    - (level - deadHero.level) * Constants.XP_MULTIPLIER);
        }
        int newLevel = level;

        while (true) {
            int xpLevelUp = Constants.XP_LEVEL_UP + newLevel * Constants.XP_LEVEL_UP_MULTIPLIER;

            if (xp >= xpLevelUp) {
                newLevel++;
            } else {
                return newLevel;
            }
        }
    }

    // the killer is null only if the hero has been killed by an overtime or by an angel
    public final void killHero(final Hero killer) {
        overtimeSlam = 0;
        hp = 0;

        if (killer != null) {
            notify(killer, LegalOperations.killedHero);
        }
    }

    public final void applyOvertime() {
        if (hp != 0 & overtimeRounds > 0) {
            hp -= overtimeDamage;
            overtimeRounds--;
        }
        if (hp == 0 && overtimeRounds > 0) {
            overtimeRounds--;
        }

        if (hp <= 0) {
            killHero(null);
        }
    }

    @Override
    public final void notify(final Hero hero, final LegalOperations legalOperation) {
        for (Observer observer : observers) {
            observer.update(this, hero, legalOperation);
        }
    }

    @Override
    public final void attach(final Observer observer) {
        observers.add(observer);
    }

    @Override
    public final void detach(final Observer observer) {
        observers.remove(observer);
    }

    // method applyStrategy is implemented in superclass.
    // it is aided by abstract chooseStrategy, which is implemented in each subclass.
    public final void applyStrategy() {
        strategy.strategy();
    }
    public abstract void chooseStrategy() throws StrategyNotFoundException;

    // method for applying the angels' bonuses
    // it is implemented in each subclass
    public abstract void acceptAngel(Angel angel);

    @Override
    public final void move(final int x, final int y) {
        row = x;
        column = y;
    }

    public final int getRow() {
        return row;
    }

    public final int getColumn() {
        return column;
    }

    public final int getHp() {
        return hp;
    }

    public final void setHp(final int hp) {
        this.hp = hp;
    }

    public final int getMaxHP() {
        return maxHP;
    }

    public final void setMaxHP(final int maxHP) {
        this.maxHP = maxHP;
    }

    public final int getXp() {
        return xp;
    }

    public final void setXp(final int xp) {
        this.xp = xp;
    }

    public final int getLevel() {
        return level;
    }

    public final void setLevel(final int level) {
        this.level = level;
    }

    public final int getOvertimeSlam() {
        return overtimeSlam;
    }

    public final void setOvertimeSlam(final int overtimeSlam) {
        this.overtimeSlam = overtimeSlam;
    }

    public final void setOvertimeRounds(final int overtimeRounds) {
        this.overtimeRounds = overtimeRounds;
    }

    public final void setOvertimeDamage(final int overtimeDamage) {
        this.overtimeDamage = overtimeDamage;
    }

    public final int getIndex() {
        return index;
    }

    public final int getOvertimeRounds() {
        return overtimeRounds;
    }

    public final List<Observer> getObservers() {
        return observers;
    }

    public final void setRow(final int row) {
        this.row = row;
    }

    public final void setColumn(final int column) {
        this.column = column;
    }

    public final int getOvertimeDamage() {
        return overtimeDamage;
    }

    public final void setIndex(final int index) {
        this.index = index;
    }

    public final void setObservers(final List<Observer> observers) {
        this.observers = observers;
    }

    public final float getBonus() {
        return bonus;
    }

    public final void setBonus(final float bonus) {
        this.bonus = bonus;
    }

    public final Strategy getStrategy() {
        return strategy;
    }

    public final void setStrategy(final Strategy strategy) {
        this.strategy = strategy;
    }
}
