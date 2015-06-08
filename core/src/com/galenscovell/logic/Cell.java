package com.galenscovell.logic;

import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Cell extends Actor {
    private Grid grid;
    private int gridX, gridY;
    private int connections;
    private boolean selected, active, node, twine;
    private TextureRegion texture;

    public Cell(int x, int y, Grid grid) {
        this.grid = grid;
        this.gridX = x;
        this.gridY = y;
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

    public void setNode() {
        this.node = true;
        texture = new TextureRegion(ResourceManager.atlas.findRegion("screwhead"));
    }

    public boolean isNode() {
        return node;
    }

    public void setTwine(int dir) {
        this.twine = true;
        if (dir == 0) {
            texture = new TextureRegion(ResourceManager.atlas.findRegion("twine_v"));
        } else {
            texture = new TextureRegion(ResourceManager.atlas.findRegion("twine_h"));
        }
    }

    public boolean isTwine() {
        return twine;
    }

    public void toggleSelected() {
        this.selected = selected ? false : true;
    }

    public void toggleActive() {
        this.active = active ? false : true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (selected) {
            batch.setColor(0.2f, 0.6f, 0.2f, 1);
        }
        batch.draw(texture, getX(), getY(), 48, 48);
        batch.setColor(1, 1, 1, 1);
    }
}
