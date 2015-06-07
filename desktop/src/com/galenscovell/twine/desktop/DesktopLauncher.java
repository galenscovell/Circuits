
/**
 * Desktop launcher for application.
 */

package com.galenscovell.twine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.galenscovell.twine.TwineMain;


public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 480;
        config.height = 800;
        config.title = "Twine";
        config.resizable = true;
        new LwjglApplication(new TwineMain(), config);
    }
}
