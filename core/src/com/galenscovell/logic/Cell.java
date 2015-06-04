
/**
 * CELL
 * Components of grid.
 */

package com.galenscovell.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.galenscovell.util.ResourceManager;


public class Cell {
    private int x, y;
    private Sprite sprite = new Sprite(ResourceManager.atlas.findRegion("circle"));


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(SpriteBatch batch, int cellSize) {
        batch.draw(sprite, x * cellSize, y * cellSize, cellSize, cellSize);
    }
}
