package com.yyc.anim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * Created by HFF on 16/10/10.
 */

public class AnimView extends HorizontalScrollView {
    Context mContext;
    View rootView;
    int levelWidth;
    int totalLevelWidth;
    int progressHeight;

    ProgressBar levelBar;
    LinearLayout levelContainer;

    LevelTimer timer;

    public AnimView(Context context) {
        super(context);
        init(context);
    }

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setBackgroundColor(getResources().getColor(R.color.bg_color));
        setHorizontalScrollBarEnabled(false);
        timer = new LevelTimer();
        rootView = View.inflate(context, R.layout.anim_layout, this);
        levelBar = (ProgressBar) rootView.findViewById(R.id.pb);
        levelContainer = (LinearLayout) rootView.findViewById(R.id.ll_level_container);
        levelBar.setMax(LevelConstant.TOTAL_POINTS);
        calculateWidth();
    }

    private void calculateWidth() {
        levelWidth = (int) getResources().getDimension(R.dimen.level_width);
        progressHeight = (int) getResources().getDimension(R.dimen.progress_height);
        int levelIconSize = (int) getResources().getDimension(R.dimen.level_icon_size);
        int avatarSize = (int) getResources().getDimension(R.dimen.avatar_size);
        totalLevelWidth = (LevelConstant.MAX_LEVEL + 1) * levelWidth + LevelConstant.MAX_LEVEL * levelIconSize + avatarSize;
    }

    public void setIcon(int curStore, int curLevel) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(totalLevelWidth, progressHeight);
        levelBar.setLayoutParams(lp);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.leftMargin = levelWidth;

        for (int i = 1; i < LevelConstant.MAX_LEVEL + 1; i++) {
            if (i == curLevel) {
                AvatarView avatarView = AvatarView.getView(mContext);
                avatarView.setLayoutParams(lp2);
                levelContainer.addView(avatarView);
                avatarView.setCurLevel("V" + i);
                continue;
            }
            LevelView lv = LevelView.getView(mContext);
            lv.setLayoutParams(lp2);
            lv.setLevel("V" + i);
            levelContainer.addView(lv);
        }
        setLevelProgress(curStore, curLevel);
        animScroll(curLevel);
    }

    private void setLevelProgress(final int curStore, final int curLevel) {
        timer.startTimer();
        timer.AddAudioTimeWatchListener(new LevelTimer.LevelTimeWatchListener() {
            @Override
            public void onTimeTick(int time) {

                int progress = curStore * time / timer.getMaxLength();
                levelBar.setProgress(progress);
            }

            @Override
            public void onTimeFinish() {
                timer.stopTimer();
                animAvatar(curLevel);
            }
        });
    }

    private void animScroll(int curLevel) {
        final AvatarView avatarView = (AvatarView) levelContainer.getChildAt(curLevel - 1);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(timer.getMaxLength());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int iconX = avatarView.getLeft() + avatarView.getWidth() / 2;
                int progressWith = levelBar.getWidth() * levelBar.getProgress() / levelBar.getMax();
                int scrollX = progressWith - AnimView.this.getWidth() / 2;
                if (progressWith <= iconX) {
                    scrollTo(scrollX, 0);
                }
            }
        });
        valueAnimator.start();
    }

    private void animAvatar(int curLevel) {
        final AvatarView avatarView= (AvatarView) levelContainer.getChildAt(curLevel - 1);
        avatarView.startAvatarDownAnim(-avatarView.getTop());
    }

}
