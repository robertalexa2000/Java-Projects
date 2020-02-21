package com.Observer;

import com.Angels.Angel;
import com.Heroes.Hero;
import main.Output;

public final class GreatMagician implements Observer {
    private static GreatMagician instance = null;

    private GreatMagician() {
    }

    public static GreatMagician getInstance() {
        if (instance == null) {
            instance = new GreatMagician();
        }

        return instance;
    }

    // handles updates from Hero subjects
    @Override
    public void update(final Hero hero1, final Hero hero2, final LegalOperations legalOperation) {
        Output output = Output.getInstance();
        String outputString;

        switch (legalOperation) {
            case killedHero:
                outputString = "Player " + hero1.toString() + " was killed by " + hero2.toString();
                output.writeString(outputString);
                break;
            case levelUp:
                outputString = hero1.toString() + " reached level " + hero1.getLevel();
                output.writeString(outputString);
                break;
            default:
                break;
        }
    }

    // handles updates from Angel Subjects
    @Override
    public void update(final Angel angel, final Hero hero, final LegalOperations legalOperation) {
        Output output = Output.getInstance();
        String outputString;

        switch (legalOperation) {
            case angelSpawned:
                outputString = "Angel " + angel.toString()
                        + " was spawned at " + angel.getRow() + " " + angel.getColumn();
                output.writeString(outputString);
                break;
            case helpFromAngel:
                outputString = angel.toString() + " helped " + hero.toString();
                output.writeString(outputString);
                break;
            case hitFromAngel:
                outputString = angel.toString() + " hit " + hero.toString();
                output.writeString(outputString);
                break;
            case killedHero:
                outputString = "Player " + hero.toString() + " was killed by an angel";
                output.writeString(outputString);
                break;
            case levelUp:
                outputString = hero.toString() + " reached level " + hero.getLevel();
                output.writeString(outputString);
                break;
            case bringToLife:
                outputString = "Player " + hero.toString() + " was brought to life by an angel";
                output.writeString(outputString);
                break;
            default:
                break;
        }
    }
}
