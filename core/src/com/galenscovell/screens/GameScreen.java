package com.galenscovell.screens;

import com.galenscovell.twine.TwineMain;

import aurelienribon.tweenengine.TweenManager;

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
    private TweenManager tweenManager;

    public GameScreen(TwineMain root, int difficulty, int levelNumber) {
        super(root);
        create(difficulty, levelNumber);
//        GLProfiler.enable();
    }

    protected void create(int difficulty, int levelNumber) {
        this.tweenManager = new TweenManager();
        this.stage = new GameStage(this, root.spriteBatch, tweenManager, difficulty, levelNumber);
    }

    public void returnToLevelSelect() {
        root.setScreen(root.levelSelectScreen);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        tweenManager.update(delta);
//        System.out.println("Calls: " + GLProfiler.drawCalls + ", Bindings: " + GLProfiler.textureBindings);
//        System.out.println("Draw Calls: " + GLProfiler.drawCalls);
//        GLProfiler.reset();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
}
