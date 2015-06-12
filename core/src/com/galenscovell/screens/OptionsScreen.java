package com.galenscovell.screens;

import com.galenscovell.graphics.BackgroundAnimation;
import com.galenscovell.tween.ActorAccessor;
import com.galenscovell.twine.TwineMain;
import com.galenscovell.util.ResourceManager;

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
 * OPTIONS SCREEN
 * Provides in-game settings to player: sfx, music and graphics.
 *
 * @author Galen Scovell
 */

public class OptionsScreen extends AbstractScreen {
    private TweenManager tweenManager;
    private BackgroundAnimation bgAnim;

    public OptionsScreen(TwineMain root) {
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
        Label titleLabel = new Label("Settings", ResourceManager.titleLabelStyle);
        titleLabel.setAlignment(Align.center);
        titleTable.add(titleLabel).expand().fill().center();
        mainTable.add(titleTable).width(400).height(160).expand().fill().center();
        mainTable.row();


        // Center table
        Table buttonTable = new Table();
        TextButton soundButton = new TextButton("SFX", ResourceManager.mainButtonStyle);
        soundButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // TODO: Implement sfx
            }
        });
        TextButton musicButton = new TextButton("Music", ResourceManager.mainButtonStyle);
        musicButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // TODO: Implement music
            }
        });
        TextButton returnButton = new TextButton("Return", ResourceManager.mainButtonStyle);
        returnButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                stage.getRoot().addAction(Actions.sequence(Actions.fadeOut(0.25f), toMainMenuScreen));
            }
        });
        buttonTable.add(soundButton).width(260).height(80).expand().fill().center();
        buttonTable.row();
        buttonTable.add(musicButton).width(260).height(80).expand().fill().center();
        buttonTable.row();
        buttonTable.add(returnButton).width(260).height(80).expand().fill().center();

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

        this.bgAnim = new BackgroundAnimation();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        root.spriteBatch.begin();
        bgAnim.draw(root.spriteBatch);
        root.spriteBatch.end();
        stage.act(delta);
        stage.draw();
        tweenManager.update(delta);
    }

    Action toMainMenuScreen = new Action() {
        public boolean act(float delta) {
            root.setScreen(root.mainMenuScreen);
            return true;
        }
    };
}
