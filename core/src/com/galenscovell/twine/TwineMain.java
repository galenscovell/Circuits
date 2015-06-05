
/**
 * TWINE MAIN
 * Provides main entry for application. Handles screen transitions.
 */

package com.galenscovell.twine;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.galenscovell.screens.GameScreen;
import com.galenscovell.screens.MainMenuScreen;
import com.galenscovell.util.ResourceManager;


public class TwineMain extends Game {
    public Screen gameScreen, mainMenuScreen;


    @Override
    public void create () {
        ResourceManager.load();
        this.mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);
    }

    public void newGame() {
        this.gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    @Override
    public void dispose () {
        ResourceManager.dispose();
        mainMenuScreen.dispose();
        if (gameScreen != null) {
            gameScreen.dispose();
        }
        Gdx.app.exit();
    }
}
