package com.tyh.gittest.viewpage;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.tyh.gittest.R;
import com.tyh.gittest.viewpage.fragment.SplashFragment;
import com.tyh.gittest.viewpage.view.transformer.ScaleTransformer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 知识点：
 * ViewPage实现微信底部切换渐变效果
 *
 * @author TanYanHao
 */
public class SplashActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private int[] resId = new int[]{
            R.drawable.feature_first,
            R.drawable.feature_second,
            R.drawable.feature_third,
            R.drawable.feature_first,
    };

    private SparseArray<SplashFragment> fragments = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        viewPager = findViewById(R.id.viewPageMain);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return SplashFragment.newInstance(resId[position]);
            }

            @Override
            public int getCount() {
                return resId.length;
            }

            @NonNull
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                SplashFragment splashFragment = (SplashFragment) super.instantiateItem(container, position);
                fragments.put(position, splashFragment);
                return splashFragment;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                fragments.remove(position);
                super.destroyItem(container, position, object);
            }
        });
        viewPager.setPageTransformer(true, new ScaleTransformer());
    }

}
