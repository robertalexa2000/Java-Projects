package com.Factory;

import com.Heroes.Hero;
import com.Heroes.Knight;
import com.Heroes.Rogue;
import com.Heroes.Pyromancer;
import com.Heroes.Wizard;

public final class HeroFactory {
    private static HeroFactory instance = null;

    private HeroFactory() {
    }

    public static HeroFactory getInstance() {
        if (instance == null) {
            instance = new HeroFactory();
        }

        return instance;
    }

    public Hero getHero(final char heroType, final int row, final int column, final int index) {
        switch (heroType) {
            case 'K':
                return new Knight(row, column, index);
            case 'R':
                return new Rogue(row, column, index);
            case 'P':
                return new Pyromancer(row, column, index);
            case 'W':
                return new Wizard(row, column, index);
            default:
                return null;
        }
    }
}
