package com.galenscovell.circuits.screens;

import com.galenscovell.circuits.logic.Cell;
import com.galenscovell.circuits.logic.Grid;
import com.galenscovell.circuits.graphics.tween.ActorAccessor;
import com.galenscovell.circuits.util.ResourceManager;

import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * GAME STAGE
 * Stage holding all actors for the game as well as navigation bars.
 *
 * @author Galen Scovell
 */

public class GameStage extends Stage {
    private GameScreen root;
    private Grid grid;
    private TweenManager tweenManager;

    public GameStage(GameScreen root, SpriteBatch spriteBatch, TweenManager tweenManager, int difficulty, int levelNumber) {
        super(new FitViewport(480, 800), spriteBatch);
        this.root = root;
        this.tweenManager = tweenManager;
        create();
        grid.loadLevel(difficulty, levelNumber);
    }

    public void returnOperation() {
        root.returnToLevelSelect();
    }

    private void create() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);


        // Top bar
        Table topTable = new Table();
        createMenu(topTable);
        mainTable.add(topTable).width(380).height(120).expand().fill().top().padBottom(32);
        mainTable.row();


        // Main game board
        Table gameBoard = new Table();
        Table gridTable = buildBoard(9, 9);
        gridTable.background(ResourceManager.buttonUp);
        gameBoard.add(gridTable).center();
        mainTable.add(gameBoard).height(480).expand().fill().center().padBottom(32);
        mainTable.row();


        // Bottom bar
        Table bottomTable = new Table();
        Table solveButton = createButton(bottomTable, "solveButton");
        bottomTable.add(solveButton).width(80).height(80).expand().fill().center();
        mainTable.add(bottomTable).height(120).expand().fill().top();

        this.addActor(mainTable);

        Tween.registerAccessor(Actor.class, new ActorAccessor());
        Tween.from(mainTable, ActorAccessor.ALPHA, 1.0f)
                .target(0)
                .start(tweenManager);
        Tween.from(gridTable, ActorAccessor.POS_Y, 1.0f)
                .target(100)
                .ease(Bounce.OUT)
                .start(tweenManager);
        tweenManager.update(Gdx.graphics.getDeltaTime());
    }

    public void createMenu(Table container) {
        Table soundButton = createButton(container, "soundOnButton");
        soundButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        Table menuButton = createButton(container, "menuButton");
        menuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        Table returnButton = createButton(container, "returnButton");
        returnButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                root.returnToLevelSelect();
            }
        });
        container.add(soundButton).width(80).height(80).expand().fill();
        container.add(menuButton).width(80).height(80).expand().fill();
        container.add(returnButton).width(80).height(80).expand().fill();

        Tween.set(soundButton, ActorAccessor.POS_X).target(0).start(tweenManager);
        Tween.set(returnButton, ActorAccessor.POS_X).target(300).start(tweenManager);
        tweenManager.update(Gdx.graphics.getDeltaTime());
        Tween.from(soundButton, ActorAccessor.ALPHA, 0.4f)
                .target(0)
                .start(tweenManager);
        Tween.from(returnButton, ActorAccessor.ALPHA, 0.4f)
                .target(0)
                .start(tweenManager);
        Tween.from(soundButton, ActorAccessor.POS_X, 1.2f)
                .target(150)
                .ease(Bounce.OUT)
                .start(tweenManager);
        Tween.from(returnButton, ActorAccessor.POS_X, 1.2f)
                .target(150)
                .ease(Bounce.OUT)
                .start(tweenManager);
        tweenManager.update(Gdx.graphics.getDeltaTime());
    }

    private Table buildBoard(int rows, int columns) {
        Table gridTable = new Table();
        this.grid = new Grid();

        Cell[][] cells = new Cell[rows][columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                Cell cell = new Cell(x, y, grid);
                cells[y][x] = cell;
                gridTable.add(cell).width(48).height(48);
            }
            gridTable.row();
        }
        grid.updateCells(cells);
        return gridTable;
    }

    public Table createButton(Table table, String name) {
        Table button = new Table();
        button.setTouchable(Touchable.enabled);
        button.setBackground(new TextureRegionDrawable(ResourceManager.atlas.findRegion(name)));
        return button;
    }

    public boolean checkSolution() {
        return grid.isComplete();
    }
}

