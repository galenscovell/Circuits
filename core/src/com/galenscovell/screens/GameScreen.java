package com.galenscovell.screens;

import com.galenscovell.graphics.BackgroundAnimation;
import com.galenscovell.circuits.CircuitsMain;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * GAME SCREEN
 * Screen containing primary game stage.
 *
 * @author Galen Scovell
 */

public class GameScreen extends AbstractScreen {
    private TweenManager tweenManager;
    private BackgroundAnimation bgAnim1, bgAnim2;

    public GameScreen(CircuitsMain root, int difficulty, int levelNumber) {
        super(root);
        create(difficulty, levelNumber);
//        GLProfiler.enable();
    }

    protected void create(int difficulty, int levelNumber) {
        this.tweenManager = new TweenManager();
        this.stage = new GameStage(this, root.spriteBatch, tweenManager, difficulty, levelNumber);
        this.bgAnim1 = new BackgroundAnimation(0);
        this.bgAnim2 = new BackgroundAnimation(1);
    }

    public void returnToLevelSelect() {
        root.setScreen(root.levelSelectScreen);
    }

    @Override
    public void render(float delta) {
        if (bgAnim1.isOffScreen()) {
            this.bgAnim1 = new BackgroundAnimation(0);
        }
        if (bgAnim2.isOffScreen()) {
            this.bgAnim2 = new BackgroundAnimation(1);
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        root.spriteBatch.begin();
        bgAnim1.draw(root.spriteBatch);
        bgAnim2.draw(root.spriteBatch);
        root.spriteBatch.end();
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
