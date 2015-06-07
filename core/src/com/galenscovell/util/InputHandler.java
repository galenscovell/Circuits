package com.galenscovell.util;

import com.galenscovell.screens.GameScreen;

import com.badlogic.gdx.input.GestureDetector;


public class InputHandler extends GestureDetector.GestureAdapter {
    private GameScreen game;

    public InputHandler(GameScreen game) {
        this.game = game;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        int[] dir = new int[2];
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX < 0) {
                dir[0] = -1;
            } else {
                dir[0] = 1;
            }
        } else {
            if (velocityY < 0) {
                dir[1] = -1;
            } else {
                dir[1] = 1;
            }
        }
        // game.fling(dir);
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean zoom (float initialDistance, float endDistance){
        return false;
    }
}
