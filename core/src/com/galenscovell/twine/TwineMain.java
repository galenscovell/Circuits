package com.galenscovell.twine;

import com.galenscovell.screens.GameScreen;
import com.galenscovell.screens.MainMenuScreen;
import com.galenscovell.screens.OptionsScreen;
import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.Game;


public class TwineMain extends Game {
    public GameScreen gameScreen;
    public MainMenuScreen mainMenuScreen;
    public OptionsScreen optionsScreen;


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
    }
}
