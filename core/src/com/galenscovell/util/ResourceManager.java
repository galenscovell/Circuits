package com.galenscovell.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * RESOURCE MANAGER
 * Handles loading of game assets.
 *
 * @author Galen Scovell
 */

public class ResourceManager {
    public static AssetManager assetManager;
    public static TextureAtlas atlas;

    public static Label.LabelStyle titleLabelStyle;
    public static Label.LabelStyle buttonLabelStyle;
    public static Label.LabelStyle detailLabelStyle;
    public static NinePatchDrawable hudbarBG;
    public static NinePatchDrawable boardBG;
    public static TextureRegionDrawable skewedBoardBG;
    public static TextButton.TextButtonStyle mainButtonStyle;

    public static void create() {
        assetManager = new AssetManager();
        load();
    }

    private static void load() {
        assetManager.load("textures/textures.pack", TextureAtlas.class);

        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter smallParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        smallParams.fontFileName = "ui/kenpixel_blocks.ttf";
        smallParams.fontParameters.size = 12;
        assetManager.load("smallFont.ttf", BitmapFont.class, smallParams);

        FreetypeFontLoader.FreeTypeFontLoaderParameter mediumParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        mediumParams.fontFileName = "ui/kenpixel_blocks.ttf";
        mediumParams.fontParameters.size = 24;
        assetManager.load("mediumFont.ttf", BitmapFont.class, mediumParams);

        FreetypeFontLoader.FreeTypeFontLoaderParameter largeParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        largeParams.fontFileName = "ui/kenpixel_blocks.ttf";
        largeParams.fontParameters.size = 64;
        assetManager.load("largeFont.ttf", BitmapFont.class, largeParams);
    }

    public static void done() {
        atlas = assetManager.get("textures/textures.pack", TextureAtlas.class);

        detailLabelStyle = new Label.LabelStyle(assetManager.get("smallFont.ttf", BitmapFont.class), Color.GRAY);
        buttonLabelStyle = new Label.LabelStyle(assetManager.get("mediumFont.ttf", BitmapFont.class), Color.WHITE);
        titleLabelStyle = new Label.LabelStyle(assetManager.get("largeFont.ttf", BitmapFont.class), Color.WHITE);

        hudbarBG = new NinePatchDrawable(atlas.createPatch("hudbar"));
        boardBG = new NinePatchDrawable(atlas.createPatch("board"));
        skewedBoardBG = new TextureRegionDrawable(new TextureRegion(ResourceManager.atlas.findRegion("TwineBGskewed")));

        mainButtonStyle = new TextButton.TextButtonStyle(hudbarBG, hudbarBG, hudbarBG, assetManager.get("mediumFont.ttf", BitmapFont.class));
    }

    public static void dispose() {
        assetManager.dispose();
        atlas.dispose();
    }
}
