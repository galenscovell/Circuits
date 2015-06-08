package com.galenscovell.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * GAME SCREEN
 * Handles screen events for main game, handles game loop for game stage.
 *
 * @author Galen Scovell
 */

public class GameScreen implements Screen {
    private Game root;
    private Stage gameStage;


    public GameScreen(Game root) {
        this.root = root;
    }

    private void create() {
        this.gameStage = new GameStage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStage.act(delta);
        gameStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height);
    }

    @Override
    public void show() {
        create();
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        gameStage.dispose();
    }
}
