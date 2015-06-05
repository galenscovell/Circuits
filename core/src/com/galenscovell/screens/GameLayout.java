
/**
 * HUD BAR
 * Displays navigation options and other features (time tracker, etc.)
 */

package com.galenscovell.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.galenscovell.util.Constants;
import com.galenscovell.util.ResourceManager;


public class GameLayout extends Stage {

    public GameLayout() {
        create();
    }

    private void create() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        Table topTable = new Table();
        topTable.setBackground(ResourceManager.hudbarBG);
        mainTable.add(topTable).width(Constants.SCREEN_X).height(Constants.CELLSIZE).expand().fill().top();
        mainTable.row();

        Table gameBoard = new Table();
        gameBoard.setBackground(ResourceManager.boardBG);
        mainTable.add(gameBoard).width(Constants.GAME_X).height(Constants.GAME_Y).expand().fill().center().padBottom(Constants.CELLSIZE);

        this.addActor(mainTable);
    }

}
