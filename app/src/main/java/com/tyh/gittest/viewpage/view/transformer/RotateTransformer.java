package com.tyh.gittest.viewpage.view.transformer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by Tan Yan Hao on 2019/4/22.
 */
public class RotateTransformer implements ViewPager.PageTransformer {

    private static final int MAX_ROTATE = 15;

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1) {
            page.setRotation(MAX_ROTATE);
            page.setPivotY(0);
            page.setPivotX(page.getWidth());
        } else if (position <= 1) {
            // 左边页面
            if (position < 0) {
                page.setPivotY(0);
                page.setPivotX(0.5f * page.getWidth() + 0.5f * (-position) * page.getWidth());
                page.setRotation(-MAX_ROTATE * position);
            } else { // 右边界面
                page.setPivotY(0);
                page.setPivotX(page.getWidth() * 0.5f * (1 - position));
                page.setRotation(-MAX_ROTATE * position);
            }
        } else {
            page.setRotation(-MAX_ROTATE);
            page.setPivotY(0);
            page.setPivotX(0);

        }
    }
}
