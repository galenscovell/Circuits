package com.galenscovell.screens;

import com.galenscovell.graphics.BackgroundAnimation;
import com.galenscovell.screens.components.DifficultyTable;
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
    private BackgroundAnimation bgAnim1, bgAnim2;

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
        final Table scrollTable = new Table();
        ScrollPane scroller = new ScrollPane(scrollTable);
        scroller.setFlingTime(0.5f);
        scroller.setFlickScroll(true);

        // Difficulty select buttons
        Table difficultyTable = new Table();
        TextButton easyButton = new TextButton("Easy", ResourceManager.mainButtonStyle);
        easyButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                changeDifficulty(scrollTable, 0);
            }
        });
        TextButton mediumButton = new TextButton("Medium", ResourceManager.mainButtonStyle);
        mediumButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                changeDifficulty(scrollTable, 1);
            }
        });
        TextButton hardButton = new TextButton("Hard", ResourceManager.mainButtonStyle);
        hardButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                changeDifficulty(scrollTable, 2);
            }
        });
        difficultyTable.add(easyButton).width(140).height(60);
        difficultyTable.add(mediumButton).width(140).height(60).padLeft(10).padRight(10);
        difficultyTable.add(hardButton).width(140).height(60);
        mainTable.add(difficultyTable).width(460).expand().fill();
        mainTable.row();

        mainTable.add(scroller).height(440).expand().fill();
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

        this.bgAnim1 = new BackgroundAnimation(0);
        this.bgAnim2 = new BackgroundAnimation(1);
    }

    private void changeDifficulty(Table container, int difficulty) {
        container.clear();
        DifficultyTable difficultyTable = new DifficultyTable(root, difficulty);
        container.add(difficultyTable);
        for (Actor actor : difficultyTable.getActors()) {
            Tween.set(actor, ActorAccessor.ALPHA).target(0).start(tweenManager);
            tweenManager.update(Gdx.graphics.getDeltaTime());
            Tween.to(actor, ActorAccessor.ALPHA, 0.3f)
                    .target(1.0f)
                    .delay(0.8f)
                    .start(tweenManager);
            tweenManager.update(Gdx.graphics.getDeltaTime());
        }
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


    Action toMainMenuScreen = new Action() {
        public boolean act(float delta) {
            root.setScreen(root.mainMenuScreen);
            return true;
        }
    };
}
