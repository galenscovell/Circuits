
/**
 * GAME SCREEN
 * Gameplay screen where primary application activity occurs. Handles game loop.
 */

package com.galenscovell.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.galenscovell.logic.Cell;
import com.galenscovell.logic.Grid;
import com.galenscovell.logic.Renderer;
import com.galenscovell.util.Constants;
import com.galenscovell.util.InputHandler;


public class GameScreen implements Screen {
    private Game root;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Grid grid;
    private GameLayout hud;
    private Renderer renderer;
    private Cell selectedCell;


    public GameScreen(Game root) {
        this.root = root;
        this.camera = new OrthographicCamera(Constants.SCREEN_X, Constants.SCREEN_Y);
        this.viewport = new FitViewport((float) Constants.SCREEN_X, (float) Constants.SCREEN_Y, camera);
        camera.setToOrtho(true, Constants.SCREEN_X, Constants.SCREEN_Y);
        this.hud = new GameLayout();
        createLevel();
    }

    public void selection(float x, float y) {
        this.selectedCell = grid.selectCell(x, y);
    }

    public void fling(int[] dir) {
        if (selectedCell != null) {
            grid.checkForCell(dir, selectedCell);
        }
    }

    private void createLevel() {
        this.grid = new Grid();
        this.renderer = new Renderer(grid.getGrid());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.24f, 0.34f, 0.43f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        hud.act(delta);
        hud.draw();
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        hud.getViewport().update(width, height);
        viewport.update(width, height);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new GestureDetector(new InputHandler(this)));
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
        hud.dispose();
    }
}
