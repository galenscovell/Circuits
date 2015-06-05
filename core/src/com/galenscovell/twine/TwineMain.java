
/**
 * TWINE MAIN
 * Provides main entry for application. Handles screen transitions.
 */

package com.galenscovell.twine;

import com.badlogic.gdx.Game;

import com.galenscovell.screens.GameScreen;
import com.galenscovell.util.ResourceManager;


public class TwineMain extends Game {
    public GameScreen gameScreen;


    @Override
    public void create () {
        ResourceManager.load();
        this.gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    @Override
    public void dispose () {
        gameScreen.dispose();
        ResourceManager.dispose();
    }
}
