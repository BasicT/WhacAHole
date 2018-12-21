package com.codework.game.actor.framework;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codework.game.WhacAHole;
import com.codework.game.actor.root.ImageActor;

public class SpecialImageActor extends ImageActor {

    private WhacAHole game;

    public SpecialImageActor(WhacAHole game) {
        this.game = game;
    }

    public SpecialImageActor(TextureRegion region, float x, float y, WhacAHole game) {
        super(region, x, y);
        //设置纹理区域内容，否则无法显示
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
