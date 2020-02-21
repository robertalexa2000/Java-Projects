package main;

import com.Map.Map;

import java.util.List;

class GameInput {
    private int rows, columns;
    private Map map;
    private int noPlayers;
    private char[] players;
    private int[][] positions;
    private int noRounds;
    private String[] movements;
    private List<List<String>> angelStrings;

    void setMap(final int localRows, final int localColumns, final Map localMap) {
        this.rows = localRows;
        this.columns = localColumns;
        this.map = localMap;
    }

    void setPlayers(final int localNoPlayers,
                    final char[] localPlayers, final int[][] localPositions) {
        this.noPlayers = localNoPlayers;
        this.players = localPlayers;
        this.positions = localPositions;
    }

    void setGame(final int localNoRounds, final String[] localMovements) {
        this.noRounds = localNoRounds;
        this.movements = localMovements;
    }

    void setAngels(final List<List<String>> localAngelStrings) {
        this.angelStrings = localAngelStrings;
    }

    public Map getMap() {
        return map;
    }

    public String[] getMovements() {
        return movements;
    }

    public int getNoRounds() {
        return noRounds;
    }

    public int[][] getPositions() {
        return positions;
    }

    public char[] getPlayers() {
        return players;
    }

    public int getNoPlayers() {
        return noPlayers;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public List<List<String>> getAngelStrings() {
        return angelStrings;
    }
}
