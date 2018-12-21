package com.codework.game.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codework.game.WhacAHole;
import com.codework.game.actor.framework.SpecialImageActor;

public class SpecialImage extends SpecialImageActor {
    public SpecialImage(WhacAHole game) {
        super(game);
    }

    public SpecialImage(TextureRegion region, float x, float y, WhacAHole game) {
        super(region, x, y, game);
        checkScale(getGame().getWorldWidth(),getGame().getWorldHeight(),getWidth(),getHeight(),10);
    }
}
