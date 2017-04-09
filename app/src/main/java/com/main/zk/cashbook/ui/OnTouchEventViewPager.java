package com.main.zk.cashbook.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ZK on 2017/4/1.
 */

public class OnTouchEventViewPager extends ViewPager {
    private OnRefreshListener mOnRefreshListener;
    public OnTouchEventViewPager(Context context) {
        super(context);
    }

    public OnTouchEventViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                if (mOnRefreshListener != null)
                    mOnRefreshListener.onRefresh();
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void setonRefreshListener(OnRefreshListener refreshListener) {
        this.mOnRefreshListener = refreshListener;
    }

    public interface OnRefreshListener {
        void onRefresh();
    }
}
