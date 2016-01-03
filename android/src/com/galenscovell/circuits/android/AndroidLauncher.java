
package com.galenscovell.circuits.android;

import android.os.Bundle;

import com.galenscovell.circuits.CircuitsMain;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * ANDROID LAUNCHER
 * Sets up application for run on Android environment.
 *
 * @author Galen Scovell
 */

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new CircuitsMain(), config);
    }
}
