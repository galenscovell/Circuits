
/**
 * CELL
 * Components of grid.
 */

package com.galenscovell.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.galenscovell.util.ResourceManager;


public class Cell {
    private int x, y, cellSize;
    private boolean occupied;
    private Sprite sprite;


    public Cell(int x, int y, int cellSize) {
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        this.sprite = new Sprite(ResourceManager.atlas.findRegion("empty"));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setOccupied() {
        this.occupied = true;
        this.sprite = new Sprite(ResourceManager.atlas.findRegion("node"));
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(sprite, x * cellSize, y * cellSize, cellSize, cellSize);
    }

    public String toString() {
        return "Cell at " + x + ", " + y + " is a node: " + occupied + ".";
    }
}
