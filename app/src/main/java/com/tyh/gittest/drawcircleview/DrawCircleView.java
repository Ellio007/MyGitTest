/*
 * Copyright (c) 2015-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有 (c) 2015-2016 湖南蚁坊软件有限公司。保留所有权利。
 */

package com.tyh.gittest.drawcircleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.tyh.gittest.R;

/**
 * 自定义任务统计  圆圈View
 * Created by Tan Yan Hao on 2016/5/16.
 */
public class DrawCircleView extends View {

    //处理中进度
    private float firstProgress = 33;
    //失败中进度
    private float secondProgress = 33;
    //成功中进度
    private float thirdProgress = 33;
    // 画圆所在的距形区域
    private RectF mRectF;
    //画笔
    private Paint mPaint;

    private String mTxtHint;

    private String mTxtNumber;

    private boolean isDefault = false;

    public DrawCircleView(Context context) {
        super(context);
        mPaint = new Paint();
        mRectF = new RectF();
    }

    public DrawCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mRectF = new RectF();
    }

    public DrawCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();
        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }
        // 设置画笔相关属性
        mPaint.setAntiAlias(true);
        canvas.drawColor(Color.TRANSPARENT);
        int mCircleLineStrokeWidth = 15;
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        // 位置
        mRectF.left = mCircleLineStrokeWidth / 2; // 左上角x
        mRectF.top = mCircleLineStrokeWidth / 2; // 左上角y
        mRectF.right = width - mCircleLineStrokeWidth / 2; // 左下角x
        mRectF.bottom = height - mCircleLineStrokeWidth / 2; // 右下角y

        if (!isDefault) {
            float firstSweepAngle;
            float secondSweepAngle;
            float thirdSweepAngle;
            //如果所占百分比大于1则让总
            firstSweepAngle = firstProgress * 360;
            secondSweepAngle = secondProgress * 360;
            thirdSweepAngle = thirdProgress * 360;
            // 绘制圆圈，进度条
            if (firstSweepAngle == 0 && thirdSweepAngle == 0) {
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_fail));
                canvas.drawArc(mRectF, -90, secondSweepAngle, false, mPaint);
            } else if (secondSweepAngle == 0 && thirdSweepAngle == 0) {
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_success));
                canvas.drawArc(mRectF, -90, firstSweepAngle, false, mPaint);
            } else if (firstSweepAngle == 0 && secondSweepAngle == 0) {
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_progress));
                canvas.drawArc(mRectF, -90, thirdSweepAngle, false, mPaint);
            } else if (firstSweepAngle == 0) {
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_fail));
                canvas.drawArc(mRectF, -90, secondSweepAngle - 3, false, mPaint);
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_progress));
                canvas.drawArc(mRectF, -90 + secondSweepAngle, thirdSweepAngle - 3, false, mPaint);
            } else if (secondSweepAngle == 0) {
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_success));
                canvas.drawArc(mRectF, -90, firstSweepAngle - 3, false, mPaint);
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_progress));
                canvas.drawArc(mRectF, -90 + firstSweepAngle, thirdSweepAngle - 3, false, mPaint);
            } else if (thirdSweepAngle == 0) {
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_success));
                canvas.drawArc(mRectF, -90, firstSweepAngle - 3, false, mPaint);
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_fail));
                canvas.drawArc(mRectF, -90 + firstSweepAngle, secondSweepAngle - 3, false, mPaint);
            } else {
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_success));
                canvas.drawArc(mRectF, -90, firstSweepAngle - 3, false, mPaint);
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_fail));
                canvas.drawArc(mRectF, -90 + firstSweepAngle, secondSweepAngle - 3, false, mPaint);
                mPaint.setColor(getResources().getColor(R.color.draw_circle_view_progress));
                canvas.drawArc(mRectF, -90 + firstSweepAngle + secondSweepAngle, thirdSweepAngle - 3, false, mPaint);
            }
            drawTextView(canvas, width, height);

        } else {
            mPaint.setColor(getResources().getColor(R.color.draw_circle_view_default));
            canvas.drawArc(mRectF, 0, 360, false, mPaint);
            // 绘制进度文案显示
            drawTextView(canvas, width, height);
        }
    }

    private void drawTextView(Canvas canvas, int width, int height) {
        // 绘制进度文案显示
        if (!TextUtils.isEmpty(mTxtHint)) {
            int mTxtStrokeWidth = 1;
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            String text = mTxtHint;
            int textHeight = height / 10;
            mPaint.setTextSize(textHeight);
            mPaint.setColor(Color.rgb(0x99, 0x99, 0x99));
            int textWidth = (int) mPaint.measureText(text, 0, text.length());
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(text, width / 2 - textWidth / 2, 3 * height / 7 + textHeight / 2, mPaint);
        }
        // 绘制进度文案显示
        if (!TextUtils.isEmpty(mTxtNumber)) {
            int mTxtStrokeWidth = 1;
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            String text = mTxtNumber;
            int textHeight = height / 10;
            mPaint.setTextSize(textHeight);
            mPaint.setColor(Color.rgb(0x99, 0x99, 0x99));
            int textWidth = (int) mPaint.measureText(text, 0, text.length());
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(text, width / 2 - textWidth / 2, 4 * height / 7 + textHeight / 2, mPaint);
        }
    }

    /**
     * 设置3种类型（成功、失败和处理中）所占比例
     *
     * @param firstProgress  成功所占比例
     * @param secondProgress 失败所占比例
     * @param thirdProgress  处理中所占比例
     */
    public void setProgress(float firstProgress, float secondProgress, float thirdProgress) {
        this.firstProgress = firstProgress;
        this.secondProgress = secondProgress;
        this.thirdProgress = thirdProgress;
        this.invalidate();
    }

    /**
     * 设置园内上面的文字提示
     *
     * @param mTxtHint 文字提示（跟评数或发文数）
     */
    public void setmTxtHint(String mTxtHint) {
        this.mTxtHint = mTxtHint;
        this.invalidate();
    }

    /**
     * 设置圆圈下面的文字提示（总数）
     *
     * @param mTxtNumber 文字提示（总数）
     */
    public void setmTxtNumber(String mTxtNumber) {
        this.mTxtNumber = mTxtNumber;
    }

    public void setDefaultView() {
        isDefault = true;
        this.invalidate();
    }
}