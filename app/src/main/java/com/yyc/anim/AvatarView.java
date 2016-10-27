package com.yyc.anim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by HFF on 16/10/18.
 */

public class AvatarView extends RelativeLayout {

    Context mContext;
    ImageView iv_avatar;
    TextView tv_cur_level;
    LinearLayout ll_bg;

    public static AvatarView getView(Context context) {
        return new AvatarView(context);
    }

    public AvatarView(Context context) {
        super(context);
        init(context);
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View rootView = View.inflate(context, R.layout.cur_level_layout, this);
        iv_avatar = (ImageView) rootView.findViewById(R.id.iv_avatar);
        tv_cur_level = (TextView) rootView.findViewById(R.id.tv_cur_level);
        ll_bg = (LinearLayout) rootView.findViewById(R.id.ll_bg);
    }

    public void setCurLevel(String level) {
        tv_cur_level.setText(level);
    }

    /**
     * 渐现动画
     */
    public void startAvatarAlphaAnim() {
        iv_avatar.setVisibility(VISIBLE);
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        iv_avatar.startAnimation(animation);
    }

    /**
     * 掉落动画
     *
     * @param top
     */
    public void startAvatarDownAnim(int top) {
        iv_avatar.setVisibility(VISIBLE);
        TranslateAnimation animator = new TranslateAnimation(0, 0, top, 0);
        animator.setDuration(500);
        iv_avatar.setAnimation(animator);
    }
}
