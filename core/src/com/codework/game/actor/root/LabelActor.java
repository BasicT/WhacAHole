package com.codework.game.actor.root;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelActor extends Label {

    public LabelActor(CharSequence text, LabelStyle style,float x,float y) {
        super(text, style);
        setX(x);
        setY(y);
    }
}
