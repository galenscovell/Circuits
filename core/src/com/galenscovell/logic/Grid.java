package com.galenscovell.logic;

import com.badlogic.gdx.Gdx;
import com.galenscovell.util.LevelParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
            if (velocityX < 0) {
                dir[0] = -1;
            } else {
                dir[0] = 1;
            }
        } else {
            if (velocityY < 0) {
                dir[1] = 1;
            } else {
                dir[1] = -1;
            }
        }
        checkMove(dir, cell);
    }

    private void checkMove(int[] dir, Cell cell) {
        Cell foundCell;
        List<Cell> coveredCells = new ArrayList<Cell>();
        boolean searching = true;
        int x = cell.getGridX();
        int y = cell.getGridY();
        int dx = dir[0] < 0 ? -1 : dir[0] > 0 ? 1 : 0;
        int dy = dir[1] < 0 ? -1 : dir[1] > 0 ? 1 : 0;

        while (!isOutOfBounds(x + dx, y + dy) && searching) {
            x += dx;
            y += dy;
            foundCell = getCell(x, y);
            if (foundCell != null) {
                if (foundCell.isNode()) {
                    searching = false;
                    foundCell.toggleSelected();
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
