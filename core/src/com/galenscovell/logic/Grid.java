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
        // Set grid cells from GameStage
        this.cells = cells;
    }

    public void loadLevel(int diff, int levelNumber) {
        // Parse new level layout and dole out values to grid cells
        String difficulty;
        if (diff == 0) {
            difficulty = "easy";
        } else if (diff == 1) {
            difficulty = "medium";
        } else {
            difficulty = "hard";
        }
        LevelParser parser = new LevelParser();
        int[][] values = parser.parseLevel(difficulty, levelNumber);

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                int gridX = cell.getGridX();
                int gridY = cell.getGridY();
                if (values[gridY][gridX] != 0) {
                    cell.setNode(values[gridY][gridX]);
                }
                cell.setup();
            }
        }
    }

    public void move(float diffX, float diffY, Cell cell) {
        // Determine move direction based on difference in original and released touch positions
        int dir;
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (diffX < 0) {
                dir = 2; // Left
            } else {
                dir = 3; // Right
            }
        } else {
            if (diffY > 0) {
                dir = 0; // Up
            } else {
                dir = 1; // Down
            }
        }
        checkMove(dir, cell);
    }

    private void checkMove(int dir, Cell cell) {
        // If node or move direction full, remove bridges in that direction
        if (cell.isFull() || cell.directionFull(dir)) {
            removeBridge(cell.getGridX(), cell.getGridY(), dir);
            cell.resetConnection(dir);
            return;
        }
        // Otherwise cast ray in direction, analyzing each hit cell along the way
        int dx = (dir == 2) ? -1 : (dir == 3) ? 1 : 0;
        int dy = (dir == 0) ? -1 : (dir == 1) ? 1 : 0;
        List<Cell> coveredCells = new ArrayList<Cell>();
        // If values hit bounds, exit
        while (!isOutOfBounds(cell.getGridX() + dx, cell.getGridY() + dy)) {
            Cell foundCell = getCell(cell.getGridX() + dx, cell.getGridY() + dy);
            // If hit cell is available node, create bridge and exit
            if (foundCell.isNode()) {
                if (!foundCell.isFull() && !foundCell.directionFull(getOppositeDirection(dir))) {
                    cell.addConnection(dir);
                    foundCell.addConnection(getOppositeDirection(dir));
                    for (Cell coveredCell : coveredCells) {
                        coveredCell.setBridge(dir);
                    }
                }
                return;
            // If hit cell is bridge and not overlapping direction, exit
            } else if (foundCell.isBridge() && !bridgeOverlap(foundCell, dir)) {
                return;
            // Otherwise add cell to list for later iteration of bridge creation
            } else {
                coveredCells.add(foundCell);
            }
            dx = (dx < 0) ? dx - 1 : (dx > 0) ? dx + 1 : 0;
            dy = (dy < 0) ? dy - 1 : (dy > 0) ? dy + 1 : 0;
        }
    }

    private boolean bridgeOverlap(Cell cell, int dir) {
        // Check if new bridge direction overlaps old bridge (for double bridge creation)
        return cell.getCreatedDirection() == dir || cell.getCreatedDirection() == getOppositeDirection(dir);
    }

    private int getOppositeDirection(int dir) {
        // Return opposing direction for handling node on other side of bridge
        if (dir == 0) {
            return 1;
        } else if (dir == 1) {
            return 0;
        } else if (dir == 2) {
            return 3;
        } else {
            return 2;
        }
    }

    private Cell getCell(int x, int y) {
        if (isOutOfBounds(x, y)) {
            return null;
        }
        return cells[y][x];
    }

    private void removeBridge(int x, int y, int dir) {
        // Cast ray in direction from node, removing all bridges
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
                found.resetConnection(getOppositeDirection(dir));
                return;
            } else if (found.isBridge()) {
                found.removeBridge();
            }
            dx = (dx < 0) ? dx - 1 : (dx > 0) ? dx + 1 : 0;
            dy = (dy < 0) ? dy - 1 : (dy > 0) ? dy + 1 : 0;
        }
    }

    private boolean isOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= 9 || y >= 9);
    }

    public boolean isComplete() {
        // Check that all nodes are full
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (!cell.isFull()) {
                    return false;
                }
            }
        }
        return true;
    }
}
