package com.main.zk.cashbook.accounting;

/**
 * Created by ZK on 2017/4/18.
 */

public class AccountingActivityListInfo {
    private String mImageName;
    private int mImageViewSrc;
    private int mImageViewSelectedSrc;
    private int mTextViewColor;
    private boolean mIsSelected;

    public AccountingActivityListInfo(String mImageName, int mImageViewSrc, int mImageViewSelectedSrc, int mTextViewColor, boolean mIsSelected){
        this.mImageName = mImageName;
        this.mImageViewSrc = mImageViewSrc;
        this.mImageViewSelectedSrc = mImageViewSelectedSrc;
        this.mTextViewColor = mTextViewColor;
        this.mIsSelected = mIsSelected;
    }

    public String getImageName() {
        return mImageName;
    }

    public void setImageName(String mImageName) {
        this.mImageName = mImageName;
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

    public int getTextViewColor() {
        return mTextViewColor;
    }

    public void setTextViewColor(int mTextViewColor) {
        this.mTextViewColor = mTextViewColor;
    }

    public boolean isIsSelected() {
        return mIsSelected;
    }

    public void setIsSelected(boolean mIsSelected) {
        this.mIsSelected = mIsSelected;
    }
}
