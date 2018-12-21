package com.codework.game.actor.root;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.codework.game.WhacAHole;

public class ButtonActor extends ImageButton {

    /**
     * 仿造ImageActor做得一个按钮根对象
     */

    private WhacAHole game;

    public ButtonActor(Drawable imageUp,float x,float y,WhacAHole game) {
        super(imageUp);
        this.game = game;
        setX(x);
        setY(y);
    }

    public float moveToX(float x){
        return getX()+ x;
    }
    public float moveToY(float y){return getY() + y;}

    /**这里的唯一变化是按钮图片Y轴缩小了20个像素，所以这里extra设置为10
     *
     *图片缩小的原因是因为动画图片底下有空白，显示的时候会影响画面效果
     *所以把按钮图片下面空白删去，以适应整体画面
     */
    public void checkScale(float stageWidth,float stageHeight,float regionWidth,float regionHeight,int extra){
        if ((int)getY() == (int)((stageHeight/5-regionHeight)/2+stageHeight/5+100+extra)){
            setSize(getWidth()*0.92f,getHeight()*0.92f);
            if ((int)getX() == (int)((stageWidth/3)-regionWidth)/2){
                setX(moveToX(30f));
            }
            if ((int)getX() == (int)(((stageWidth/3)-regionWidth)/2)+(stageWidth/3)*2){
                setX(moveToX(-30f));
            }
        }
        if ((int)getY() == (int)((stageHeight/5-regionHeight)/2+(stageHeight/5)*2+100+extra)){
            setSize(getWidth()*0.92f*0.92f,getHeight()*0.92f*0.92f);
            setY(moveToY(-50f));
            if ((int)getX() == (int)((stageWidth/3)-regionWidth)/2){
                setX(moveToX(60f));
            }
            if ((int)getX() == (int)(((stageWidth/3)-regionWidth)/2)+(stageWidth/3)*2){
                setX(moveToX(-60f));
            }
        }
    }

    public WhacAHole getGame() {
        return game;
    }

    public void setGame(WhacAHole game) {
        this.game = game;
    }
}
