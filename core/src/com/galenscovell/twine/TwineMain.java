package com.galenscovell.twine;

import com.galenscovell.screens.GameScreen;
import com.galenscovell.screens.MainMenuScreen;
import com.galenscovell.screens.OptionsScreen;
import com.galenscovell.screens.LoadingScreen;
import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.Game;

/**
 * TWINE MAIN
 * Primary entry into application.
 *
 * @author Galen Scovell
 */

public class TwineMain extends Game {
    public LoadingScreen loadingScreen;
    public MainMenuScreen mainMenuScreen;
    public OptionsScreen optionsScreen;
    public GameScreen gameScreen;

    @Override
    public void create () {
        this.loadingScreen = new LoadingScreen(this);
        this.mainMenuScreen = new MainMenuScreen(this);
        this.optionsScreen = new OptionsScreen(this);
        setScreen(loadingScreen);
    }

    public void newGame() {
        this.gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    @Override
    public void dispose () {
        loadingScreen.dispose();
        mainMenuScreen.dispose();
        optionsScreen.dispose();
        if (gameScreen != null) {
            gameScreen.dispose();
        }
        ResourceManager.dispose();
    }
}
