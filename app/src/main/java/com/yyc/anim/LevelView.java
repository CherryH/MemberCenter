package com.yyc.anim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by HFF on 16/10/18.
 */

public class LevelView extends TextView {

    public static LevelView getView(Context context) {
        return new LevelView(context);
    }

    public LevelView(Context context) {
        super(context);
    }

    public LevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLevel(String level) {
        setBackgroundResource(R.drawable.ic_level);
        setTextColor(getResources().getColor(R.color.red_deep));
        setTextSize(11);
        setGravity(Gravity.CENTER);
        setText(level);
    }

}
