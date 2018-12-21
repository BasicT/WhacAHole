package com.codework.game.actor;

import com.codework.game.WhacAHole;
import com.codework.game.actor.base.BaseLabelActor;
import com.codework.game.util.GameState;

import java.text.DecimalFormat;

public class TimeActor extends BaseLabelActor {
    //定义格式显示
    private DecimalFormat format = new DecimalFormat("00");
    //时间计数器
    private float timer;
    //游戏状态
    private GameState gameState;
    //初始化状态为end
    public TimeActor(CharSequence text, LabelStyle style, float x, float y, WhacAHole game) {
        super(text, style, x, y, game);
        gameState = GameState.end;
    }

    @Override
    public void act(float delta) {
        if (gameState == GameState.start){
            super.act(delta);
            //时间累减
            timer -= delta;
            //表示时间大于0，还有时间即显示时间
            if (timer > 0.01){
                setText("00 : " + format.format((int)timer));
            }else {
                gameState = GameState.end;
                setTimer(30);
            }
        }else {
            setText("00 : 00");//没有时间时显示00:00
        }

    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
