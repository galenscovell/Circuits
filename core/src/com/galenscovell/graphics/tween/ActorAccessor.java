package com.galenscovell.graphics.tween;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * ACTOR ACCESSOR
 * Accessor for Actor class, used by Universal Tween Engine.
 *
 * @author Galen Scovell
 */

public class ActorAccessor implements TweenAccessor<Actor> {
    public static final int ROTATE = 0;
    public static final int POS_Y = 1;
    public static final int ALPHA = 2;

    @Override
    public int getValues(Actor target, int tweenType, float[] returnValues) {
        switch(tweenType) {
            case ROTATE:
                returnValues[0] = target.getRotation();
                return 1;
            case POS_Y:
                returnValues[0] = target.getY();
                return 2;
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 3;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Actor target, int tweenType, float[] newValues) {
        switch(tweenType) {
            case ROTATE:
                target.setRotation(newValues[0]);
                break;
            case POS_Y:
                target.setY(newValues[0]);
                break;
            case ALPHA:
                target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
                break;
            default:
                assert false;
        }
    }
}
