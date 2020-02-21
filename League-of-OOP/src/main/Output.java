package main;

import com.Heroes.Hero;
import fileio.FileSystem;

import java.io.IOException;
import java.util.List;

public final class Output {
    private String input;
    private String output;
    private FileSystem fs;
    private static Output instance = null;

    private Output(final String input, final String output) {
        this.input = input;
        this.output = output;

        try {
            fs = new FileSystem(input, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static Output getInstance(final String input, final String output) {
        if (instance == null) {
            instance = new Output(input, output);
        }

        return instance;
    }

    // sets the beginning of a new round
    public void updateRound(final int round) {
        try {
            if (round != 1) {
                fs.writeNewLine();
            }
            fs.writeWord("~~ Round " + round + " ~~");
            fs.writeNewLine();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // writes the results at the end of the game
    public void displayResults(final GameInput gameInput, final List<Hero> heroes) {
        try {
            fs.writeNewLine();
            fs.writeWord("~~ Results ~~");
            fs.writeNewLine();

            char[] players = gameInput.getPlayers();
            for (int i = 0; i < gameInput.getNoPlayers(); i++) {
                Hero h = heroes.get(i);
                char heroType = players[i];

                if (h.getHp() == 0) {
                    String localOutput = heroType + " dead";
                    fs.writeWord(localOutput);
                    fs.writeNewLine();
                    continue;
                }

                String localOutput = heroType + " " + h.getLevel() + " "
                        + h.getXp() + " " + h.getHp() + " " + h.getRow()
                        + " " + h.getColumn();
                fs.writeWord(localOutput);
                fs.writeNewLine();
            }

            fs.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // used for displaying either a string or a new line (in which case localOutput is null)
    public void writeString(final String localOutput) {
        if (localOutput == null) {
            try {
                fs.writeNewLine();
            } catch (IOException e) {
                System.out.println(e);
            }

            return;
        }

        try {
            fs.writeWord(localOutput);
            fs.writeNewLine();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public FileSystem getFs() {
        return fs;
    }

    public static Output getInstance() {
        return instance;
    }
}
