package com.galenscovell.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * RESOURCE MANAGER
 * Handles loading of game assets.
 *
 * @author Galen Scovell
 */

public class ResourceManager {
    public static AssetManager assetManager;
    public static TextureAtlas atlas;

    public static BitmapFont smallFont, mediumFont, largeFont;
    public static Label.LabelStyle titleLabelStyle;
    public static Label.LabelStyle buttonLabelStyle;
    public static Label.LabelStyle detailLabelStyle;
    public static NinePatchDrawable hudbarBG;
    public static NinePatchDrawable boardBG;
    public static TextButton.TextButtonStyle mainButtonStyle;

    public static void create() {
        assetManager = new AssetManager();
        load();
    }

    private static void load() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("ui/kenpixel_blocks.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        smallFont = fontGenerator.generateFont(parameter);
        parameter.size = 24;
        mediumFont = fontGenerator.generateFont(parameter);
        parameter.size = 64;
        largeFont = fontGenerator.generateFont(parameter);
        fontGenerator.dispose();

        detailLabelStyle = new Label.LabelStyle(smallFont, Color.GRAY);
        buttonLabelStyle = new Label.LabelStyle(mediumFont, Color.WHITE);
        titleLabelStyle = new Label.LabelStyle(largeFont, Color.WHITE);

        assetManager.load("textures/textures.pack", TextureAtlas.class);
    }

    public static void done() {
        atlas = assetManager.get("textures/textures.pack", TextureAtlas.class);

        hudbarBG = new NinePatchDrawable(atlas.createPatch("hudbar"));
        boardBG = new NinePatchDrawable(atlas.createPatch("board"));

        mainButtonStyle = new TextButton.TextButtonStyle(hudbarBG, hudbarBG, hudbarBG, mediumFont);
    }

    public static void dispose() {
        assetManager.dispose();
    }
}
