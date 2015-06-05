
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
        this.cellSize = Constants.CELLSIZE;
        this.rows = Constants.GAME_Y / cellSize;
        this.columns = Constants.GAME_X / cellSize;
        this.cells = new Cell[columns][rows];
        constructGrid();
        placeNodes();
    }

    public Cell[][] getGrid() {
        return cells;
    }

    public Cell getCell(int x, int y) {
        if (isOutOfBounds(x, y)) {
            return null;
        }
        return cells[x][y];
    }

    public void selectCell(float x, float y) {
        // Modify input to take into account forced margins on grid
        x -= cellSize;
        y += (cellSize * 3);
        // (SCREEN_Y - y) due to difference in input/camera coordinate systems (inverted y, otherwise)
        Cell selected = getCell((int) x / cellSize, (int) (Constants.SCREEN_Y - y) / cellSize);
        if (selected != null && selected.isOccupied()) {
            selected.toggleSelected();
        }
    }

    private void constructGrid() {
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    private void placeNodes() {
        Random random = new Random();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                boolean[] neighbors = findNeighbors(cell.getX(), cell.getY());
                if (!neighbors[0] && !neighbors[2]) {
                    int nodeChance = random.nextInt(5);
                    if (nodeChance > 2) {
                        cell.setOccupied();
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
