package com.Factory;

import com.Angels.Angel;
import com.Angels.DamageAngel;
import com.Angels.Dracula;
import com.Angels.TheDoomer;
import com.Angels.DarkAngel;
import com.Angels.GoodBoy;
import com.Angels.LevelUpAngel;
import com.Angels.LifeGiver;
import com.Angels.SmallAngel;
import com.Angels.Spawner;
import com.Angels.XPAngel;

public final class AngelFactory {
    private static AngelFactory instance = null;

    private AngelFactory() {
    }

    public static AngelFactory getInstance() {
        if (instance == null) {
            instance = new AngelFactory();
        }

        return instance;
    }

    public Angel getAngel(final String angelType, final int row, final int column) {
        switch (angelType) {
            case "DamageAngel":
                return new DamageAngel(row, column);
            case "TheDoomer":
                return new TheDoomer(row, column);
            case "Dracula":
                return new Dracula(row, column);
            case "XPAngel":
                return new XPAngel(row, column);
            case "SmallAngel":
                return new SmallAngel(row, column);
            case "GoodBoy":
                return new GoodBoy(row, column);
            case "LifeGiver":
                return new LifeGiver(row, column);
            case "LevelUpAngel":
                return new LevelUpAngel(row, column);
            case "DarkAngel":
                return new DarkAngel(row, column);
            case "Spawner":
                return new Spawner(row, column);
            default:
                return null;
        }
    }
}
