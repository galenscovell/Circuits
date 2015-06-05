
/**
 * RENDERER
 * Handles all display functionality.
 */

package com.galenscovell.logic;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.galenscovell.util.Constants;


public class Renderer {
    private Cell[][] cells;
    private SpriteBatch batch;

    public Renderer(Cell[][] cells) {
        this.cells = cells;
        this.batch = new SpriteBatch();
    }

    public void render() {
        batch.begin();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.draw(batch, Constants.CELLSIZE);
            }
        }
        batch.end();
    }

}
