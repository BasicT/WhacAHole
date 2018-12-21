package com.codework.game.res;

import com.badlogic.gdx.utils.viewport.StretchViewport;

public interface Res {

    /** 固定世界宽度为 480, 高度根据实际屏幕比例换算 */
    public static final float FIX_WORLD_WIDTH = 1080;

    /**
     * 纹理图集
     */
    public static interface Region{

        public static final String BG = "background.png";
        public static final String HIT_IMG = "hitimg.png";

        public static final String HOLE = "hole.png";
        public static final String HAVE = "have.png";
        public static final String HIT = "hit.png";
        public static final String HEAD = "have_head.png";

        public static final String UP_BTN = "buttonup.png";
        public static final String DOWN_BTN = "buttondown.png";

        public static final String HAVE_BTN = "havebtn.png";


    }
}
