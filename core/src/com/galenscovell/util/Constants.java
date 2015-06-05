
/**
 * Constants used throughout application.
 *
 */

package com.galenscovell.util;


public class Constants {
    public static final int SCREEN_X = 480;
    public static final int SCREEN_Y = 800;

    public static final int CELLSIZE = 32;
    public static final int GAME_X = SCREEN_X - (CELLSIZE * 2);
    public static final int GAME_Y = SCREEN_Y - (CELLSIZE * 4);

    public static final int COLUMNS = (GAME_X - (CELLSIZE * 2)) / CELLSIZE;
    public static final int ROWS = (GAME_Y - CELLSIZE) / CELLSIZE;
}
