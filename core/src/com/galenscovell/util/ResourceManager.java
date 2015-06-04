
/**
 * RESOURCE MANAGER
 * Handles loading of game assets.
 */

package com.galenscovell.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class ResourceManager {
    public static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/textures.pack"));


    public static void load() {

    }

    public static void dispose() {
        atlas.dispose();
    }
}
