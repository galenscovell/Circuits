package com.galenscovell.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.galenscovell.logic.Cell;
import com.galenscovell.logic.Grid;
import com.galenscovell.util.ResourceManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.List;


public class GameStage extends Stage {
    private Grid grid;

    public GameStage() {
        super(new FitViewport(480, 800));
        create();
    }

    public void setInput() {

    }

    public void selection(int x, int y) {

    }

    private void create() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.setDebug(true);


        // Top bar
        Table topTable = new Table();
        topTable.setBackground(ResourceManager.hudbarBG);
        mainTable.add(topTable).height(48).expand().fill().center();
        mainTable.row();

        // Main game board
        Table gameBoard = new Table();
        gameBoard.setDebug(true);
        gameBoard.setBackground(ResourceManager.boardBG);

        Table gridTable = buildBoard(9, 9);
        gameBoard.add(gridTable).center();
        mainTable.add(gameBoard).height(704).expand().fill().center();
        mainTable.row();


        // Bottom bar
        Table bottomTable = new Table();
        bottomTable.setBackground(ResourceManager.hudbarBG);
        mainTable.add(bottomTable).height(48).expand().fill().center();
        mainTable.row();

        this.addActor(mainTable);
    }

    private Table buildBoard(int rows, int columns) {
        Table gridTable = new Table();
        gridTable.setDebug(true);

        Cell[][] cells = new Cell[rows][columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                Cell cell = new Cell(x, y);
                cells[y][x] = cell;
                gridTable.add(cell).width(48).height(48);
            }
            gridTable.row();
        }
        this.grid = new Grid(cells);
        return gridTable;
    }

}

