package com.codework.game.actor;

import com.codework.game.WhacAHole;
import com.codework.game.actor.base.BaseLabelActor;
import com.codework.game.util.GameState;

import java.text.DecimalFormat;

public class ScoreLabel extends BaseLabelActor {
    //定义格式显示
    private DecimalFormat format = new DecimalFormat("000");
    //时间计数器
    private float timer;
    //静态变量可以记录最高分
    public static int highScore;
    //当前分数
    private int score;
    //游戏状态
    private GameState gameState;
    //设置初始状态为end
    public ScoreLabel(CharSequence text, LabelStyle style, float x, float y, WhacAHole game) {
        super(text, style, x, y, game);
        gameState = GameState.end;
    }
    //得分累加
    public int addScore(){
        this.score += 100;
        return score;
    }

    @Override
    public void act(float delta) {
        //have_stay状态只显示最高分，忽略一下显示模式
        if (gameState == GameState.have_stay){
            setText("BEST   "+format.format(highScore));
        }
        //当前分数状态
        if (gameState == GameState.start){
            super.act(delta);
            //时间累减
            timer -= delta;
            //当时间大于1表示还有时间时，显示当前分数
            if (timer > 0.01){
                setText("SCORE : " + format.format(score));
            }else //没有时间将状态转为end
                {
                gameState = GameState.end;
                int temp;
                temp = score;//临时变量存储分数，与最高分比较，取较高者
                if (temp > highScore){
                    highScore = temp;
                }
                //初始化分数，归位计数器
                score = 0;
                setTimer(30);
            }
        }
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
