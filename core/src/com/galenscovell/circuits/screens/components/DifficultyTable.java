package com.galenscovell.circuits.screens.components;

import com.galenscovell.circuits.CircuitsMain;
import com.galenscovell.circuits.util.ResourceManager;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * DIFFICULTY TABLE
 * Container for scrollable level selection buttons.
 */

public class DifficultyTable extends Table {
    private CircuitsMain root;
    private List<Actor> actors;

    public DifficultyTable(CircuitsMain root, int difficulty) {
        this.root = root;
        create(difficulty);
    }

    private void create(int difficulty) {
        this.actors = new ArrayList<Actor>();
        String type;
        if (difficulty == 0) {
            type = "Easy";
        } else if (difficulty == 1) {
            type = "Moderate";
        } else {
            type = "Hard";
        }
        for (int y = 1; y <= 24; y += 3) {
            for (int x = y; x < y + 3; x++) {
                final int d = difficulty;
                final int l = x;
                TextButton levelButton = new TextButton(type + "\n#" + l, ResourceManager.levelButtonStyle);
                levelButton.addListener(new ClickListener() {
                    public void clicked(InputEvent e, float x, float y) {
                        root.newGame(d, l);
                    }
                });
                this.add(levelButton).width(140).height(90).pad(5);
                actors.add(levelButton);
            }
            this.row();
        }
    }

    public List<Actor> getActors() {
        return actors;
    }
}
