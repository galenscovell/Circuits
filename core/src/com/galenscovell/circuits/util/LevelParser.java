package com.galenscovell.circuits.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * LEVEL PARSER
 * Takes in .txt file and creates 2D array of values for level creation.
 *
 * @author Galen Scovell
 */

public class LevelParser {

    public int[][] parseLevel(String difficulty, int levelNumber) {
        int[][] values = new int[9][9];
        FileHandle file = Gdx.files.internal("levels/" + difficulty + ".txt");
        BufferedReader reader = new BufferedReader(file.reader());
        try {
            String line = reader.readLine();
            while (line != null && !line.equals(difficulty + "#" + levelNumber)) {
                line = reader.readLine();
            }
            for (int y = 0; y < 9; y++) {
                String[] row = reader.readLine().split(" ");
                for (int x = 0; x < 9; x++) {
                    values[y][x] = Integer.parseInt(row[x]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return values;
    }

}
