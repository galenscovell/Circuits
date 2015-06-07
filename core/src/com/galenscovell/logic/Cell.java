package com.galenscovell.logic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.galenscovell.util.ResourceManager;


public class Cell extends Actor {
    private int x, y;
    private int connections;
    private TextureRegion texture;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.texture = new TextureRegion(ResourceManager.atlas.findRegion("screwhead"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), 48, 48);
    }
}
