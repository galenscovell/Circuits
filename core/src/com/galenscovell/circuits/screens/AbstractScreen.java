package com.galenscovell.circuits.screens;

import com.galenscovell.circuits.CircuitsMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * ABSTRACT SCREEN
 * Basic screen functionality common to all application screens.
 *
 * @author Galen Scovell
 */

public class AbstractScreen implements Screen {
    protected CircuitsMain root;
    protected Stage stage;

    public AbstractScreen(CircuitsMain root) {
        this.root = root;
    }

    protected void create() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (stage != null) {
            stage.getViewport().update(width, height);
        }
    }

    @Override
    public void show() {
        create();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        if (stage != null) {
            stage.dispose();
        }
    }
}
