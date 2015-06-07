package com.galenscovell.screens;

import com.galenscovell.logic.Grid;
import com.galenscovell.util.InputHandler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class GameScreen implements Screen {
    private Game root;
    private Grid grid;
    private Stage gameStage;
    private InputMultiplexer fullInput;


    public GameScreen(Game root) {
        this.root = root;
    }

    private void create() {
        this.gameStage = new GameStage();
        this.fullInput = new InputMultiplexer();
        fullInput.addProcessor(gameStage);
        fullInput.addProcessor(new GestureDetector(new InputHandler(this)));
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
        Gdx.input.setInputProcessor(fullInput);
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
