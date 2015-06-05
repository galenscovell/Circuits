
/**
 * RENDERER
 * Handles all display functionality.
 */

package com.galenscovell.logic;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.galenscovell.util.Constants;
import com.galenscovell.util.ResourceManager;


public class Renderer {
    private Cell[][] cells;
    private int cellSize;
    private SpriteBatch batch;


    public Renderer(Cell[][] cells) {
        this.cells = cells;
        this.cellSize = Constants.CELLSIZE;
        this.batch = new SpriteBatch();
    }

    public void render() {
        batch.begin();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.draw(batch, cellSize);
            }
        }
        batch.end();
    }

}
