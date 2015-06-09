package com.galenscovell.logic;

import com.galenscovell.util.LevelParser;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GRID
 * Handles cell grid events and organization.
 *
 * @author Galen Scovell
 */

public class Grid {
    private Cell[][] cells;


    public void updateCells(Cell[][] cells) {
        this.cells = cells;
    }

    public void loadLevel() {
        LevelParser parser = new LevelParser();
        int[][] values = parser.parseLevel("very_easy", 1);

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                int gridX = cell.getGridX();
                int gridY = cell.getGridY();
                if (values[gridY][gridX] == 0) {
                    continue;
                }
                cell.setNode(values[gridY][gridX]);
            }
        }
    }

    public void move(float velocityX, float velocityY, Cell cell) {
        int[] dir = new int[2];
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            dir[0] = (velocityX < 0) ? -1 : 1;
        } else {
            dir[1] = (velocityY < 0) ? 1 : -1;
        }
        checkMove(dir, cell);
    }

    private void checkMove(int[] dir, Cell cell) {
        int x = cell.getGridX();
        int y = cell.getGridY();
        int dx = (dir[0] < 0) ? -1 : (dir[0] > 0) ? 1 : 0;
        int dy = (dir[1] < 0) ? -1 : (dir[1] > 0) ? 1 : 0;

        Cell foundCell;
        List<Cell> coveredCells = new ArrayList<Cell>();
        boolean searching = true;

        while (!isOutOfBounds(x + dx, y + dy) && searching) {
            x += dx;
            y += dy;
            foundCell = getCell(x, y);
            if (foundCell != null) {
                if (foundCell.isNode() && !foundCell.isFull()) {
                    searching = false;
                    foundCell.toggleSelected();
                    if (dy > 0) {
                        cell.addConnection(0);
                        foundCell.addConnection(1);
                    } else if (dy < 0) {
                        cell.addConnection(1);
                        foundCell.addConnection(0);
                    } else if (dx < 0) {
                        cell.addConnection(2);
                        foundCell.addConnection(3);
                    } else {
                        cell.addConnection(3);
                        foundCell.addConnection(2);
                    }
                    for (Cell coveredCell : coveredCells) {
                        if (dx == 0) {
                            coveredCell.setTwine(0);
                        } else {
                            coveredCell.setTwine(1);
                        }
                    }
                } else if (foundCell.isTwine()) {
                    return;
                } else {
                    coveredCells.add(foundCell);
                }
            }
        }
    }

    private Cell getCell(int x, int y) {
        if (isOutOfBounds(x, y)) {
            return null;
        }
        return cells[y][x];
    }

    private boolean[] findNeighbors(int x, int y) {
        boolean[] neighbors = new boolean[4];
        if (!isOutOfBounds(x - 1, y) && getCell(x - 1, y) != null) {
            if (getCell(x - 1, y).isNode()) {
                neighbors[0] = true;
            }
        }
        if (!isOutOfBounds(x + 1, y) && getCell(x + 1, y) != null) {
            if (getCell(x + 1, y).isNode()) {
                neighbors[1] = true;
            }
        }
        if (!isOutOfBounds(x, y - 1) && getCell(x, y - 1) != null) {
            if (getCell(x, y - 1).isNode()) {
                neighbors[2] = true;
            }
        }
        if (!isOutOfBounds(x, y + 1) && getCell(x, y + 1) != null) {
            if (getCell(x, y + 1).isNode()) {
                neighbors[3] = true;
            }
        }
        return neighbors;
    }

    private boolean isOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= 9 || y >= 9);
    }
}
