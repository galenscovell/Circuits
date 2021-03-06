package com.galenscovell.circuits.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
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

    public static Label.LabelStyle titleLabelStyle;
    public static Label.LabelStyle buttonLabelStyle;
    public static Label.LabelStyle detailLabelStyle;
    public static NinePatchDrawable buttonUp;
    public static NinePatchDrawable buttonDown;
    public static NinePatchDrawable buttonSelected;
    public static NinePatchDrawable buttonUnselected;
    public static TextButton.TextButtonStyle mainButtonStyle;
    public static TextButton.TextButtonStyle levelButtonStyle;
    public static TextButton.TextButtonStyle selectionButtonStyle;

    public static Sprite selectionPointer;
    public static Sprite litbg;

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
        smallParams.fontFileName = "fonts/spincycle_ot.otf";
        smallParams.fontParameters.size = 16;
        assetManager.load("smallFont.ttf", BitmapFont.class, smallParams);

        FreetypeFontLoader.FreeTypeFontLoaderParameter mediumParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        mediumParams.fontFileName = "fonts/spincycle_ot.otf";
        mediumParams.fontParameters.size = 32;
        assetManager.load("mediumFont.ttf", BitmapFont.class, mediumParams);

        FreetypeFontLoader.FreeTypeFontLoaderParameter largeParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        largeParams.fontFileName = "fonts/spincycle_ot.otf";
        largeParams.fontParameters.size = 72;
        assetManager.load("largeFont.ttf", BitmapFont.class, largeParams);
    }

    public static void done() {
        atlas = assetManager.get("textures/textures.pack", TextureAtlas.class);

        detailLabelStyle = new Label.LabelStyle(assetManager.get("smallFont.ttf", BitmapFont.class), Color.GRAY);
        buttonLabelStyle = new Label.LabelStyle(assetManager.get("mediumFont.ttf", BitmapFont.class), Color.WHITE);
        titleLabelStyle = new Label.LabelStyle(assetManager.get("largeFont.ttf", BitmapFont.class), Color.WHITE);

        buttonUp = new NinePatchDrawable(atlas.createPatch("buttonup"));
        buttonDown = new NinePatchDrawable(atlas.createPatch("buttondown"));
        buttonSelected = new NinePatchDrawable(atlas.createPatch("selectboxchecked"));
        buttonUnselected = new NinePatchDrawable(atlas.createPatch("selectboxunchecked"));

        mainButtonStyle = new TextButton.TextButtonStyle(buttonUp, buttonDown, buttonUp, assetManager.get("mediumFont.ttf", BitmapFont.class));
        levelButtonStyle = new TextButton.TextButtonStyle(buttonUp, buttonDown, buttonUp, assetManager.get("smallFont.ttf", BitmapFont.class));
        selectionButtonStyle = new TextButton.TextButtonStyle(buttonUnselected, buttonUnselected, buttonSelected, assetManager.get("mediumFont.ttf", BitmapFont.class));

        selectionPointer = new Sprite(atlas.findRegion("pointer"));
        litbg = new Sprite(atlas.findRegion("litBG"));
    }

    public static void dispose() {
        assetManager.dispose();
        atlas.dispose();
    }
}
