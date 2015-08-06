package com.galenscovell.logic;

import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * CELL
 * Each cell can be either a 'node' or 'empty' (with possibility to become a bridge).
 * Depending on designation different sprites, methods and fields will be used.
 *
 * @author Galen Scovell
 */

public class Cell extends Actor {
    private Grid grid;
    private int gridX, gridY;
    private Sprite currentSprite;
    private Sprite[] sprites;
    private boolean node, bridge;

    // 'Node' specific
    private int maxConnections, totalConnections;
    private int[] connections;
    private boolean selected, active;
    private float touchedX, touchedY;

    // 'Bridge' specific
    private int createdDirection;


    public Cell(int x, int y, Grid grid) {
        this.grid = grid;
        this.gridX = x;
        this.gridY = y;
    }

    public void setup() {
        if (isNode()) {
            this.connections = new int[4];
            this.totalConnections = 0;
            this.sprites = new Sprite[3];
            this.addListener(new ActorGestureListener() {
                public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    selected = true;
                    active = true;
                    touchedX = x;
                    touchedY = y;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    move(x - touchedX, y - touchedY);
                    selected = false;
                    active = false;
                }
            });
            sprites[0] = new Sprite(ResourceManager.atlas.findRegion("node" + maxConnections + "_empty"));
            sprites[1] = new Sprite(ResourceManager.atlas.findRegion("node" + maxConnections + "_single"));
            sprites[2] = new Sprite(ResourceManager.atlas.findRegion("node" + maxConnections + "_double"));
            this.currentSprite = sprites[0];
        } else {
            this.sprites = new Sprite[5];
            sprites[0] = new Sprite(ResourceManager.atlas.findRegion("empty"));
            sprites[1] = new Sprite(ResourceManager.atlas.findRegion("bridge_v"));
            sprites[2] = new Sprite(ResourceManager.atlas.findRegion("bridge_h"));
            sprites[3] = new Sprite(ResourceManager.atlas.findRegion("bridge_double_v"));
            sprites[4] = new Sprite(ResourceManager.atlas.findRegion("bridge_double_h"));
            this.currentSprite = sprites[0];
        }
    }

    public void move(float dx, float dy) {
        grid.move(dx, dy, this);
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public boolean hasConnection(int dir) {
        return connections[dir] != 0;
    }

    public void addConnection(int dir) {
        if (isFull()) {
            return;
        }
        if (directionFull(dir)) {
            resetConnection(dir);
        } else {
            connections[dir]++;
            totalConnections++;
            if (dir == 1) {
                if (connections[dir] == 1) {
                    this.currentSprite = sprites[1];
                } else {
                    this.currentSprite = sprites[2];
                }
            }
        }
    }

    public void resetConnection(int dir) {
        totalConnections -= connections[dir];
        connections[dir] = 0;
        if (dir == 1) {
            this.currentSprite = sprites[0];
        }
    }

    public boolean directionFull(int dir) {
        return connections[dir] == 2;
    }

    public boolean isFull() {
        return totalConnections == maxConnections;
    }

    public void setNode(int maxConnections) {
        this.node = true;
        this.maxConnections = maxConnections;
    }

    public boolean isNode() {
        return node;
    }

    public void setBridge(int dir) {
        if (dir == 0 || dir == 1) {
            if (isBridge()) {
                this.currentSprite = sprites[3];
            } else {
                this.currentSprite = sprites[1];
                this.bridge = true;
                this.createdDirection = dir;
            }
        } else {
            if (isBridge()) {
                this.currentSprite = sprites[4];
            } else {
                this.currentSprite = sprites[2];
                this.bridge = true;
                this.createdDirection = dir;
            }
        }
    }

    public int getCreatedDirection() {
        return createdDirection;
    }

    public void removeBridge() {
        this.bridge = false;
        this.currentSprite = sprites[0];
    }

    public boolean isBridge() {
        return bridge;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isNode() && isFull()) {
            batch.draw(ResourceManager.litbg, getX() - 2, getY() - 2, 52, 52);
        }
        if (selected) {
            batch.draw(ResourceManager.selectionPointer, getX(), getY() + 48, 48, 48);
            batch.setColor(0.0f, 1, 0.4f, 1);
            batch.draw(currentSprite, getX(), getY(), 48, 48);
            batch.setColor(1, 1, 1, 1);
        } else {
            batch.draw(currentSprite, getX(), getY(), 48, 48);
        }
    }
}
