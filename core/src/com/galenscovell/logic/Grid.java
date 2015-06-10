package com.galenscovell.logic;

import com.galenscovell.util.LevelParser;

import java.util.ArrayList;
import java.util.List;

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
        int[] move = new int[2];
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            move[0] = (velocityX < 0) ? -1 : 1;
        } else {
            move[1] = (velocityY < 0) ? 1 : -1;
        }
        int dir;
        if (move[0] > 0) {
            dir = 3;
        } else if (move[0] < 0) {
            dir = 2;
        } else if (move[1] > 0) {
            dir = 1;
        } else {
            dir = 0;
        }
        checkMove(dir, cell);
    }

    private void checkMove(int dir, Cell cell) {
        if (cell.isFull() || cell.directionFull(dir)) {
            removeBridge(cell.getGridX(), cell.getGridY(), dir);
            cell.resetConnection(dir);
            return;
        }
        int x = cell.getGridX();
        int y = cell.getGridY();
        int dx = (dir == 2) ? -1 : (dir == 3) ? 1 : 0;
        int dy = (dir == 0) ? -1 : (dir == 1) ? 1 : 0;

        List<Cell> coveredCells = new ArrayList<Cell>();
        while (!isOutOfBounds(x + dx, y + dy)) {
            Cell foundCell = getCell(x + dx, y + dy);
            if (foundCell.isNode()) {
                if (!foundCell.isFull() && !foundCell.directionFull(getOppositeDirection(dir))) {
                    cell.addConnection(dir);
                    foundCell.addConnection(getOppositeDirection(dir));
                    for (Cell coveredCell : coveredCells) {
                        // Set bridge orientation (vertical or horizontal)
                        if (dx == 0) {
                            coveredCell.setTwine(0);
                        } else {
                            coveredCell.setTwine(1);
                        }
                    }
                }
                return;
            } else if (foundCell.isTwine() && (!(foundCell.getCreatedDirection() == dir || foundCell.getCreatedDirection() == getOppositeDirection(dir)))) {
                return;
            } else {
                coveredCells.add(foundCell);
            }
            dx = (dx < 0) ? dx - 1 : (dx > 0) ? dx + 1 : 0;
            dy = (dy < 0) ? dy - 1 : (dy > 0) ? dy + 1 : 0;
        }
    }

    private int getOppositeDirection(int dir) {
        int oppositeDirection;
        if (dir == 0) {
            oppositeDirection = 1;
        } else if (dir == 1) {
            oppositeDirection = 0;
        } else if (dir == 2) {
            oppositeDirection = 3;
        } else {
            oppositeDirection = 2;
        }
        return oppositeDirection;
    }

    private Cell getCell(int x, int y) {
        if (isOutOfBounds(x, y)) {
            return null;
        }
        return cells[y][x];
    }

    private void removeBridge(int x, int y, int dir) {
        int dx = 0;
        int dy = 0;
        if (dir == 0) {
            dy--;
        } else if (dir == 1) {
            dy++;
        } else if (dir == 2) {
            dx--;
        } else {
            dx++;
        }
        while (!isOutOfBounds(x + dx, y + dy)) {
            Cell found = getCell(x + dx, y + dy);
            if (found.isNode()) {
                found.removeConnection(getOppositeDirection(dir));
                return;
            } else if (found.isTwine()) {
                found.removeTwine();
            }
            dx = (dx < 0) ? dx - 1 : (dx > 0) ? dx + 1 : 0;
            dy = (dy < 0) ? dy - 1 : (dy > 0) ? dy + 1 : 0;
        }
    }

    private boolean isOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= 9 || y >= 9);
    }
}
