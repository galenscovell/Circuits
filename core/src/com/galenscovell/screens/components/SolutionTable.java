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
        this.setBackground(ResourceManager.barUp);
        if (root.checkSolution()) {
            Table correctButton = root.createButton(this, "checkmark");
            Label solveLabel = new Label("Correct!", ResourceManager.buttonLabelStyle);
            Table nextButton = root.createButton(this, "exitRight");
            nextButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    root.returnOperation();
                }
            });
            solveLabel.setAlignment(Align.center);
            this.add(correctButton).width(50).height(50).expand().fill().left();
            this.add(solveLabel).expand().fill();
            this.add(nextButton).width(50).height(50).expand().fill().right();

        } else {
            Label solveLabel = new Label("Incorrect solution", ResourceManager.buttonLabelStyle);
            Table closeButton = root.createButton(this, "cross");
            closeButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    root.bottomTableOperation(2);
                }
            });
            solveLabel.setAlignment(Align.center);
            this.add(solveLabel).expand().fill();
            this.add(closeButton).width(50).height(50).expand().fill().right();
        }
    }
}
