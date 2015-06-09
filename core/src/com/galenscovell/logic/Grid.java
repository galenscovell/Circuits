package com.galenscovell.logic;

import com.galenscovell.util.LevelParser;

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
        if (cell.isFull()) {
            removeBridges(cell.getGridX(), cell.getGridY());
            cell.resetConnections();
            return;
        }
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
                    // Set twine orientation (vertical or horizontal)
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

    private Cell getCell(int x, int y) {
        if (isOutOfBounds(x, y)) {
            return null;
        }
        return cells[y][x];
    }

    private void removeBridges(int x, int y) {
        for (int dx = 1; !isOutOfBounds(x - dx, y); dx++) {
            Cell left = getCell(x - dx, y);
            if (left.isNode()) {
                left.removeConnection(3);
                break;
            } else if (left.isTwine()) {
                left.removeTwine();
            }
        }
        for (int dx = 1; !isOutOfBounds(x + dx, y); dx++) {
            Cell right = getCell(x + dx, y);
            if (right.isNode()) {
                right.removeConnection(2);
                break;
            } else if (right.isTwine()) {
                right.removeTwine();
            }
        }
        for (int dy = 1; !isOutOfBounds(x, y - dy); dy++) {
            Cell up = getCell(x, y - dy);
            if (up.isNode()) {
                up.removeConnection(1);
                break;
            } else if (up.isTwine()) {
                up.removeTwine();
            }
        }
        for (int dy = 1; !isOutOfBounds(x, y + dy); dy++) {
            Cell down = getCell(x, y + dy);
            if (down.isNode()) {
                down.removeConnection(0);
                break;
            } else if (down.isTwine()) {
                down.removeTwine();
            }
        }
    }

    private boolean isOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= 9 || y >= 9);
    }
}
