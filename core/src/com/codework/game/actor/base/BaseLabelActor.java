package com.codework.game.actor.base;

import com.codework.game.WhacAHole;
import com.codework.game.actor.root.LabelActor;

public class BaseLabelActor extends LabelActor {

    private WhacAHole game;

    public BaseLabelActor(CharSequence text, LabelStyle style, float x, float y,WhacAHole game) {
        super(text, style, x, y);
        this.game = game;
    }

    public WhacAHole getGame() {
        return game;
    }

    public void setGame(WhacAHole game) {
        this.game = game;
    }
}
