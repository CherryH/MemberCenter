package com.yyc.anim;

import android.content.Context;

/**
 * Created by HFF on 16/10/20.
 */

public class LevelUtil {

    public static int getProgress(Context context, int curLevel, int curPoints) {
        int nextIntegral;// 下一个等级积分
        int currIntegral;// 当前等级积分
        int[] pointStartArray = context.getResources().getIntArray(R.array.member_points_start);

        if (curPoints >= LevelConstant.MAX_POINTS) {
            return LevelConstant.TOTAL_POINTS;
        }

        if (curLevel > pointStartArray.length) {
            return LevelConstant.TOTAL_POINTS;
        }
        currIntegral = pointStartArray[curLevel - 1];
        nextIntegral = pointStartArray[curLevel];

        int perProgress = LevelConstant.TOTAL_POINTS / (LevelConstant.MAX_LEVEL + 1);
        int progress = perProgress * curLevel;
        progress = progress + (curPoints - currIntegral) * perProgress / (nextIntegral - currIntegral);
        return progress;
    }
}
