package com.galenscovell.screens;

import com.galenscovell.util.ResourceManager;
import com.galenscovell.twine.TwineMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * LOADING SCREEN
 * Displays loading animation asynchronously while game resources are loading.
 *
 * @author Galen Scovell
 */

public class LoadingScreen extends AbstractScreen {
    private Stage stage;

    public LoadingScreen(TwineMain root) {
        super(root);
    }

    protected void create() {
        this.stage = new Stage(new FitViewport(480, 800));

        Table splashMain = new Table();
        splashMain.setFillParent(true);

        Label splashLabel = new Label("Splash goes here", ResourceManager.buttonLabelStyle);
        splashLabel.setAlignment(Align.center);
        splashMain.add(splashLabel).expand().fill().center();

        stage.addActor(splashMain);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
        create();
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(Actions.sequence(Actions.fadeIn(1.0f), Actions.fadeOut(1.0f), toMainMenuScreen));
    }

    Action toMainMenuScreen = new Action() {
        public boolean act(float delta) {
            root.setScreen(root.mainMenuScreen);
            return true;
        }
    };
}