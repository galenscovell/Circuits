
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


public class HudBar extends Stage {

    public HudBar() {
        create();
    }

    private void create() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        Table topTable = new Table();
        topTable.setBackground(ResourceManager.hudbarBG);
        mainTable.add(topTable).width(Constants.SCREEN_X).height(Constants.CELLSIZE).expand().fill().top();

        this.addActor(mainTable);
    }

}
