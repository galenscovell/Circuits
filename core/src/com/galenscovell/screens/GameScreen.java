package com.galenscovell.screens;

import com.galenscovell.twine.TwineMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.profiling.GLProfiler;

/**
 * GAME SCREEN
 * Screen containing main game stage.
 *
 * @author Galen Scovell
 */

public class GameScreen extends AbstractScreen {

    public GameScreen(TwineMain root) {
        super(root);
//        GLProfiler.enable();
    }

    protected void create() {
        this.stage = new GameStage();
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
//        System.out.println("Calls: " + GLProfiler.drawCalls + ", Bindings: " + GLProfiler.textureBindings);
//        System.out.println("Draw Calls: " + GLProfiler.drawCalls);
//        GLProfiler.reset();
    }
}
