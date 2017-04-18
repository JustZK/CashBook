package com.main.zk.cashbook.accounting;

/**
 * Created by ZK on 2017/4/18.
 */

public class AccountingActivityListInfo {
    private int mImageViewSrc;
    private int mImageViewSelectedSrc;
    private boolean mIsSelected;

    public AccountingActivityListInfo(int mImageViewSrc, int mImageViewSelectedSrc, boolean mIsSelected){
        this.mImageViewSrc = mImageViewSrc;
        this.mImageViewSelectedSrc = mImageViewSelectedSrc;
        this.mIsSelected = mIsSelected;
    }

    public int getImageViewSrc() {
        return mImageViewSrc;
    }

    public void setImageViewSrc(int mImageViewSrc) {
        this.mImageViewSrc = mImageViewSrc;
    }

    public int getImageViewSelectedSrc() {
        return mImageViewSelectedSrc;
    }

    public void setImageViewSelectedSrc(int mImageViewSelectedSrc) {
        this.mImageViewSelectedSrc = mImageViewSelectedSrc;
    }

    public boolean isIsSelected() {
        return mIsSelected;
    }

    public void setIsSelected(boolean mIsSelected) {
        this.mIsSelected = mIsSelected;
    }
}
