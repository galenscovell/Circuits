package com.galenscovell.logic;

import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.graphics.g2d.Batch;
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
    private boolean selected, active, node, twine;
    private TextureRegion texture;

    public Cell(int x, int y, Grid grid) {
        this.grid = grid;
        this.gridX = x;
        this.gridY = y;
        this.connections = new int[4];
        this.totalConnections = 0;
        this.texture = new TextureRegion(ResourceManager.atlas.findRegion("empty"));
        this.addListener(new ActorGestureListener() {
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleSelected();
                toggleActive();
            }

            public void fling(InputEvent event, float velocityX, float velocityY, int button) {
                if (active) {
                    move(velocityX, velocityY);
                    toggleSelected();
                    toggleActive();
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

    public int[] getConnections() {
        // Up, down, left, right
        return connections;
    }

    public void addConnection(int dir) {
        if (!isFull()) {
            if (directionFull(dir)) {
                resetConnection(dir);
            } else {
                connections[dir]++;
                totalConnections++;
            }
        }
        System.out.println("Node: " + maxConnections + ", " + totalConnections);
    }

    public void removeConnection(int dir) {
        connections[dir]--;
        totalConnections--;
    }

    public void resetConnection(int dir) {
        totalConnections -= connections[dir];
        connections[dir] = 0;
    }

    public boolean directionFull(int dir) {
        return connections[dir] == 2;
    }

    public boolean isFull() {
        return totalConnections == maxConnections;
    }

    private int totalConnections() {
        return totalConnections;
    }

    public void setNode(int maxConnections) {
        this.node = true;
        this.maxConnections = maxConnections;
        this.texture = new TextureRegion(ResourceManager.atlas.findRegion("node" + maxConnections));
    }

    public boolean isNode() {
        return node;
    }

    public void setTwine(int dir) {
        if (dir == 0) {
            if (isTwine()) {
                this.texture = new TextureRegion(ResourceManager.atlas.findRegion("twine_double_v"));
            } else {
                this.texture = new TextureRegion(ResourceManager.atlas.findRegion("twine_v"));
                this.twine = true;
                this.createdDirection = dir;
            }
        } else {
            if (isTwine()) {
                this.texture = new TextureRegion(ResourceManager.atlas.findRegion("twine_double_h"));
            } else {
                this.texture = new TextureRegion(ResourceManager.atlas.findRegion("twine_h"));
                this.twine = true;
                this.createdDirection = dir;
            }
        }
    }

    public int getCreatedDirection() {
        return createdDirection;
    }

    public void removeTwine() {
        this.twine = false;
        this.texture = new TextureRegion(ResourceManager.atlas.findRegion("empty"));
    }

    public boolean isTwine() {
        return twine;
    }

    public void toggleSelected() {
        this.selected = !selected;
    }

    public void toggleActive() {
        this.active = !active;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isNode() && isFull()) {
            batch.setColor(0.5f, 0.5f, 0.5f, 1);
            batch.draw(texture, getX(), getY(), 48, 64);
            batch.setColor(1, 1, 1, 1);
        } else {
            batch.draw(texture, getX(), getY(), 48, 64);
        }
    }
}
