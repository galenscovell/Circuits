package com.galenscovell.twine;

import com.galenscovell.screens.GameScreen;
import com.galenscovell.screens.LevelSelectScreen;
import com.galenscovell.screens.LoadingScreen;
import com.galenscovell.screens.MainMenuScreen;
import com.galenscovell.screens.OptionsScreen;
import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * TWINE MAIN
 * Primary entry into application. Contains all screens and only spriteBatch.
 *
 * @author Galen Scovell
 */

public class TwineMain extends Game {
    public SpriteBatch spriteBatch;
    public LoadingScreen loadingScreen;
    public MainMenuScreen mainMenuScreen;
    public OptionsScreen optionsScreen;
    public LevelSelectScreen levelSelectScreen;
    public GameScreen gameScreen;

    @Override
    public void create () {
        this.spriteBatch = new SpriteBatch();
        this.loadingScreen = new LoadingScreen(this);
        this.mainMenuScreen = new MainMenuScreen(this);
        this.optionsScreen = new OptionsScreen(this);
        this.levelSelectScreen = new LevelSelectScreen(this);
        setScreen(loadingScreen);
    }

    public void newGame(int difficulty, int levelNumber) {
        this.gameScreen = new GameScreen(this, difficulty, levelNumber);
        setScreen(gameScreen);
    }

    @Override
    public void dispose () {
        loadingScreen.dispose();
        mainMenuScreen.dispose();
        optionsScreen.dispose();
        levelSelectScreen.dispose();
        if (gameScreen != null) {
            gameScreen.dispose();
        }
        ResourceManager.dispose();
    }
}
