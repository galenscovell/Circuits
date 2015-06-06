
/**
 * RESOURCE MANAGER
 * Handles loading of game assets.
 */

package com.galenscovell.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;


public class ResourceManager {
    public static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/textures.pack"));
    public static Label.LabelStyle titleLabelStyle;
    public static Label.LabelStyle buttonLabelStyle;
    public static Label.LabelStyle detailLabelStyle;
    public static NinePatchDrawable hudbarBG;
    public static NinePatchDrawable boardBG;
    public static TextButton.TextButtonStyle mainButtonStyle;

    public static void load() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("ui/kenpixel_blocks.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont smallFont = fontGenerator.generateFont(parameter);
        parameter.size = 24;
        BitmapFont mediumFont = fontGenerator.generateFont(parameter);
        parameter.size = 64;
        BitmapFont largeFont = fontGenerator.generateFont(parameter);
        fontGenerator.dispose();

        detailLabelStyle = new Label.LabelStyle(smallFont, Color.GRAY);
        buttonLabelStyle = new Label.LabelStyle(mediumFont, Color.WHITE);
        titleLabelStyle = new Label.LabelStyle(largeFont, Color.WHITE);

        hudbarBG = new NinePatchDrawable(atlas.createPatch("hudbar"));
        boardBG = new NinePatchDrawable(atlas.createPatch("board"));

        mainButtonStyle = new TextButton.TextButtonStyle(hudbarBG, hudbarBG, hudbarBG, mediumFont);
    }

    public static void dispose() {
        atlas.dispose();
    }
}
