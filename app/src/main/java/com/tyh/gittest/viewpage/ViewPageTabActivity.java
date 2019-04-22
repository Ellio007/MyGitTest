package com.tyh.gittest.viewpage;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.tyh.gittest.R;
import com.tyh.gittest.viewpage.fragment.TabFragment;
import com.tyh.gittest.viewpage.view.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 知识点：
 * ViewPage实现微信底部切换渐变效果
 *
 * @author TanYanHao
 */
public class ViewPageTabActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private TabView btnWeChat;
    private TabView btnFriend;
    private TabView btnFind;
    private TabView btnMy;

    private List<TabView> tabViews = new ArrayList<>();

    private List<String> titles = new ArrayList<>(Arrays.asList("微信", "通讯录", "朋友圈", "我"));

    private SparseArray<TabFragment> fragments = new SparseArray<>();

    private static final String BUNDLE_KEY_POS = "bundle_key_pos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_tab);
        initViews();
        initViewPagerAdapter();
        initEvents();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int currentTabPos = savedInstanceState.getInt(BUNDLE_KEY_POS, 0);
        setCurrentTab(currentTabPos);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_KEY_POS, viewPager.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    private void initEvents() {
        for (int i = 0; i < tabViews.size(); i++) {
            TabView tabView = tabViews.get(i);
            int finalI = i;
            tabView.setOnClickListener(v -> {
                viewPager.setCurrentItem(finalI, false);
                setCurrentTab(finalI);
            });
        }
    }

    private void initViewPagerAdapter() {
        viewPager = findViewById(R.id.viewPageMain);
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return TabFragment.newInstance(titles.get(position));
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TabFragment fragment = (TabFragment) super.instantiateItem(container, position);
                fragments.put(position, fragment);
                return fragment;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                fragments.remove(position);
                super.destroyItem(container, position, object);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 左->右 0->1, left pos ,right pos+1,positionOffset 0~1
                // 右->左 0->1, left pos ,right pos+1,positionOffset 1~0
                if (positionOffset > 0) {
                    TabView leftBtn = tabViews.get(position);
                    TabView rightBtn = tabViews.get(position + 1);
                    leftBtn.setProgress((1 - positionOffset));
                    rightBtn.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        btnWeChat = findViewById(R.id.tab_weixin);
        btnFriend = findViewById(R.id.tab_friend);
        btnFind = findViewById(R.id.tab_find);
        btnMy = findViewById(R.id.tab_my);

        btnWeChat.setIconAndText(R.drawable.main_bottom_default_call_icon, R.drawable.main_bottom_checked_call_icon, "微信");

        btnFriend.setIconAndText(R.drawable.main_bottom_default_clues_icon, R.drawable.main_bottom_checked_clues_icon, "通讯录");

        btnFind.setIconAndText(R.drawable.main_bottom_default_contact_icon, R.drawable.main_bottom_checked_contact_icon, "发现");

        btnMy.setIconAndText(R.drawable.main_bottom_default_customer_icon, R.drawable.main_bottom_checked_customer_icon, "我");

        tabViews.add(btnWeChat);
        tabViews.add(btnFriend);
        tabViews.add(btnFind);
        tabViews.add(btnMy);

        setCurrentTab(0);
    }

    private void setCurrentTab(int pos) {
        for (int i = 0; i < tabViews.size(); i++) {
            TabView tabView = tabViews.get(i);
            if (i == pos) {
                tabView.setProgress(1);
            } else {
                tabView.setProgress(0);
            }
        }
    }

}
