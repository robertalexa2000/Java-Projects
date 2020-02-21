package main;

import com.Map.Map;
import fileio.FileSystem;

import java.util.ArrayList;
import java.util.List;

final class GameInputLoader {
    private final String mInputPath;
    private final String mOutputPath;

    GameInputLoader(final String inputPath, final String outputPath) {
        mInputPath = inputPath;
        mOutputPath = outputPath;
    }

    GameInput load() {
        int rows = 0, columns = 0;
        Map map = null;
        int noPlayers = 0;
        char[] players = null;
        int[][] positions = null;
        int noRounds = 0;
        String[] movements = null;
        List<List<String>> angelsStrings = null;

        try {
            FileSystem fs = new FileSystem(mInputPath, mOutputPath);

            rows = fs.nextInt();
            columns = fs.nextInt();
            String[] mapLines = new String[rows];
            for (int i = 0; i < rows; i++) {
                mapLines[i] = fs.nextWord();
            }
            map = Map.getInstance();
            map.initMap(rows, columns, mapLines);

            noPlayers = fs.nextInt();
            players = new char[noPlayers];
            positions = new int[noPlayers][2];
            for (int i = 0; i < noPlayers; i++) {
                players[i] = fs.nextWord().charAt(0);
                positions[i][0] = Integer.parseInt(fs.nextWord());
                positions[i][1] = Integer.parseInt(fs.nextWord());
            }

            noRounds = fs.nextInt();
            movements = new String[noRounds];
            for (int i = 0; i < noRounds; i++) {
                movements[i] = fs.nextWord();
            }

            angelsStrings = new ArrayList<>();
            for (int i = 0; i < noRounds; i++) {
                int noAngels = fs.nextInt();

                List<String> angels = new ArrayList<>();
                for (int j = 0; j < noAngels; j++) {
                    angels.add(fs.nextWord());
                }
                angelsStrings.add(angels);
            }

            fs.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        GameInput gameInput = new GameInput();
        gameInput.setMap(rows, columns, map);
        gameInput.setPlayers(noPlayers, players, positions);
        gameInput.setGame(noRounds, movements);
        gameInput.setAngels(angelsStrings);

        return gameInput;
    }
}
