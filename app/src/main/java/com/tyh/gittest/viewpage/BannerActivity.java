package com.tyh.gittest.viewpage;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.tyh.gittest.R;
import com.tyh.gittest.viewpage.fragment.SplashFragment;
import com.tyh.gittest.viewpage.view.transformer.RotateTransformer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 知识点：
 * ViewPage实现微信底部切换渐变效果
 *
 * @author TanYanHao
 */
public class BannerActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private int[] resId = new int[]{
            0xffff0000,
            0xff00ff00,
            0xff0000ff,
    };

    private SparseArray<SplashFragment> fragments = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        viewPager = findViewById(R.id.viewPageMain);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(40);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return resId.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = new View(container.getContext());
                view.setBackgroundColor(resId[position]);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp);
                container.addView(view);
                return view;

            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        viewPager.setPageTransformer(true,new RotateTransformer());
    }

}
