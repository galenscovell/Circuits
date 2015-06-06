
/**
 * GRID
 * Responsible for grid construction.
 */

package com.galenscovell.logic;

import com.galenscovell.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Grid {
    private Cell[][] cells;
    private int rows, columns, cellSize;


    public Grid() {
        this.cellSize = Constants.CELLSIZE;
        this.rows = Constants.ROWS;
        this.columns = Constants.COLUMNS;
        this.cells = new Cell[rows][columns];
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
        return cells[y][x];
    }

    public Cell selectCell(float x, float y) {
        // Modify input to take into account forced margins on grid
        x -= (cellSize * 2);
        y += (cellSize * 2);
        // (SCREEN_Y - y) due to difference in input/camera coordinate systems (inverted y, otherwise)
        Cell selected = getCell((int) x / cellSize, (int) (Constants.SCREEN_Y - y) / cellSize);
        if (selected != null && selected.isOccupied()) {
            return selected;
        }
        return null;
    }

    public void checkMove(int[] dir, Cell cell) {
        Cell foundCell;
        List<Cell> coveredCells = new ArrayList<Cell>();
        boolean searching = true;
        int x = cell.getX();
        int y = cell.getY();
        int dx = dir[0] < 0 ? -1 : dir[0] > 0 ? 1 : 0;
        int dy = dir[1] < 0 ? -1 : dir[1] > 0 ? 1 : 0;

        while (!isOutOfBounds(x + dx, y + dy) && searching) {
            x += dx;
            y += dy;
            foundCell = getCell(x, y);
            if (foundCell != null) {
                if (foundCell.isOccupied()) {
                    searching = false;
                    foundCell.toggleSelected();
                    for (Cell coveredCell : coveredCells) {
                        coveredCell.setBridged(dy, dy);
                    }
                } else if (foundCell.isBridged()) {
                    return;
                } else {
                    coveredCells.add(foundCell);
                }
            }
        }
    }

    private void constructGrid() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                cells[y][x] = new Cell(x, y);
            }
        }
    }

    private void placeNodes() {
        Random random = new Random();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                boolean[] neighbors = findNeighbors(cell.getX(), cell.getY());
                if (!neighbors[0] && !neighbors[2]) {
                    int nodeChance = random.nextInt(10);
                    if (nodeChance > 6) {
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
