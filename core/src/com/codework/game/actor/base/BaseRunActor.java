package com.codework.game.actor.base;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.codework.game.WhacAHole;
import com.codework.game.actor.HaveButtonActor;
import com.codework.game.actor.SpecialImage;
import com.codework.game.actor.framework.RunActor;
import com.codework.game.util.GameState;
/**
 * 地鼠演员
 * 负责地鼠的3个状态
 * 首先显示头部
 * 数秒后显示全身
 * 如果
 *     被点击则显示被打状态
 *     没被点击则显示下降状态
 */
public class BaseRunActor extends RunActor {
    /**
     * 纹理区域，同上
     */
    private TextureRegion start;
    private TextureRegion have;
    private TextureRegion hit;
    /**
     * 动画区域
     * 开始动画对应头部，全身动画对应全身上、下2个状态，打击动画对应打击
     */
    private Animation startAnimation;
    private Animation haveAnimation;
    private Animation hitAnimation;
    /**
     * 动画纹理，与动画变量对应，keyRegion用于显示动画
     */
    private TextureRegion[] startFrames;
    private TextureRegion[] haveFrames;
    private TextureRegion[] hitFrames;
    private TextureRegion keyRegion;
    //动画分片，越大约流畅
    private int split;
    //动画间隔，越小越快
    private float duration;
    /** 时间步 delta 的累加值 */
    private float stateTime;
    //全身按键，用于响应“打地鼠”行为
    public HaveButtonActor haveBtn;
    //被打图片，用于停留显示被打状态
    public SpecialImage hitImg;
    //public Image hitImg;
    //游戏状态
    private GameState gameState;

    /**
     * 构造函数
     * 因为地鼠对象确定所以所有图片都可以在此处理
     * @param start 开始的头部图片
     * @param have  全身图片
     * @param hit   被打图片
     * @param x     x坐标
     * @param y     y坐标
     * @param split 图片纹理分片数，越大越流畅
     * @param duration  动画间隔，越小越快
     */
    public BaseRunActor(TextureRegion start, TextureRegion have, TextureRegion hit, float x, float y, int split, float duration, WhacAHole game) {
        super(start, x, y,game);
        //this.x = x;
        /*
        因为动画设置的原因
            设置分30片，那么Y轴可以不偏移，但是向下动画会有失真效果
            设置分40片，那么Y轴需要偏移20左右的像素，那么这里传回y坐标需要修正回相应的像素
                否则按钮和图片标签显示会有出入
         */
        //this.y = y;
        this.split = split;
        this.duration =duration;
        this.start = start;
        this.have = have;
        this.hit = hit;
        this.show();
    }

    private void show(){
        //游戏初始状态并且能被随机函数选择
        gameState = GameState.end;
        //开始头部动画初始化
        startFrames = new TextureRegion[split];
        for (int i = 0; i < split; i++){
            startFrames[i] = new TextureRegion(start.getTexture(),
                    start.getTexture().getWidth(),start.getTexture().getHeight()/split*(i+1));
        }
        startAnimation = new Animation(duration,startFrames);
        startAnimation.setPlayMode(com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL);
        //全身动画初始化
        haveFrames = new TextureRegion[split];
        for (int i = 0; i < split; i++){
            haveFrames[i] = new TextureRegion(have.getTexture(),
                    have.getTexture().getWidth(),have.getTexture().getHeight()/split*(i+1));
        }
        haveAnimation = new Animation(duration,haveFrames);
        //打击动画初始化
        hitFrames = new TextureRegion[split];
        for (int i = 0; i < split; i++){
            hitFrames[i] = new TextureRegion(hit.getTexture(),
                    hit.getTexture().getWidth(),hit.getTexture().getHeight()/split*(i+1));
        }
        hitAnimation = new Animation(duration,hitFrames);
        hitAnimation.setPlayMode(Animation.PlayMode.REVERSED);
        //全身可以“打地鼠”按钮初始化
        haveBtn = new HaveButtonActor(new TextureRegionDrawable(getGame().getHaveBtnRegion()),getX(),getY(),getGame());
        //Gdx.app.debug("baserun",""+haveBtn.getX()+" . " +haveBtn.getY());
        haveBtn.setVisible(false);
        //添加点击事件，点击后游戏状态设置为hit_stay，表示被打图片标签停留
        haveBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                // TODO Auto-generated method stub
                //点击后将状态转为hit_stay，执行被打后的动画
                gameState = GameState.hit_stay;
                //点击后加分
                getGame().getMainGame().getGameStage().getScoreLabel().addScore();
                return true;
            }
        });
        //被打图片标签初始化
        hitImg = new SpecialImage(getGame().getHitImgRegion(),getX(),getY(),getGame());
        hitImg.setVisible(false);
    }

    /**
     * 动画主流程
     *                        开始显示头部动画
     *                                ↓
     *                     数秒后显示上升全身动画
     *                              ↓
     *                    数秒后显示可点击全身按钮
     *                      ↓                ↓
     *                 未点击               点击
     *                    ↓                 ↓
     *       数秒后显示下降全身动画       显示被打停留图片标签
     *                                     ↓
     *                             数秒后显示被打向下动画
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        switch (gameState){
            //开始状态显示上升头部动画
            case start:
                stateTime += delta;
                keyRegion = (TextureRegion) startAnimation.getKeyFrame(stateTime);
                setRegion(keyRegion);
                //约2秒后转have_up状态
                if (stateTime > 2) {
                    gameState = GameState.have_up;
                    stateTime = 0;
                }
                break;
            //上升状态，显示全身上升动画
            case have_up:
                stateTime += delta;
                haveAnimation.setPlayMode(com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL);
                keyRegion = (TextureRegion)haveAnimation.getKeyFrame(stateTime);
                setRegion(keyRegion);
                //1秒后转have_stay状态
                if (stateTime > 1){
                    gameState = GameState.have_stay;
                    stateTime = 0;
                }
                break;
            //显示可点击全身按钮
            case have_stay:
                stateTime += delta;
                //清空动画，优化画面
                setRegion(null);
                //显示按钮
                haveBtn.setVisible(true);
                //1秒后转未点击转have_down状态
                if (stateTime > 1){
                    gameState = GameState.have_down;
                    stateTime = 0;
                }
                break;
            //显示全身下降动画
            case have_down:
                stateTime += delta;
                haveAnimation.setPlayMode(Animation.PlayMode.REVERSED);
                keyRegion = (TextureRegion)haveAnimation.getKeyFrame(stateTime);
                setRegion(keyRegion);
                //按钮失效，优化画面
                haveBtn.setVisible(false);
                //2秒后转为end状态待随机函数抓取
                if (stateTime > 2){
                    gameState = GameState.end;
                    stateTime = 0;
                }
                break;
            //全身按钮转换过来的hit_stay状态，显示被打图片标签
            case hit_stay:
                stateTime += delta;
                //清空动画，优化画面
                setRegion(null);
                //按钮失效，显示被打图片
                haveBtn.setVisible(false);
                hitImg.setVisible(true);
                //1秒后转hit状态
                if (stateTime > 1){
                    gameState = GameState.hit;
                    stateTime = 0;
                }
                break;
            //显示被打下降动画
            case hit:
                stateTime += delta;
                keyRegion = (TextureRegion)hitAnimation.getKeyFrame(stateTime);
                setRegion(keyRegion);
                //被打静止图片失效，优化画面
                hitImg.setVisible(false);
                //2秒后转为end状态待随机函数抓取
                if (stateTime > 2) {
                    gameState = GameState.end;
                    stateTime = 0;

                }
                break;
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
