package com.tyh.gittest.customeview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

import com.tyh.gittest.R;

import java.util.ArrayList;
import java.util.List;

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

        ImageBannerFrameLayout bannerView = findViewById(R.id.bannerView);

        List<Bitmap> bitmaps = new ArrayList<>();
        for (int i = 0; i < resIds.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resIds[i]);
            bitmaps.add(bitmap);
        }
        bannerView.setImageWidth(width);
        bannerView.setImageBitmaps(bitmaps);
//        for (int resId : resIds) {
//            ImageView imageView = new ImageView(this);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
//            imageView.setLayoutParams(lp);
//            imageView.setImageResource(resId);
//            bannerView.addView(imageView);
//        }
    }
}
