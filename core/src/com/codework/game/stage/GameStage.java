package com.codework.game.stage;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.codework.game.WhacAHole;
import com.codework.game.actor.AnimationActor;
import com.codework.game.actor.HoleActor;
import com.codework.game.actor.ScoreLabel;
import com.codework.game.actor.TimeActor;
import com.codework.game.res.Coordinate;
import com.codework.game.stage.basestage.BaseStage;
import com.codework.game.util.GameState;

/**
 * 游戏主舞台
 * 主要的游戏逻辑所在处
 */


public class GameStage extends BaseStage {
    /** 坐标系 */
    private Coordinate holeCoordinate;
    /** 地洞 */
    private HoleActor[] holeActors;
    /** 地鼠 */
    private AnimationActor[] actors;
    /** 地鼠被打静止图片 */
    private HoleActor[] hitActors;
    /** 开始按键 */
    private ImageButton start;
    /** 时间标签 */
    private TimeActor timeLabel;
    /** 分数标签 */
    private ScoreLabel scoreLabel;//当前分数
    /** 游戏状态 */
    private GameState gameState;
    /** 时间计步器 */
    private float stateTime;

    public GameStage(WhacAHole game, StretchViewport viewport) {
        super(game, viewport);
        //初始化变量
        init();
    }

    private void init(){
        //初始化游戏状态为end
        gameState = GameState.end;
        //初始化坐标系。  以地洞作为参考系，因为图片大小一样，所以可以通用
        holeCoordinate = new Coordinate(getGame(),getGame().getHoleRegion());
        //初始化地洞
        holeActors = new HoleActor[9];
        for (int i = 0; i < 9; i++){
            holeActors[i] = new HoleActor(getGame().getHoleRegion(),holeCoordinate.getVetor(i).x,holeCoordinate.getVetor(i).y,getGame());
            addActor(holeActors[i]);
        }
        //初始化地鼠
        actors = new AnimationActor[9];
        for (int i = 0; i < 9; i++){
            actors[i] = new AnimationActor(getGame().getHeadRegion(),getGame().getHaveRegion(),getGame().getHitRegion(),
                    holeCoordinate.getVetor(i).x,holeCoordinate.getVetor(i).y+20,40,0.025f,getGame());
            addActor(actors[i]);
            //添加按键地鼠用于响应“打地鼠”行为
            addActor(actors[i].haveBtn);
            //添加被打地鼠图片显示被打效果
            addActor(actors[i].hitImg);
        }
        //初始化按键并设置按键位置
        start = new ImageButton(
                new TextureRegionDrawable(getGame().getUpBtnRegion()),
                new TextureRegionDrawable(getGame().getDownBtnRegion())
        );
        start.setX(getWidth()/5-120);
        start.setY(getHeight()/5*3+250);
        addActor(start);
        //设置按键响应
        addBtnListener();
        //设置按键更新随机抽取“地鼠”演员
        addBtnAct();
        //初始化时间标签
        timeLabel = new TimeActor("00: 00",getGame().getStyle(),getWidth()/5*3+50,getHeight()/5*3+250,getGame());
        timeLabel.setTimer(30f);
        addActor(timeLabel);
        //初始化分数标签
        scoreLabel = new ScoreLabel("SCORE : 000",getGame().getStyle(),getWidth()/5-120,getHeight()/5*3+250,getGame());
        scoreLabel.setScore(0);//设置初始分数为0
        scoreLabel.setTimer(30f);//设置计数时间为30f************（此处可以为变量以增加游戏难度）**********
        scoreLabel.setVisible(false);
        addActor(scoreLabel);
    }

    private void addBtnListener(){
        //添加按键的点击事件
        start.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                //将按键设置为开始状态
                gameState = GameState.start;
                //将时间标签设置为开始
                timeLabel.setGameState(GameState.start);
                //将分数标签设置为开始
                scoreLabel.setGameState(GameState.start);
                start.setVisible(false);
                scoreLabel.setVisible(true);
                super.touchUp(event, x, y, pointer, button);
                return true;
            }
        });
    }

    private void addBtnAct(){
        //添加按键的更新事件
        start.addAction(new Action() {
            //延迟值temp
            int temp = 1;
            @Override
            public boolean act(float delta) {
                //如果按键为start状态则开始更新状态
                if (gameState == GameState.start || gameState == GameState.have_stay){
                    stateTime += delta;
                    //在have_stay状态下再随机抽取
                    if (gameState != GameState.have_stay){
                        temp++;//延迟值随delta增加
                        //100取余数1，表示每加100次通过1次，大约时间为1秒钟
                        if (temp % 100 == 1) {
                            //i为0到8的随机数，用于生成随机地洞
                            int i = MathUtils.random(0,8);
                            //判断地洞是否为end状态可以避免正在显示地鼠的地洞重复被选择
                            if (actors[i].getGameState() == GameState.end){
                                //start状态表示“地鼠”开始更新状态
                                actors[i].setGameState(GameState.start);
                                //isRun状态表示是否显示地洞
                                actors[i].setRun(true);
                            }
                        }
                        //因动画显示有延迟，所以stateTime=26表示30秒之后，转为不在抽取
                        if (stateTime > 28 && gameState != GameState.have_stay) {
                            gameState = GameState.have_stay;
                        }
                    }
                    //33秒后转为停止，显示完成游戏画面
                    if (stateTime > 33){
                        gameState = GameState.end;
                        //显示游戏结束画面
                        getGame().getMainGame().showGameOverStage();
                        stateTime = 0;
                    }
                }
                return false;
            }
        });
    }

    public void ready(){
        //将按键设置为开始状态
        gameState = GameState.start;
        //将时间标签设置为开始
        timeLabel.setGameState(GameState.start);
        //将分数标签设置为开始
        scoreLabel.setGameState(GameState.start);
    }

    public ScoreLabel getScoreLabel() {
        return scoreLabel;
    }//用于“地鼠”演员获取分数标签以改变分数
}
