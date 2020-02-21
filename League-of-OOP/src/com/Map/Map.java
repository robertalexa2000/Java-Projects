package com.Map;

public final class Map {
    private static Map instance = null;
    private MapCell[][] cells;

    private Map() {
    }

    public static Map getInstance() {
        if (instance == null) {
            return new Map();
        }

        return instance;
    }

    public void initMap(final int rows, final int columns, final String[] mapLines) {
        cells = new MapCell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new MapCell(mapLines[i].charAt(j));
            }
        }
    }

    public MapCell[][] getCells() {
        return cells;
    }
}
