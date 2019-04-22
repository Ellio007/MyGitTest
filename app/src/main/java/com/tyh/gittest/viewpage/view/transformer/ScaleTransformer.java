package com.tyh.gittest.viewpage.view.transformer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @author Tan Yan Hao
 * @date 2019/4/19
 */
public class ScaleTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;

    private static final float MIN_ALPHA = 0.5f;


    @Override
    public void transformPage(@NonNull View page, float position) {
        // a->b
        // a , position :(0,-1)
        // b , position :(1,0)

        // b->a
        // a , position :(-1,0)
        // b , position :(0,1)
        if (position < -1) {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);
            page.setTranslationX(MIN_SCALE);
            page.setTranslationY(MIN_SCALE);
        } else if (position <= 1) {
            // 左边页面
            if (position < 0) {
                // a->b position:(0,-1)
                // 1, 0.75
                float scaleA = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
                page.setScaleX(scaleA);
                page.setScaleY(scaleA);

                float alphaA = MIN_ALPHA + (1 - MIN_ALPHA) * (1 + position);
                page.setAlpha(alphaA);

                page.setTranslationX(scaleA);
                page.setTranslationY(scaleA);
            } else {
                // 右边界面
                // a->b
                // b , position :(1,0)
                // 0.75 ,1
                float scaleB = MIN_SCALE + (1 - MIN_SCALE) * +(1 - position);
                page.setScaleX(scaleB);
                page.setScaleY(scaleB);

                float alphaB = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - position);
                page.setAlpha(alphaB);
                // b->a
                // b , position :(0,1)
                // 1 ， 0.75f
                page.setTranslationX(scaleB);
                page.setTranslationY(scaleB);
            }
        } else {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);
            page.setTranslationX(MIN_SCALE);
            page.setTranslationY(MIN_SCALE);
        }
    }
}
