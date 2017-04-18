package com.main.zk.cashbook.accounting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.main.zk.cashbook.R;

import java.util.ArrayList;

public class AccountingActivity extends AppCompatActivity {
    private ImageButton accounting_back_btn, accounting_ok_btn;
    private Button accounting_expenditure_btn, accounting_income_btn, accounting_account_btn, accounting_lost_time_btn, accounting_member_btn;
    private TextView accounting_expenditure_income_tv;
    private EditText accounting_expenditure_income_et, accounting_remarks_et;
    private GridView accounting_gv;
    private AccountingActivityListAdapter adapter_expenditure;
    private ArrayList<AccountingActivityListInfo> list_expenditure;
    private int old_position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting);

        init();
    }

    private void init(){
        accounting_back_btn = (ImageButton)findViewById(R.id.accounting_back_btn);
        accounting_ok_btn = (ImageButton)findViewById(R.id.accounting_ok_btn);
        accounting_expenditure_btn = (Button)findViewById(R.id.accounting_expenditure_btn);
        accounting_income_btn = (Button)findViewById(R.id.accounting_income_btn);
        accounting_account_btn = (Button)findViewById(R.id.accounting_account_btn);
        accounting_lost_time_btn = (Button)findViewById(R.id.accounting_lost_time_btn);
        accounting_member_btn = (Button)findViewById(R.id.accounting_member_btn);
        accounting_expenditure_income_tv = (TextView)findViewById(R.id.accounting_expenditure_income_tv);
        accounting_expenditure_income_et = (EditText)findViewById(R.id.accounting_expenditure_income_et);
        accounting_remarks_et = (EditText)findViewById(R.id.accounting_remarks_et);
        accounting_gv = (GridView)findViewById(R.id.accounting_gv);

        list_expenditure = new ArrayList<>();
        adapter_expenditure = new AccountingActivityListAdapter(this, list_expenditure);
        list_expenditure.add(new AccountingActivityListInfo(R.drawable.e_use_money, R.drawable.e_use_money_s, false));
        list_expenditure.add(new AccountingActivityListInfo(R.drawable.e_apparel, R.drawable.e_apparel_s, false));
        list_expenditure.add(new AccountingActivityListInfo(R.drawable.e_beauty_makeup, R.drawable.e_beauty_makeup_s, false));
        list_expenditure.add(new AccountingActivityListInfo(R.drawable.e_buy_food, R.drawable.e_buy_food_s, false));
        list_expenditure.add(new AccountingActivityListInfo(R.drawable.e_daily_necessities,R.drawable.e_daily_necessities_s, false));
        list_expenditure.add(new AccountingActivityListInfo(R.drawable.e_food, R.drawable.e_food_s,  false));
        list_expenditure.add(new AccountingActivityListInfo(R.drawable.e_fruit, R.drawable.e_fruit_s, false));
        list_expenditure.add(new AccountingActivityListInfo(R.drawable.e_red_envelopes, R.drawable.e_red_envelopes_s, false));
        list_expenditure.add(new AccountingActivityListInfo(R.drawable.e_snacks, R.drawable.e_snacks_s, false));
        list_expenditure.add(new AccountingActivityListInfo(R.drawable.e_traffic, R.drawable.e_traffic_s, false));
        accounting_gv.setAdapter(adapter_expenditure);
        adapter_expenditure.notifyDataSetChanged();

        accounting_gv.setOnItemClickListener(mOnItemClickListener);

    }

    private GridView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (old_position != -1)
                list_expenditure.get(old_position).setIsSelected(false);
            list_expenditure.get(position).setIsSelected(true);
            old_position = position;
            adapter_expenditure.notifyDataSetChanged();
        }
    };

}
