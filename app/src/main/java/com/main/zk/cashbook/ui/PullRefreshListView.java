package com.main.zk.cashbook.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.main.zk.cashbook.R;
import com.main.zk.cashbook.util.LogUtil;

public class PullRefreshListView extends ListView implements AbsListView.OnScrollListener {
    private Context mContext;
    private LayoutInflater inflater;

    private LinearLayout headerView;
    private int headerContentHeight = 200;
    private int startY;
    private int stateY;

    private boolean isRecoded = false;
    private boolean isStartAnimation = false;
    private boolean isRefreshable;

    private OnRefreshListener refreshListener;

    public PullRefreshListView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public PullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init(){
        inflater = LayoutInflater.from(mContext);
        headerView = (LinearLayout) inflater.inflate(R.layout.lv_header, null);
        measureView(headerView);
        headerContentHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0, -1 * headerContentHeight, 0, 0);
        headerView.invalidate();
        addHeaderView(headerView, null, false);

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
                    isRecoded = false;
                    break;
                case MotionEvent.ACTION_UP:
                    LogUtil.d("test", "ACTION_UP");
                    headerView.setPadding(0, -1 * headerContentHeight, 0, 0);
                    if (isRecoded) {
                        isRecoded = false;
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
                    if (!isRecoded) {
                        isRecoded = true;
                        startY = stateY;
                    }
                    if (!isStartAnimation && stateY > startY) {
                        isStartAnimation = true;
                        onRefreshAnimation(true, false);
                    }
                    if ((stateY - startY) <= headerContentHeight) {
                        headerView.setPadding(0, (stateY - startY)
                                - headerContentHeight, 0, 0);
                    }
                    break;
                default:
                    break;
            }
        return true;
    }

    // “估计”headView的width以及height
    private void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0,
                params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
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
