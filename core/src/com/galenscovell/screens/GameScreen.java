package com.galenscovell.screens;

import com.galenscovell.twine.TwineMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * GAME SCREEN
 * Screen containing main game stage.
 *
 * @author Galen Scovell
 */

public class GameScreen extends AbstractScreen {

    public GameScreen(TwineMain root) {
        super(root);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    protected void create() {
        this.stage = new GameStage();
    }
}
