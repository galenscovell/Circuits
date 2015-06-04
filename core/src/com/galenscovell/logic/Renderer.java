
/**
 * RENDERER
 * Handles all display functionality.
 */

package com.galenscovell.logic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.galenscovell.util.Constants;

import java.util.List;


public class Renderer {
    private List<Cell> cells;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private int cellSize;

    public Renderer(List<Cell> cells) {
        this.cells = cells;
        this.camera = new OrthographicCamera(Constants.SCREEN_X, Constants.SCREEN_Y);
        this.viewport = new FitViewport((float) Constants.SCREEN_X, (float) Constants.SCREEN_Y, camera);
        camera.setToOrtho(true, Constants.SCREEN_X, Constants.SCREEN_Y);
        this.batch = new SpriteBatch();
        this.cellSize = Constants.CELLSIZE;
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (Cell cell : cells) {
            cell.draw(batch, cellSize);
        }
        batch.end();
    }

}
