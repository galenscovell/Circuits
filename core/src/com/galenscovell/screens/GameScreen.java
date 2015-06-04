
/**
 * GAME SCREEN
 * Gameplay screen where primary application activity occurs. Handles game loop.
 */

package com.galenscovell.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import com.galenscovell.logic.Grid;
import com.galenscovell.logic.Renderer;


public class GameScreen implements Screen {
    private Game root;
    private Renderer renderer;


    public GameScreen(Game root) {
        this.root = root;
        Grid grid = new Grid();
        this.renderer = new Renderer(grid.getGrid());
    }

    @Override
    public void render(float delta) {
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
