package com.codework.game.stage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.codework.game.WhacAHole;
import com.codework.game.actor.ScoreLabel;
import com.codework.game.actor.base.BaseImageActor;
import com.codework.game.stage.basestage.BaseStage;
import com.codework.game.util.GameState;

public class GameOverStage extends BaseStage {
    //背景
    private BaseImageActor bg;
    //restart按钮
    private ImageButton restart;
    //最高分数
    private ScoreLabel highScore;
    public GameOverStage(WhacAHole game, StretchViewport viewport) {
        super(game, viewport);
        //初始化
        init();
        //设置按钮响应事件
        setBtnListener();
    }

    private void init(){
        bg = new BaseImageActor(getGame().getBgRegion(),
                (getWidth()-getGame().getBgRegion().getRegionWidth())/2,
                (getHeight()-getGame().getBgRegion().getRegionHeight())/2,getGame());
        addActor(bg);

        restart = new ImageButton(
                new TextureRegionDrawable(getGame().getUpBtnRegion()),
                new TextureRegionDrawable(getGame().getDownBtnRegion()));
        restart.setPosition((getWidth()-getGame().getBgRegion().getRegionWidth())/2+100,
                (getHeight()-getGame().getBgRegion().getRegionHeight())/2+100);
        addActor(restart);

        highScore = new ScoreLabel("",getGame().getStyle(),(getWidth()-getGame().getBgRegion().getRegionWidth())/2+70,
                (getHeight()-getGame().getBgRegion().getRegionHeight())/2+550,getGame());
        highScore.setGameState(GameState.have_stay);
        addActor(highScore);
    }

    private void setBtnListener(){
        restart.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                //重新开始游戏
                getGame().getMainGame().restartReadyGame();
                super.touchUp(event, x, y, pointer, button);
                return true;
            }
        });
    }
}
