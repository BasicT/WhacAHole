package com.codework.game.actor.framework;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codework.game.WhacAHole;
import com.codework.game.actor.root.ImageActor;

public class RunActor extends ImageActor {

    private WhacAHole game;
    //判断是否显示
    private boolean isRun = false;

    public RunActor(WhacAHole game) {
        this.game = game;
    }

    public RunActor(TextureRegion region, float x, float y, WhacAHole game) {
        super(region, x, y);
        this.game = game;
    }

    //增加条件判断，以启动动画
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isRun){super.draw(batch, parentAlpha);}
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    public WhacAHole getGame() {
        return game;
    }
}
