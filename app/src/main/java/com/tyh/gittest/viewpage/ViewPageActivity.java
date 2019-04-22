package com.tyh.gittest.viewpage;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.Button;

import com.tyh.gittest.R;
import com.tyh.gittest.viewpage.fragment.TabFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * ViewPage使用
 * 知识点：
 * 1. ViewPage的正确使用姿势
 * 2. Fragment与Activity通信，Activity 与 Fragment通信
 *
 * @author TanYanHao
 */
public class ViewPageActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private Button btnWeChat;
    private Button btnFriend;
    private Button btnFind;
    private Button btnMy;

    private List<Button> buttons = new ArrayList<>();

    private List<String> titles = new ArrayList<>(Arrays.asList("微信", "通讯录", "朋友圈", "我"));

    private SparseArray<TabFragment> fragments = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        initViews();
        initViewPagerAdapter();
    }

    private void initViewPagerAdapter() {
        viewPager = findViewById(R.id.viewPageMain);
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                TabFragment fragment = TabFragment.newInstance(titles.get(position));
                if (position == 0) {
                    fragment.setOnTitleClickListener(title -> changeWeChatTab(title));
                }
                return fragment;
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
                    Button leftBtn = buttons.get(position);
                    Button rightBtn = buttons.get(position + 1);
                    leftBtn.setText((1 - positionOffset) + "");
                    rightBtn.setText(positionOffset + "");
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
        btnWeChat = findViewById(R.id.btn_weixin);
        btnFriend = findViewById(R.id.btn_friend);
        btnFind = findViewById(R.id.btn_find);
        btnMy = findViewById(R.id.btn_my);

        buttons.add(btnWeChat);
        buttons.add(btnFriend);
        buttons.add(btnFind);
        buttons.add(btnMy);

        btnWeChat.setOnClickListener(v -> {
            // 获取第一个fragment
            TabFragment tabFragment = fragments.get(0);
            if (tabFragment != null) {
                tabFragment.changeTitle("changed");
            }
        });
    }

    public void changeWeChatTab(String title) {
        btnWeChat.setText(title);
    }
}
