package com.galenscovell.twine;

import com.galenscovell.screens.GameScreen;
import com.galenscovell.screens.MainMenuScreen;
import com.galenscovell.screens.OptionsScreen;
import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;


public class TwineMain extends Game {
    public Screen gameScreen, mainMenuScreen, optionsScreen;


    @Override
    public void create () {
        ResourceManager.load();
        this.mainMenuScreen = new MainMenuScreen(this);
        this.optionsScreen = new OptionsScreen(this);
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
        optionsScreen.dispose();
        if (gameScreen != null) {
            gameScreen.dispose();
        }
    }
}
