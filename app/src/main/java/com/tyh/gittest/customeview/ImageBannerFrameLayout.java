package com.tyh.gittest.customeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tyh.gittest.R;

import java.util.List;

/**
 * Created by Tan Yan Hao on 2019/4/26.
 */
public class ImageBannerFrameLayout extends FrameLayout {

    private ImageBannerView imageBannerView;

    private LinearLayout ll;

    private int width;


    public ImageBannerFrameLayout(@NonNull Context context) {
        super(context);
        initImageBannerView();
        initDotLinearLayout();
    }

    public ImageBannerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initImageBannerView();
        initDotLinearLayout();
    }

    public ImageBannerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImageBannerView();
        initDotLinearLayout();
    }

    /**
     * 初始化自定义的图片轮播功能的核心类
     */
    private void initImageBannerView() {
        imageBannerView = new ImageBannerView(getContext());
        FrameLayout.LayoutParams lp = new
                FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        imageBannerView.setLayoutParams(lp);
        imageBannerView.setBannerViewListener(index -> {
            int count = ll.getChildCount();
            for (int i = 0; i < count; i++) {
                ImageView iv = (ImageView) ll.getChildAt(i);
                if (i == index) {
                    iv.setImageResource(R.drawable.dot_selected);
                } else {
                    iv.setImageResource(R.drawable.dot_normal);
                }
            }
        });
        addView(imageBannerView);
    }

    /**
     * 初始化底部圆点布局
     */
    private void initDotLinearLayout() {
        ll = new LinearLayout(getContext());
        FrameLayout.LayoutParams lp = new
                FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 40);
        lp.gravity = Gravity.BOTTOM;
        ll.setLayoutParams(lp);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setGravity(Gravity.CENTER);
        ll.setBackgroundColor(Color.RED);
        addView(ll);

        ll.setAlpha(0.5f);
//        FrameLayout.LayoutParams layoutParams = (LayoutParams)ll. getLayoutParams();
//        layoutParams.gravity = Gravity.BOTTOM;
//        ll.setLayoutParams(layoutParams);
    }

    public void setImageBitmaps(List<Bitmap> bitmaps) {
        for (Bitmap bitmap : bitmaps) {
            addBitmapToImageBannerView(bitmap);
            addDotToLinearLayout();
        }
    }

    private void addDotToLinearLayout() {
        ImageView iv = new ImageView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5, 5, 5, 5);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.dot_normal);
        ll.addView(iv);
    }

    private void addBitmapToImageBannerView(Bitmap bitmap) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageBitmap(bitmap);
        imageBannerView.addView(imageView);
    }

    public void setImageWidth(int width) {
        this.width = width;
    }
}
