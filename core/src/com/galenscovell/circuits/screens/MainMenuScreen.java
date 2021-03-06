package com.galenscovell.circuits.screens;

import com.galenscovell.circuits.graphics.BackgroundAnimation;
import com.galenscovell.circuits.graphics.tween.ActorAccessor;
import com.galenscovell.circuits.CircuitsMain;
import com.galenscovell.circuits.util.ResourceManager;

import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * MAINMENU SCREEN
 * Displays primary game menu to player.
 *
 * @author Galen Scovell
 */

public class MainMenuScreen extends AbstractScreen {
    private TweenManager tweenManager;
    private BackgroundAnimation bgAnim1, bgAnim2;

    public MainMenuScreen(CircuitsMain root) {
        super(root);
    }

    protected void create() {
        this.stage = new Stage(new FitViewport(480, 800), root.spriteBatch);

        // Overall root table
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.pad(10, 10, 10, 10);


        // Top table
        Table titleTable = new Table();
        Label titleLabel = new Label("Twine", ResourceManager.titleLabelStyle);
        titleLabel.setAlignment(Align.center);
        titleTable.add(titleLabel).expand().fill().center();
        mainTable.add(titleTable).width(400).height(160).expand().fill().center();
        mainTable.row();


        // Center table
        Table buttonTable = new Table();
        TextButton newGameButton = new TextButton("New Game", ResourceManager.mainButtonStyle);
        newGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                stage.getRoot().addAction(Actions.sequence(Actions.fadeOut(0.25f), toLevelSelectScreen));
            }
        });
        TextButton optionsButton = new TextButton("Options", ResourceManager.mainButtonStyle);
        optionsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                stage.getRoot().addAction(Actions.sequence(Actions.fadeOut(0.25f), toOptionsScreen));
            }
        });
        TextButton quitButton = new TextButton("Exit Twine", ResourceManager.mainButtonStyle);
        quitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                stage.getRoot().addAction(Actions.sequence(Actions.fadeOut(0.25f), quitGame));
            }
        });
        buttonTable.add(newGameButton).width(260).height(80).expand().fill().center();
        buttonTable.row();
        buttonTable.add(optionsButton).width(260).height(80).expand().fill().center();
        buttonTable.row();
        buttonTable.add(quitButton).width(260).height(80).expand().fill().center();

        mainTable.add(buttonTable).width(300).height(400).expand().fill().center();
        mainTable.row();


        // Bottom table
        Table endTable = new Table();
        Label detailLabel = new Label("Twine v0.1a \u00a9 2015, Galen Scovell", ResourceManager.detailLabelStyle);
        detailLabel.setAlignment(Align.bottom, Align.center);
        endTable.add(detailLabel).expand().fill().bottom().center();
        mainTable.add(endTable).width(300).height(60).expand().fill().center();


        stage.addActor(mainTable);

        this.tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class, new ActorAccessor());

        Tween.from(titleLabel, ActorAccessor.ALPHA, 0.5f)
                .target(0)
                .start(tweenManager);
        Tween.from(titleLabel, ActorAccessor.POS_Y, 0.75f)
                .target(100)
                .ease(Bounce.OUT)
                .start(tweenManager);
        Tween.from(buttonTable, ActorAccessor.ALPHA, 0.5f)
                .target(0)
                .start(tweenManager);
        tweenManager.update(Gdx.graphics.getDeltaTime());

        this.bgAnim1 = new BackgroundAnimation(0);
        this.bgAnim2 = new BackgroundAnimation(1);
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
    }

    Action toLevelSelectScreen = new Action() {
        public boolean act(float delta) {
            root.setScreen(root.levelSelectScreen);
            return true;
        }
    };

    Action toOptionsScreen = new Action() {
        public boolean act(float delta) {
            root.setScreen(root.optionsScreen);
            return true;
        }
    };

    Action quitGame = new Action() {
        public boolean act(float delta) {
            Gdx.app.exit();
            return true;
        }
    };
}
