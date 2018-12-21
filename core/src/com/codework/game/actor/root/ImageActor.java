package com.codework.game.actor.root;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ImageActor extends Actor {

    private TextureRegion region;

    public ImageActor() {
    }

    public ImageActor(TextureRegion region,float x,float y) {
        this.region = region;
        //设置位置 非常重要且容易忽视的一步，没设置则无法显示演员
        setX(x);
        setY(y);
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
        if (this.region != null){
            setSize(this.region.getRegionWidth(),this.region.getRegionHeight());
        }else {
            setSize(0,0);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (region == null || !isVisible()){
            return;
        }
        // 备份 batch 原本的 Color
        Color tempBatchColor = batch.getColor();

        // 获取演员的 Color
        Color color = getColor();

        // 将演员的 Color 结合 parentAlpha 设置到 batch
        batch.setColor(color.r,color.g,color.b,color.a*parentAlpha);

        // 结合演员的属性绘制表示演员的纹理区域
        batch.draw(
                region,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );

        // 还原 batch 原本的 Color
        batch.setColor(tempBatchColor);
    }
    //封装的向X轴和Y轴移动方法
    public float moveToX(float x){
        return getX()+ x;
    }
    public float moveToY(float y){return getY() + y;}
    //透视原理设置，这里没有用相机实现相关功能，下一步可以改进***************************
    public void checkScale(float stageWidth,float stageHeight,float regionWidth,float regionHeight, int extra){
        //找第二排整体变小，并且第1和第3向中间靠拢
        if (getY() == (((stageHeight/5)-regionHeight)/2)+(stageHeight/5) +100 + extra){
            setScale(0.92f);
            if (getX() == (((stageWidth/3)-regionWidth)/2)){
                setX(moveToX(30f));
            }
            if (getX() == (((stageWidth/3)-regionWidth)/2)+(stageWidth/3)*2){
                setX(moveToX(-30f));
            }
        }
        //找第三排整体变小向下移动，并且第1和第3向中间靠拢
        if (getY() == (((stageHeight/5)-regionHeight)/2)+(stageHeight/5)*2 +100 + extra){
            setScale(0.92f*0.92f);
            setY(moveToY(-50f));
            if (getX() == (((stageWidth/3)-regionWidth)/2)){
                setX(moveToX(60f));
            }
            if (getX() == (((stageWidth/3)-regionWidth)/2)+(stageWidth/3)*2){
                setX(moveToX(-60f));
            }
        }
    }
}
