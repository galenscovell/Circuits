package com.galenscovell.screens;

import com.galenscovell.tween.ActorAccessor;
import com.galenscovell.util.ResourceManager;
import com.galenscovell.twine.TwineMain;

import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * LEVELSELECT SCREEN
 * Displays available difficulties and levels to player.
 *
 * @author Galen Scovell
 */

public class LevelSelectScreen extends AbstractScreen {
    private TweenManager tweenManager;

    public LevelSelectScreen(TwineMain root) {
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
        Label titleLabel = new Label("Level\nSelect", ResourceManager.titleLabelStyle);
        titleLabel.setAlignment(Align.center);
        titleTable.add(titleLabel).expand().fill().center();
        mainTable.add(titleTable).width(400).height(160).expand().fill().center();
        mainTable.row();


        // Center table (scrollable)
        Table scrollTable = new Table();
        loadLevels(scrollTable);
        ScrollPane scroller = new ScrollPane(scrollTable);
        scroller.setFlingTime(0.5f);
        scroller.setFlickScroll(true);
        mainTable.add(scroller).height(500).expand().fill();
        mainTable.row();


        // bottom table
        Table buttonTable = new Table();
        TextButton returnButton = new TextButton("Return", ResourceManager.mainButtonStyle);
        returnButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                stage.getRoot().addAction(Actions.sequence(Actions.fadeOut(0.25f), toMainMenuScreen));
            }
        });
        buttonTable.add(returnButton).width(260).height(80).expand().fill().center();

        mainTable.add(buttonTable).width(300).height(100).expand().fill().center();
        mainTable.row();

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
    }

    private void loadLevels(Table container) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 10; x++) {
                TextButton levelButton = new TextButton("Difficulty: " + y + "\n#" + x, ResourceManager.levelButtonStyle);
                final int row = y;
                final int column = x;
                levelButton.addListener(new ClickListener() {
                    public void clicked(InputEvent e, float x, float y) {
                        root.newGame(row, column);
                    }
                });
                container.add(levelButton).width(120).height(90).pad(5);
            }
            container.row();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
