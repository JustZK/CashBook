package com.main.zk.cashbook.accounting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.zk.cashbook.R;

import java.util.ArrayList;

/**
 * Created by ZK on 2017/4/18.
 */

public class AccountingActivityListAdapter extends BaseAdapter {
    private ArrayList<AccountingActivityListInfo> list;
    private LayoutInflater inflater;
    private Context mContext;

    public AccountingActivityListAdapter(Context mContext, ArrayList<AccountingActivityListInfo> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final AccountingActivityListInfo accountingActivityListInfo = list.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.accounting_expenditure_list_item, null);
            viewHolder = new ViewHolder((ImageView) convertView.findViewById(R.id.accounting_expenditure_list_item_iv),
                    (TextView) convertView.findViewById(R.id.accounting_expenditure_list_item_tv));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.accounting_expenditure_list_item_tv.setText(accountingActivityListInfo.getImageName());
        if (!accountingActivityListInfo.isIsSelected()) {
            viewHolder.accounting_expenditure_list_item_iv.setImageDrawable(mContext.getResources()
                    .getDrawable(accountingActivityListInfo.getImageViewSrc()));
            viewHolder.accounting_expenditure_list_item_tv.setTextColor(mContext.getResources().getColor(R.color.light_grey));
        }else {
            viewHolder.accounting_expenditure_list_item_iv.setImageDrawable(mContext.getResources()
                    .getDrawable(accountingActivityListInfo.getImageViewSelectedSrc()));
            viewHolder.accounting_expenditure_list_item_tv.setTextColor(mContext.getResources().getColor(accountingActivityListInfo.getTextViewColor()));
        }


        return convertView;
    }

    private class ViewHolder {
        protected ImageView accounting_expenditure_list_item_iv;
        protected TextView accounting_expenditure_list_item_tv;

        public ViewHolder(ImageView accounting_expenditure_list_item_iv,
                          TextView accounting_expenditure_list_item_tv) {
            this.accounting_expenditure_list_item_iv = accounting_expenditure_list_item_iv;
            this.accounting_expenditure_list_item_tv = accounting_expenditure_list_item_tv;
        }

    }
}
