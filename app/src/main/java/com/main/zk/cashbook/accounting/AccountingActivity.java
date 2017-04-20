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
import com.main.zk.cashbook.db.DBHelper;
import com.main.zk.cashbook.util.SharedPreferencesUtil;
import com.main.zk.cashbook.util.SharedPreferencesUtil.Key;
import com.main.zk.cashbook.util.TimeOpera;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;

public class AccountingActivity extends AppCompatActivity {
    private ImageButton accounting_back_btn, accounting_ok_btn;
    private Button accounting_expenditure_btn, accounting_income_btn, accounting_account_btn, accounting_lost_time_btn, accounting_member_btn;
    private TextView accounting_expenditure_income_tv;
    private EditText accounting_expenditure_income_et, accounting_remarks_et;
    private GridView accounting_gv;
    private PercentLinearLayout accounting_expenditure_income_pll;
    private AccountingActivityListAdapter adapter_expenditure;
    private ArrayList<AccountingActivityListInfo> list_expenditure;
    private int old_position = 0;
    private DBHelper dbHelper;
    private SharedPreferencesUtil spUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting);

        init();
    }

    private void init(){
        dbHelper = DBHelper.getInstance(this);
        spUtil = SharedPreferencesUtil.getInstance(this);
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
        accounting_expenditure_income_pll = (PercentLinearLayout)findViewById(R.id.accounting_expenditure_income_pll);

        list_expenditure = new ArrayList<>();
        adapter_expenditure = new AccountingActivityListAdapter(this, list_expenditure);
        list_expenditure.add(new AccountingActivityListInfo(getString(R.string.e_use_money), R.drawable.e_use_money,
                R.drawable.e_use_money_s, R.color.e_use_money, true));
        list_expenditure.add(new AccountingActivityListInfo(getString(R.string.e_apparel), R.drawable.e_apparel,
                R.drawable.e_apparel_s, R.color.e_apparel, false));
        list_expenditure.add(new AccountingActivityListInfo(getString(R.string.e_beauty_makeup), R.drawable.e_beauty_makeup,
                R.drawable.e_beauty_makeup_s, R.color.e_beauty_makeup, false));
        list_expenditure.add(new AccountingActivityListInfo(getString(R.string.e_buy_food), R.drawable.e_buy_food,
                R.drawable.e_buy_food_s, R.color.e_buy_food, false));
        list_expenditure.add(new AccountingActivityListInfo(getString(R.string.e_daily_necessities), R.drawable.e_daily_necessities,
                R.drawable.e_daily_necessities_s, R.color.e_daily_necessities, false));
        list_expenditure.add(new AccountingActivityListInfo(getString(R.string.e_food), R.drawable.e_food,
                R.drawable.e_food_s, R.color.e_food, false));
        list_expenditure.add(new AccountingActivityListInfo(getString(R.string.e_fruit), R.drawable.e_fruit,
                R.drawable.e_fruit_s, R.color.e_fruit, false));
        list_expenditure.add(new AccountingActivityListInfo(getString(R.string.e_red_envelopes), R.drawable.e_red_envelopes,
                R.drawable.e_red_envelopes_s, R.color.e_red_envelopes, false));
        list_expenditure.add(new AccountingActivityListInfo(getString(R.string.e_snacks), R.drawable.e_snacks,
                R.drawable.e_snacks_s, R.color.e_snacks, false));
        list_expenditure.add(new AccountingActivityListInfo(getString(R.string.e_traffic), R.drawable.e_traffic,
                R.drawable.e_traffic_s, R.color.e_traffic, false));
        accounting_gv.setAdapter(adapter_expenditure);
        adapter_expenditure.notifyDataSetChanged();

        accounting_gv.setOnItemClickListener(mOnItemClickListener);
        accounting_ok_btn.setOnClickListener(mOnClickListener);

        dbHelper.queryExpenseRecord();
    }

    private GridView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (old_position != position) {
                accounting_expenditure_income_pll.setBackgroundColor(getResources()
                        .getColor(list_expenditure.get(position).getTextViewColor()));
                accounting_expenditure_income_tv.setText(list_expenditure.get(position).getImageName());
                list_expenditure.get(old_position).setIsSelected(false);
                list_expenditure.get(position).setIsSelected(true);
                old_position = position;
                adapter_expenditure.notifyDataSetChanged();
            }
        }
    };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.accounting_ok_btn:
                    dbHelper.insertExpenseRecord(spUtil.getString(Key.UserName.name(), ""),
                            TimeOpera.getStringDateShort(),
                            list_expenditure.get(old_position).getImageName(),
                            list_expenditure.get(old_position).getImageViewSelectedSrc(),
                            list_expenditure.get(old_position).getTextViewColor(),
                            Integer.parseInt(accounting_expenditure_income_et.getText().toString()),
                            "CASH", "ZK");
                    break;
                default:
                    break;
            }
        }
    };

}
