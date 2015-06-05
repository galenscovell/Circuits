
/**
 * CELL
 * Components of grid.
 */

package com.galenscovell.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.galenscovell.util.ResourceManager;


public class Cell {
    private int x, y, connections;
    private boolean occupied, selected;
    private Sprite sprite;


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.connections = 0;
        this.sprite = new Sprite(ResourceManager.atlas.findRegion("empty"));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getConnections() {
        return connections;
    }

    public void incrementConnections() {
        connections++;
        if (connections == 3) {
            connections = 0;
        }
    }

    public void toggleSelected() {
        this.selected = selected ? false : true;
    }

    public void setOccupied() {
        this.occupied = true;
        this.sprite = new Sprite(ResourceManager.atlas.findRegion("node"));
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void draw(SpriteBatch batch, int cellSize) {
        if (selected) {
            batch.setColor(0.2f, 0.8f, 0.4f, 1.0f);
        }
        // x + 1 = forced horizontal margin (begins one cell over), y + 3 similar
        batch.draw(sprite, (x + 1) * cellSize, (y + 3) * cellSize, cellSize, cellSize);
        batch.setColor(1, 1, 1, 1);
    }
}
