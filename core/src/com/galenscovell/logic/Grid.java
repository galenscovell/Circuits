
/**
 * GRID
 * Responsible for grid construction.
 */

package com.galenscovell.logic;

import com.galenscovell.util.Constants;

import java.util.ArrayList;
import java.util.List;


public class Grid {
    private List<Cell> cells;
    private int rows, columns;


    public Grid() {
        this.cells = new ArrayList<Cell>();
        this.rows = Constants.GRID_Y;
        this.columns = Constants.GRID_X;
        construct();
    }

    public List<Cell> getGrid() {
        return cells;
    }

    private void construct() {
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                cells.add(new Cell(x, y));
            }
        }
    }
}
