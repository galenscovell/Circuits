
/**
 * GRID
 * Responsible for grid construction.
 */

package com.galenscovell.logic;

import com.galenscovell.util.Constants;

import java.util.Random;


public class Grid {
    private Cell[][] cells;
    private int rows, columns, cellSize;


    public Grid() {
        this.cellSize = 40;
        this.rows = Constants.SCREEN_Y / cellSize;
        this.columns = Constants.SCREEN_X / cellSize;
        this.cells = new Cell[columns][rows];
        constructGrid();
        placeNodes();
    }

    public Cell[][] getGrid() {
        return cells;
    }

    public Cell getCell(int column, int row) {
        return cells[column][row];
    }

    private void constructGrid() {
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                cells[x][y] = new Cell(x, y, cellSize);
            }
        }
    }

    private void placeNodes() {
        Random random = new Random();
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                boolean[] neighbors = findNeighbors(x, y);
                if (!neighbors[0] && !neighbors[2]) {
                    int nodeChance = random.nextInt(5);
                    if (nodeChance > 2) {
                        getCell(x, y).setOccupied();
                    }
                }
            }
        }
    }

    private boolean[] findNeighbors(int x, int y) {
        boolean[] neighbors = new boolean[4];
        if (!isOutOfBounds(x - 1, y) && getCell(x - 1, y).isOccupied()) {
            neighbors[0] = true;
        }
        if (!isOutOfBounds(x + 1, y) && getCell(x + 1, y).isOccupied()) {
            neighbors[1] = true;
        }
        if (!isOutOfBounds(x, y - 1) && getCell(x, y - 1).isOccupied()) {
            neighbors[2] = true;
        }
        if (!isOutOfBounds(x, y + 1) && getCell(x, y + 1).isOccupied()) {
            neighbors[3] = true;
        }
        return neighbors;
    }

    private boolean isOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= columns || y >= rows);
    }
}
