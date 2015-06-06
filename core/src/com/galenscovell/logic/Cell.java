
/**
 * CELL
 * Components of grid.
 */

package com.galenscovell.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.galenscovell.util.ResourceManager;


public class Cell {
    private int x, y, maxConnections;
    private int[] connections;
    private boolean occupied, selected, bridged;
    private Sprite sprite;


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.connections = null;
        this.sprite = new Sprite(ResourceManager.atlas.findRegion("empty"));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getConnections() {
        // [up, down, left, right]
        return connections;
    }

    public int sumConnections() {
        int total = 0;
        for (int val : connections) {
            total += val;
        }
        return total;
    }

    public void toggleSelected() {
        this.selected = selected ? false : true;
    }

    public void setBridged(int dx, int dy) {
        this.bridged = true;
        if (dx == 0) {
            this.sprite = new Sprite(ResourceManager.atlas.findRegion("twine_h_red"));
        } else {
            this.sprite = new Sprite(ResourceManager.atlas.findRegion("twine_v_red"));
        }
    }

    public void setOccupied() {
        this.occupied = true;
        this.sprite = new Sprite(ResourceManager.atlas.findRegion("screwhead"));
        this.connections = new int[4];
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isBridged() {
        return bridged;
    }

    public void draw(SpriteBatch batch, int cellSize) {
        if (selected) {
            batch.setColor(0.1f, 0.6f, 0.3f, 1.0f);
        }
        // x + 1 = forced horizontal margin (begins one cell over), y + 3 similar
        batch.draw(sprite, (x + 2) * cellSize, (y + 2) * cellSize, cellSize, cellSize);
        batch.setColor(1, 1, 1, 1);
    }
}
