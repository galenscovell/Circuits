
/**
 * RESOURCE MANAGER
 * Handles loading of game assets.
 */

package com.galenscovell.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;


public class ResourceManager {
    public static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/textures.pack"));
    public static NinePatchDrawable hudbarBG;
    public static TiledDrawable boardBG;

    public static void load() {
        hudbarBG = new NinePatchDrawable(atlas.createPatch("hudbar"));
        boardBG = new TiledDrawable(atlas.findRegion("wood"));
    }

    public static void dispose() {
        atlas.dispose();
    }
}
