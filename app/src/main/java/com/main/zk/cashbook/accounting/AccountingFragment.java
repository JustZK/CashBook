package com.main.zk.cashbook.accounting;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.main.zk.cashbook.R;
import com.main.zk.cashbook.ui.PullRefreshListView;
import com.main.zk.cashbook.util.ToastUtil;

import java.util.ArrayList;


/**
 * Created by ZK on 2017/3/28.
 */
public class AccountingFragment extends Fragment {
    private View view;
    private ImageView accounting_write_iv;
    private AnimationDrawable animationDirection;
    private PullRefreshListView accounting_lv;
    private ArrayList<AccountingListInfo> list;
    private AccountingListAdapter accountingListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.accounting_fragment,container,false);

        init();

        return view;
    }

    private void init(){
        accounting_write_iv = (ImageView) view.findViewById(R.id.accounting_write_iv);
        accounting_lv = (PullRefreshListView) view.findViewById(R.id.accounting_lv);
        animationDirection = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_write);
        accounting_write_iv.setOnClickListener(mOnClickListener);
        accounting_lv.setonRefreshListener(mOnRefreshListener);

        list = new ArrayList<>();
        accountingListAdapter = new AccountingListAdapter(getContext(), list);
        accounting_lv.setAdapter(accountingListAdapter);
        accountingListAdapter.setOnClickEventListener(mOnClickEventListener);

        list.add(new AccountingListInfo(false, -1, "今天", -55, -1, -1));
        list.add(new AccountingListInfo(false, 0, null, -1, -1, 66));
        list.add(new AccountingListInfo(false, 1, null, -1,-1, 77));
        list.add(new AccountingListInfo(false, 2, null, -1,88, -1));
        accountingListAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.accounting_write_iv:
                    ToastUtil.makeText(getContext(), "accounting_write_iv");
                    break;
                default:
                    break;
            }
        }
    };

    private AccountingListAdapter.OnClickEventListener mOnClickEventListener = new AccountingListAdapter.OnClickEventListener() {
        @Override
        public void onStatusEvent(int position, int ID) {
            if (!list.get(position).isShowButton()) {
                for (AccountingListInfo accountingListInfo : list)
                    accountingListInfo.setShowButton(false);
                list.get(position).setShowButton(true);
            }else
                list.get(position).setShowButton(false);
            accountingListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onDeleteEvent(int position, int ID) {

        }

        @Override
        public void onModifyEvent(int position, int ID) {

        }
    };

    private PullRefreshListView.OnRefreshListener mOnRefreshListener = new PullRefreshListView.OnRefreshListener() {
        @Override
        public void onRefresh(boolean isStartAnimation, boolean isOnRefresh) {
            if (isStartAnimation){
                if (!animationDirection.isRunning()) {
                    accounting_write_iv.setImageDrawable(animationDirection);
                    animationDirection.start();
                }
            }else {
                if (animationDirection.isRunning()) {
                    animationDirectionStop();
                }
            }
            if(isOnRefresh){
                ToastUtil.makeText(getContext(), "accounting_write_iv");
            }

        }
    };

    public boolean getAnimationDirectionIsRunning(){
        if (animationDirection != null)
            return animationDirection.isRunning();
        else
            return false;
    };

    public void animationDirectionStop(){
        animationDirection.stop();
        accounting_write_iv.setImageResource(R.drawable.write);
    }

}
