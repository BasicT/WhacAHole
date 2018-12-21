package com.codework.game.stage.basestage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.codework.game.WhacAHole;

public class BaseStage extends Stage {

    private WhacAHole game;

    /** 舞台是否可见（是否更新和绘制） */
    private boolean visible = true;

    public BaseStage(WhacAHole game, StretchViewport viewport){
        super(viewport);
        this.game = game;
    }

    public WhacAHole getGame() {
        return game;
    }

    public void setGame(WhacAHole game) {
        this.game = game;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
