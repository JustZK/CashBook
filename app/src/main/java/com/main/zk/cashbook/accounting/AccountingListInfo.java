package com.main.zk.cashbook.accounting;

/**
 * Created by ZK on 2017/4/8.
 */

public class AccountingListInfo {
    private boolean mShowButton; //是否显示删除、修改按钮
    private int mID;
    private String mTime; //时间
    private float mLave; //剩余（赚、亏）
    private float mIncome; //收入
    private float mExpenditure; //支出

    public AccountingListInfo (boolean mShowButton,int mID, String mTime, float mLave, float mIncome, float mExpenditure){
        this.mShowButton = mShowButton;
        this.mID = mID;
        this.mTime = mTime;
        this.mLave = mLave;
        this.mIncome = mIncome;
        this.mExpenditure = mExpenditure;
    }

    public boolean isShowButton() {
        return mShowButton;
    }

    public void setShowButton(boolean mShowButton) {
        this.mShowButton = mShowButton;
    }

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public float getLave() {
        return mLave;
    }

    public void setLave(float mLave) {
        this.mLave = mLave;
    }

    public float getIncome() {
        return mIncome;
    }

    public void setIncome(float mIncome) {
        this.mIncome = mIncome;
    }

    public float getExpenditure() {
        return mExpenditure;
    }

    public void setExpenditure(float mExpenditure) {
        this.mExpenditure = mExpenditure;
    }
}
