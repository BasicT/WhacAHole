package com.codework.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.codework.game.res.Res;
import com.codework.game.screen.MainGame;

public class WhacAHole extends Game {
	public static final String TAG = "WhacAHole";

	/** 是否显示帧率 */
	public static final boolean SHOW_FPS = true;

	/** 世界宽度 */
	private float worldWidth;
	/** 世界高度 */
	private float worldHeight;

	/** 游戏主场景 */
	private MainGame mainGame;

	/** 字体设置 */
	private BitmapFont bitmapFont;

    /** 纹理 */
    Texture bg;
    Texture hitImg;

    Texture hole;
    Texture have;
    Texture hit;
    Texture head;

    Texture upBtn;
    Texture downBtn;
    Texture haveBtn;

    /** 纹理区域 */
    TextureRegion bgRegion;
    TextureRegion hitImgRegion;

    TextureRegion holeRegion;
    TextureRegion haveRegion;
    TextureRegion hitRegion;
    TextureRegion headRegion;
    /** 按键纹理区域 */
    TextureRegion upBtnRegion;
    TextureRegion downBtnRegion;

    TextureRegion haveBtnRegion;
    /** 文字样式 */
    Label.LabelStyle style;
	
	@Override
	public void create () {
		// 设置LOG输出级别
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		// 为了不压扁或拉长图片, 按实际屏幕比例计算世界宽高
		worldWidth = Res.FIX_WORLD_WIDTH;
		worldHeight = Gdx.graphics.getHeight()* worldWidth/Gdx.graphics.getWidth();

        // 初始化纹理区域
        initRegion();

        // 初始化字体样式
        bitmapFont =new BitmapFont(Gdx.files.internal("scribbleT.fnt"), Gdx.files.internal("scribbleT.png"),false);
        style = new Label.LabelStyle(bitmapFont,bitmapFont.getColor());

        // 创建主游戏场景
        mainGame = new MainGame(this);

        // 设置当前场景
        setScreen(mainGame);
	}

	private void initRegion(){

	    bg = new Texture(Res.Region.BG);
	    bgRegion = new TextureRegion(bg);

	    hitImg = new Texture(Res.Region.HIT_IMG);
	    hitImgRegion = new TextureRegion(hitImg);

	    hole = new Texture(Res.Region.HOLE);
	    holeRegion = new TextureRegion(hole);

        have = new Texture(Res.Region.HAVE);
        haveRegion = new TextureRegion(have);

        hit = new Texture(Res.Region.HIT);
        hitRegion = new TextureRegion(hit);

        head = new Texture(Res.Region.HEAD);
        headRegion = new TextureRegion(head);

        upBtn = new Texture(Res.Region.UP_BTN);
        upBtnRegion = new TextureRegion(upBtn);

        downBtn = new Texture(Res.Region.DOWN_BTN);
        downBtnRegion = new TextureRegion(downBtn);

        haveBtn = new Texture(Res.Region.HAVE_BTN);
        haveBtnRegion = new TextureRegion(haveBtn);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();

        // 应用退出时需要手动销毁场景
        if (mainGame != null) {
            mainGame.dispose();
        }
        if (bg != null){
            bg.dispose();
        }
        if (hitImg != null){
            hitImg.dispose();
        }
        if (hole != null){
            hole.dispose();
        }
        if (have != null){
            have.dispose();
        }
        if (hit != null){
            hit.dispose();
        }
        if (head != null){
            head.dispose();
        }
        if (bitmapFont != null){
            bitmapFont.dispose();
        }
        if (upBtn != null){
            upBtn.dispose();
        }
        if (downBtn != null){
            downBtn.dispose();
        }
        if (haveBtn != null){
            haveBtn.dispose();
        }
	}

    public float getWorldWidth() {
        return worldWidth;
    }

    public float getWorldHeight() {
        return worldHeight;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public TextureRegion getBgRegion() {
        return bgRegion;
    }

    public TextureRegion getHoleRegion() {
        return holeRegion;
    }

    public TextureRegion getHaveRegion() {
        return haveRegion;
    }

    public TextureRegion getHitRegion() {
        return hitRegion;
    }

    public TextureRegion getHeadRegion() {
        return headRegion;
    }

    public TextureRegion getUpBtnRegion() {
        return upBtnRegion;
    }

    public TextureRegion getDownBtnRegion() {
        return downBtnRegion;
    }

    public Label.LabelStyle getStyle() {
        return style;
    }

    public TextureRegion getHaveBtnRegion() {
        return haveBtnRegion;
    }

    public TextureRegion getHitImgRegion() {
        return hitImgRegion;
    }
}
