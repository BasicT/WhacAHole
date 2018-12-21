package com.codework.game.actor.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codework.game.WhacAHole;
import com.codework.game.actor.root.ImageActor;

public class BaseImageActor extends ImageActor {

    private WhacAHole game;

    public BaseImageActor(WhacAHole game) {
        this.game = game;
    }

    public BaseImageActor(TextureRegion region, float x, float y, WhacAHole game) {
        super(region, x, y);
        setRegion(region);
        this.game = game;
    }

    public WhacAHole getGame() {
        return game;
    }

    public void setGame(WhacAHole game) {
        this.game = game;
    }
}
