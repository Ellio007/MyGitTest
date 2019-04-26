package com.tyh.gittest.customeview;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.tyh.gittest.R;

import androidx.annotation.Nullable;

/**
 * Created by Tan Yan Hao on 2019/4/25.
 */
public class BannerActivity extends Activity {

    private int[] resIds = new int[]{
            R.drawable.feature_first,
            R.drawable.feature_second,
            R.drawable.feature_third,
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        ImageBannerView bannerView = findViewById(R.id.bannerView);
        for (int resId : resIds) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(resId);
            bannerView.addView(imageView);
        }
        bannerView.setImageBannerListener(position ->
                Toast.makeText(BannerActivity.this, "position= " + position, Toast.LENGTH_SHORT).show());
    }
}
