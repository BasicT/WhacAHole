package com.codework.game.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codework.game.WhacAHole;
import com.codework.game.actor.base.BaseRunActor;

public class AnimationActor extends BaseRunActor {

    public AnimationActor(TextureRegion start, TextureRegion have, TextureRegion hit,
                          float x, float y, int split, float duration, WhacAHole game) {
        super(start, have, hit, x, y, split, duration, game);
        checkScale(getGame().getWorldWidth(),getGame().getWorldHeight(),
                start.getRegionWidth(),start.getRegionHeight(),20);
    }
}
