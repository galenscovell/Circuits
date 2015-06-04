
/**
 * TWINE MAIN
 * Provides main entry for application. Handles screen transitions.
 */

package com.galenscovell.twine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL20;

import com.galenscovell.screens.GameScreen;


public class TwineMain extends Game {
    public GameScreen gameScreen;


    @Override
    public void create () {
        this.gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    @Override
    public void dispose () {

    }
}
