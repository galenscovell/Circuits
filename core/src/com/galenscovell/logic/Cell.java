package com.galenscovell.logic;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Cell extends Actor {
    private int x, y;
    private int connections;
    private boolean selected;
    private TextureRegion texture;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.texture = new TextureRegion(ResourceManager.atlas.findRegion("screwhead"));
        this.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println(getX() + ", " + getY());
                toggleSelected();
                return true;
            }
        });
    }

    public void toggleSelected() {
        selected = selected ? false : true;
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
