package com.main.zk.cashbook.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

import com.main.zk.cashbook.util.LogUtil;

public class PullRefreshListView extends ListView implements AbsListView.OnScrollListener {

    private final static int headerContentHeight = 200;
    private int startY;
    private int stateY;

    private boolean isRecored = false;
    private boolean isStartAnimation = false;
    private boolean isRefreshable;

    private OnRefreshListener refreshListener;

    public PullRefreshListView(Context context) {
        super(context);
        init();
    }

    public PullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem == 0) {
            isRefreshable = true;
        } else {
            isRefreshable = false;
        }
        LogUtil.d("test", "isRefreshable::"+isRefreshable);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isRefreshable)
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    LogUtil.d("test", "ACTION_DOWN");
                    isStartAnimation = false;
                    isRecored = false;
                    break;
                case MotionEvent.ACTION_UP:
                    LogUtil.d("test", "ACTION_UP");
                    if (isRecored) {
                        isRecored = false;
                        if ((stateY - startY) > headerContentHeight) {
                            onRefreshAnimation(false, true);
                        } else {
                            onRefreshAnimation(false, false);
                        }
                    }
                    if (isStartAnimation)
                        isStartAnimation = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    LogUtil.d("test", "ACTION_MOVE");
                    stateY = (int) ev.getY();
                    if (!isRecored) {
                        isRecored = true;
                        startY = stateY;
                    }
                    if (!isStartAnimation && stateY > startY) {
                        isStartAnimation = true;
                        onRefreshAnimation(true, false);
                    }
                    break;
                default:
                    break;
            }
        return true;
    }

    public void setonRefreshListener(OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    public interface OnRefreshListener {
        void onRefresh(boolean isStartAnimation, boolean isOnRefresh);
    }

    private void onRefreshAnimation(boolean isStart, boolean isOnRefresh) {
        if (refreshListener != null) {
            refreshListener.onRefresh(isStart, isOnRefresh);
        }
    }
}
