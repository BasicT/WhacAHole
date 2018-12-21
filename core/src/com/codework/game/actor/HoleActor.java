package com.codework.game.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codework.game.WhacAHole;
import com.codework.game.actor.base.BaseImageActor;


public class HoleActor extends BaseImageActor {

    public HoleActor(WhacAHole game) {
        super(game);
    }

    public HoleActor(TextureRegion region, float x, float y, WhacAHole game) {
        super(region, x, y, game);
        checkScale(getGame().getWorldWidth(),getGame().getWorldHeight(),region.getRegionWidth(),region.getRegionHeight(),0);

    }
}
