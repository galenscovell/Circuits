
/**
 * MAIN MENU SCREEN
 * Application entry screen displaying broad game options to player.
 */

package com.galenscovell.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import com.galenscovell.twine.TwineMain;
import com.galenscovell.util.Constants;
import com.galenscovell.util.ResourceManager;


public class MainMenuScreen implements Screen {
    private TwineMain root;
    private Stage stage;


    public MainMenuScreen(TwineMain root) {
        this.root = root;
        create();
    }

    private void create() {
        this.stage = new Stage();

        // Overall root table
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.pad(10, 10, 10, 10);


        // Top table
        Table titleTable = new Table();
        Label titleLabel = new Label("TWINE", ResourceManager.titleLabelStyle);
        titleLabel.setAlignment(Align.center);
        titleTable.add(titleLabel).expand().fill().center();
        mainTable.add(titleTable).width(400).height(160).expand().fill().center();
        mainTable.row();


        // Center table
        Table buttonTable = new Table();
        TextButton newGameButton = new TextButton("New Game", ResourceManager.mainButtonStyle);
        newGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                stage.getRoot().addAction(Actions.sequence(Actions.fadeOut(0.5f), toGameScreen));
            }
        });
        buttonTable.add(newGameButton).width(200).height(80).expand().fill().center();
        buttonTable.row();

        TextButton optionsButton = new TextButton("Options", ResourceManager.mainButtonStyle);
        optionsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        buttonTable.add(optionsButton).width(200).height(80).expand().fill().center();
        buttonTable.row();

        TextButton quitButton = new TextButton("Exit Twine", ResourceManager.mainButtonStyle);
        quitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                stage.getRoot().addAction(Actions.sequence(Actions.fadeOut(0.5f), quitGame));
            }
        });
        buttonTable.add(quitButton).width(200).height(80).expand().fill().center();

        mainTable.add(buttonTable).width(300).height(400).expand().fill().center();
        mainTable.row();


        // Bottom table
        Table endTable = new Table();
        Label detailLabel = new Label("Twine v0.1a \u00a9 2015, Galen Scovell", ResourceManager.detailLabelStyle);
        detailLabel.setAlignment(Align.bottom, Align.center);
        endTable.add(detailLabel).expand().fill().bottom().center();
        mainTable.add(endTable).width(300).height(60).expand().fill().center();

        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(Actions.fadeIn(0.5f));
        Gdx.input.setInputProcessor(stage);
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
        stage.dispose();
    }

    Action toGameScreen = new Action() {
        public boolean act(float delta) {
            root.newGame();
            return true;
        }
    };

    Action toOptionsScreen = new Action() {
        public boolean act(float delta) {
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
