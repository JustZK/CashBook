package com.main.zk.cashbook.accounting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.zk.cashbook.R;

import java.util.ArrayList;

/**
 * Created by ZK on 2017/4/8.
 */

public class AccountingListAdapter extends BaseAdapter {
    private ArrayList<AccountingListInfo> list;
    private LayoutInflater inflater;
    private Context mContext;
    private OnClickEventListener onClickEventListener;

    public AccountingListAdapter(Context mContext, ArrayList<AccountingListInfo> list) {
        this.mContext = mContext;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final AccountingListInfo accountingListInfo = list.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.accounting_list_item, null);
            viewHolder = new ViewHolder((ImageButton) convertView.findViewById(R.id.accounting_item_delete),
                    (ImageView) convertView.findViewById(R.id.accounting_item_node),
                    (ImageView) convertView.findViewById(R.id.accounting_item_status),
                    (TextView) convertView.findViewById(R.id.accounting_item_income),
                    (TextView) convertView.findViewById(R.id.accounting_item_expenditure),
                    (ImageButton) convertView.findViewById(R.id.accounting_item_modify));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (accountingListInfo.isShowButton()) {
            viewHolder.accounting_item_delete.setVisibility(View.VISIBLE);
            viewHolder.accounting_item_modify.setVisibility(View.VISIBLE);
            viewHolder.accounting_item_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickEventListener != null){
                        onClickEventListener.onDeleteEvent(position, accountingListInfo.getID());
                    }
                }
            });
            viewHolder.accounting_item_modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickEventListener != null){
                        onClickEventListener.onModifyEvent(position, accountingListInfo.getID());
                    }
                }
            });
        } else {
            viewHolder.accounting_item_delete.setVisibility(View.INVISIBLE);
            viewHolder.accounting_item_modify.setVisibility(View.INVISIBLE);
        }
        if (accountingListInfo.getTime() != null) {
            viewHolder.accounting_item_delete.setVisibility(View.INVISIBLE);
            viewHolder.accounting_item_modify.setVisibility(View.INVISIBLE);
            viewHolder.accounting_item_node.setVisibility(View.VISIBLE);
            viewHolder.accounting_item_status.setVisibility(View.INVISIBLE);
            viewHolder.accounting_item_income.setVisibility(View.VISIBLE);
            viewHolder.accounting_item_expenditure.setVisibility(View.VISIBLE);
            if (accountingListInfo.getLave() >= 0) {
                viewHolder.accounting_item_expenditure.setText(accountingListInfo.getTime());
                viewHolder.accounting_item_income.setText("" + accountingListInfo.getLave());
            } else {
                viewHolder.accounting_item_income.setText(accountingListInfo.getTime());
                viewHolder.accounting_item_expenditure.setText("" + accountingListInfo.getLave());
            }
        } else {
            viewHolder.accounting_item_node.setVisibility(View.INVISIBLE);
            viewHolder.accounting_item_status.setVisibility(View.VISIBLE);
            if (accountingListInfo.getIncome() > 0) {
                viewHolder.accounting_item_expenditure.setVisibility(View.INVISIBLE);
                viewHolder.accounting_item_income.setVisibility(View.VISIBLE);
                viewHolder.accounting_item_status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.income));
                viewHolder.accounting_item_income.setText(
                        mContext.getResources().getString(R.string.accounting_item_income) +
                                accountingListInfo.getIncome());
            }
            if (accountingListInfo.getExpenditure() > 0) {
                viewHolder.accounting_item_expenditure.setVisibility(View.VISIBLE);
                viewHolder.accounting_item_income.setVisibility(View.INVISIBLE);
                viewHolder.accounting_item_status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.expenditure));
                viewHolder.accounting_item_expenditure.setText(
                        mContext.getResources().getString(R.string.accounting_item_expenditure) +
                                accountingListInfo.getExpenditure());
            }

            viewHolder.accounting_item_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickEventListener != null){
                        onClickEventListener.onStatusEvent(position, accountingListInfo.getID());
                    }
                }
            });
        }
        return convertView;
    }

    private class ViewHolder {
        protected ImageButton accounting_item_delete;
        protected ImageView accounting_item_node;
        protected ImageView accounting_item_status;
        protected TextView accounting_item_income;
        protected TextView accounting_item_expenditure;
        protected ImageButton accounting_item_modify;

        public ViewHolder(ImageButton accounting_item_delete, ImageView accounting_item_node,
                          ImageView accounting_item_status, TextView accounting_item_income,
                          TextView accounting_item_expenditure, ImageButton accounting_item_modify) {
            this.accounting_item_delete = accounting_item_delete;
            this.accounting_item_node = accounting_item_node;
            this.accounting_item_status = accounting_item_status;
            this.accounting_item_income = accounting_item_income;
            this.accounting_item_expenditure = accounting_item_expenditure;
            this.accounting_item_modify = accounting_item_modify;
        }

    }

//    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.accounting_item_status:
//                    break;
//                case R.id.accounting_item_delete:
//                    break;
//                case R.id.accounting_item_modify:
//                    break;
//                default:
//                    break;
//            }
//        }
//    };

    public interface OnClickEventListener{
        void onStatusEvent(int position, int ID);
        void onDeleteEvent(int position, int ID);
        void onModifyEvent(int position, int ID);
    }

    public void setOnClickEventListener(OnClickEventListener onClickEventListener){
        this.onClickEventListener = onClickEventListener;
    }
}
