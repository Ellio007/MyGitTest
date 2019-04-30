package com.tyh.gittest.customeview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Tan Yan Hao
 * @date 2019/4/25
 */
public class ImageBannerView extends ViewGroup {

    private static final int MESSAGE_WHAT = 0;

    private int childCount;

    private int childrenWidth;

    private int childrenHeight;

    private int x;

    private int index;

    private Scroller scroller;

    private boolean isAuto = true;

    private Timer timer = new Timer();

    private TimerTask timerTask;

    public interface ImageBannerViewListener {
        void selectImage(int index);
    }

    private ImageBannerViewListener bannerViewListener;

    public void setBannerViewListener(ImageBannerViewListener bannerViewListener) {
        this.bannerViewListener = bannerViewListener;
    }

    private Handler autoHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_WHAT:
                    index++;
                    if (index >= childCount) {
                        index = 0;
                    }
                    int distance = childrenWidth * index;
                    int oldIndex;
                    // 当index 等于0时，上一个位置是childCount - 1,
                    // 其他情况下是index-1
                    if (index == 0) {
                        oldIndex = childCount - 1;
                    } else {
                        oldIndex = index - 1;
                    }
                    // startX 表示当前的位置，可以用getScrollX()得到
                    int startX = oldIndex * childrenWidth;
                    // dx 用目标的位置减去当前的位置得到
                    int dx = distance - startX;
                    scroller.startScroll(startX, 0, dx, 0);
                    invalidate();
                    bannerViewListener.selectImage(index);
//                    scrollTo(distance,0);
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    private boolean isClick;

    private ImageBannerListener listener;

    public void setImageBannerListener(ImageBannerListener listener) {
        this.listener = listener;
    }

    public interface ImageBannerListener {
        /**
         * 图片点击事件
         *
         * @param position 当前点击图片位置
         */
        void clickImageIndex(int position);
    }

    public void startAuto() {
        isAuto = true;
    }

    public void stopAuto() {
        isAuto = false;
    }

    public ImageBannerView(Context context) {
        super(context);
        initScroller();
    }

    public ImageBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScroller();
    }

    public ImageBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initScroller();
    }

    private void initScroller() {
        scroller = new Scroller(getContext());
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isAuto) {
                    autoHandler.sendEmptyMessage(MESSAGE_WHAT);
                }
            }
        };
        timer.schedule(timerTask, 3000, 3000);

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        childCount = getChildCount();
        if (0 == childCount) {
            setMeasuredDimension(0, 0);
        } else {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            View view = getChildAt(0);
            childrenWidth = view.getMeasuredWidth();
            childrenHeight = view.getMeasuredHeight();
            int width = childrenWidth * childCount;
            setMeasuredDimension(width, childrenHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int leftMargin = 0;
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                view.layout(leftMargin, 0, leftMargin + childrenWidth, childrenHeight);
                leftMargin += childrenWidth;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isClick = true;
                stopAuto();
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                x = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int distance = moveX - x;
                scrollBy(-distance, 0);
                x = moveX;
                isClick = false;
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                index = (scrollX + childrenWidth / 2) / childrenWidth;
                if (isClick) {
                    if (listener != null) {
                        listener.clickImageIndex(index);
                    }
                } else {
                    if (index < 0) {
                        index = 0;
                    } else if (index == childCount - 1) {
                        index = childCount - 1;
                    }
                    int dx = index * childrenWidth - scrollX;
                    scroller.startScroll(scrollX, 0, dx, 0);
                    invalidate();
                }
                startAuto();
                bannerViewListener.selectImage(index);
//                scrollTo(index * childrenWidth, 0);
                break;
            default:
                break;
        }
        return true;
    }


}
