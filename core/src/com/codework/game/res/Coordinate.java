package com.codework.game.res;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codework.game.WhacAHole;

public class Coordinate {

    private WhacAHole game;
    private TextureRegion region;

    Vector2[] vector2s;


    public Coordinate(WhacAHole game, TextureRegion region) {
        this.game = game;
        this.region = region;
    }

    public WhacAHole getGame() {
        return game;
    }

    public void setGame(WhacAHole game) {
        this.game = game;
    }

    public Vector2 getVetor(int index){
        float stageWidth=getGame().getWorldWidth();
        float stageHeight=getGame().getWorldHeight();
        float regionWidth = region.getRegionWidth();
        float regionHeight = region.getRegionHeight();
        vector2s = new Vector2[9];
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                vector2s[i*3+j] = new Vector2();
                vector2s[i*3+j].x=(((stageWidth/3)-regionWidth)/2)+(stageWidth/3)*j;
                vector2s[i*3+j].y=(((stageHeight/5)-regionHeight)/2)+(stageHeight/5)*i +100;
            }
        }
        return vector2s[index];
    }
}
