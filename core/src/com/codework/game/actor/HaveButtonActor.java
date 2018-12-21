package com.codework.game.actor;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.codework.game.WhacAHole;
import com.codework.game.actor.root.ButtonActor;

public class HaveButtonActor extends ButtonActor {

    public HaveButtonActor(Drawable imageUp, float x, float y, WhacAHole game) {
        super(imageUp, x, y,game);
        checkScale(getGame().getWorldWidth(),getGame().getWorldHeight(),
                getWidth(),getHeight(),10);

    }
}
