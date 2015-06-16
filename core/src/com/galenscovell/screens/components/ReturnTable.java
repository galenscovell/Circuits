package com.galenscovell.screens.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.galenscovell.screens.GameStage;
import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * RETURN TABLE
 * Container for displaying options for returning from game stage.
 *
 * @author Galen Scovell
 */

public class ReturnTable extends Table {
    private GameStage root;

    public ReturnTable(GameStage root) {
        this.root = root;
        create();
    }

    private void create() {
        this.setBackground(ResourceManager.barUp);
        Table returnButton = root.createButton(this, "exitLeft");
        returnButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                root.returnOperation();
            }
        });
        Label returnLabel = new Label("Return to level select?", ResourceManager.buttonLabelStyle);
        returnLabel.setAlignment(Align.center);
        Table closeButton = root.createButton(this, "cross");
        closeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                root.bottomTableOperation(2);
            }
        });
        this.add(returnButton).width(50).height(50).expand().fill().left();
        this.add(returnLabel).expand().fill();
        this.add(closeButton).width(50).height(50).expand().fill().right();
    }
}
