package com.yyc.anim;


import android.os.Handler;

/**
 * Created by HFF on 16/9/9.
 * 计时器
 */
public class LevelTimer {
    Handler mHandler;
    long mStartTime;
    private int maxLength = 5000;  //最长时间

    private LevelTimeWatchListener listener;

    public LevelTimer() {
        mHandler = new Handler();
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void startTimer() {
        if (mHandler != null) {
            mStartTime = System.currentTimeMillis();
            mHandler.postDelayed(runnable, 10);
        }
    }

    public void stopTimer() {
        if (mHandler != null) {
            mHandler.removeCallbacks(runnable);
        }
    }

    // 定时器
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                long endTime = System.currentTimeMillis();
                int time = (int) ((endTime - mStartTime));
                if (listener != null) {
                    listener.onTimeTick(time);
                }
                if (time > maxLength) {
                    listener.onTimeFinish();
                } else {
                    mHandler.postDelayed(this, 10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public interface LevelTimeWatchListener {
        void onTimeTick(int time);

        void onTimeFinish();
    }

    public void AddAudioTimeWatchListener(LevelTimeWatchListener listener) {
        this.listener = listener;
    }
}
