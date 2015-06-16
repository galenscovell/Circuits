package com.galenscovell.logic;

import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * CELL
 * Each cell is an Actor with its own inputlistener, state, texture and connections.
 *
 * @author Galen Scovell
 */

public class Cell extends Actor {
    private Grid grid;
    private int gridX, gridY;
    private int maxConnections, totalConnections, createdDirection;
    private int[] connections;
    private boolean selected, active, node, bridge;
    private Sprite texture;
    private Sprite baseSprite, singleSprite, doubleSprite;

    public Cell(int x, int y, Grid grid) {
        this.grid = grid;
        this.gridX = x;
        this.gridY = y;
        this.connections = new int[4];
        this.totalConnections = 0;
        this.texture = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("empty")));
        this.addListener(new ActorGestureListener() {
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isNode()) {
                    selected = true;
                    active = true;
                }
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (isNode()) {
                    selected = false;
                    active = false;
                }
            }

            public void fling(InputEvent event, float velocityX, float velocityY, int button) {
                if (active) {
                    move(velocityX, velocityY);
                    selected = false;
                    active = false;
                }
            }
        });
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
                    this.texture = singleSprite;
                } else {
                    this.texture = doubleSprite;
                }
            }
        }
    }

    public void resetConnection(int dir) {
        totalConnections -= connections[dir];
        connections[dir] = 0;
        if (dir == 1) {
            this.texture = baseSprite;
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
        this.baseSprite = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("node" + maxConnections + "_empty")));
        this.singleSprite = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("node" + maxConnections + "_single")));
        this.doubleSprite = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("node" + maxConnections + "_double")));
        this.texture = baseSprite;
    }

    public boolean isNode() {
        return node;
    }

    public void setBridge(int dir) {
        if (dir == 0 || dir == 1) {
            if (isBridge()) {
                this.texture = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("bridge_double_v")));
            } else {
                this.texture = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("bridge_v")));
                this.bridge = true;
                this.createdDirection = dir;
            }
        } else {
            if (isBridge()) {
                this.texture = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("bridge_double_h")));
            } else {
                this.texture = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("bridge_h")));
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
        this.texture = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("empty")));
    }

    public boolean isBridge() {
        return bridge;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (selected) {
            batch.draw(ResourceManager.selectionPointer, getX(), getY() + 48, 48, 48);
        } else if (isNode() && isFull()) {
            // TODO: Some sort of notice for player that node is full
        }
        batch.draw(texture, getX(), getY(), 48, 48);
    }
}
