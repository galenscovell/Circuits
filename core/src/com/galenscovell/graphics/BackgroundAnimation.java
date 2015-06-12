package com.galenscovell.graphics;

import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

/**
 * BACKGROUND ANIMATION
 * Keeps track of background sprite position and handles animation.
 *
 * @author Galen Scovell
 */

public class BackgroundAnimation {
    private Sprite sprite;
    private int x, y, direction;

    public BackgroundAnimation() {
        Random random = new Random();
        int choice = random.nextInt(4);
        if (choice == 0) {
            this.sprite = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("stripe_v_r")));
            this.x = -100;
            this.y = 0;
            this.direction = 0;
        } else if (choice == 1) {
            this.sprite = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("stripe_v_l")));
            this.x = 500;
            this.y = 0;
            this.direction = 1;
        } else if (choice == 2) {
            this.sprite = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("stripe_h_u")));
            this.x = 0;
            this.y = 820;
            this.direction = 2;
        } else {
            this.sprite = new Sprite(new TextureRegion(ResourceManager.atlas.findRegion("stripe_h_d")));
            this.x = 0;
            this.y = -100;
            this.direction = 3;
        }
    }

    public boolean isOffScreen(int direction) {
        if (direction == 0) {
            return x > 500;
        } else if (direction == 1) {
            return x < -100;
        } else if (direction == 2) {
            return y < -100;
        } else {
            return y > 820;
        }
    }

    public void animate(int direction) {
        if (direction == 0) {
            x++;
            if (isOffScreen(direction)) {
                x = -100;
            }
        } else if (direction == 1) {
            x--;
            if (isOffScreen(direction)) {
                x = 500;
            }
        } else if (direction == 2) {
            y--;
            if (isOffScreen(direction)) {
                y = 820;
            }
        } else {
            y++;
            if (isOffScreen(direction)) {
                y = -100;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        if (direction == 0 || direction == 1) {
            batch.draw(sprite, x, y, 100, 800);
        } else {
            batch.draw(sprite, x, y, 480, 100);
        }
        animate(direction);
    }
}