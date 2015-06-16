package com.galenscovell.screens.components;

import com.galenscovell.screens.GameStage;
import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * SOLUTION TABLE
 * Container displaying result of solution checking.
 *
 * @author Galen Scovell
 */

public class SolutionTable extends Table {
    private GameStage root;

    public SolutionTable(GameStage root) {
        this.root = root;
        create();
    }

    private void create() {
        Label solveLabel;
        Table solvedButton;
        if (root.checkSolution()) {
            solveLabel = new Label("Correct! Proceed to next puzzle", ResourceManager.buttonLabelStyle);
            solvedButton = root.createButton(this, "checkmark");
            solvedButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    root.returnOperation();
                }
            });
        } else {
            solveLabel = new Label("Incorrect solution", ResourceManager.buttonLabelStyle);
            solvedButton = root.createButton(this, "cross");
        }
        solveLabel.setAlignment(Align.center);
        this.add(solveLabel).expand().fill();
        this.add(solvedButton).width(50).height(50).expand().fill().right();
    }
}
