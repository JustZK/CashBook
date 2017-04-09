package com.main.zk.cashbook.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.main.zk.cashbook.R;
import com.main.zk.cashbook.accounting.AccountingFragment;
import com.main.zk.cashbook.other.OtherFragment;
import com.main.zk.cashbook.report.ReportFragment;
import com.main.zk.cashbook.ui.OnTouchEventViewPager;
import com.main.zk.cashbook.util.SharedPreferencesUtil;
import com.main.zk.cashbook.util.SharedPreferencesUtil.Key;
import com.main.zk.cashbook.util.ToastUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private OnTouchEventViewPager main_vp;
    private BottomNavigationView main_navigation;
    private MenuItem menuItem;
    private ArrayList<Fragment> fragments;
    private TextView main_user_name_tv, main_email_tv;
    private SharedPreferencesUtil spUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        spUtil = SharedPreferencesUtil.getInstance(this);
        main_vp = (OnTouchEventViewPager) findViewById(R.id.main_vp);
        main_navigation = (BottomNavigationView) findViewById(R.id.main_navigation);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationViewListener);
        main_user_name_tv = (TextView) navigationView.getHeaderView(0).findViewById(R.id.main_user_name_tv);
        main_email_tv = (TextView) navigationView.getHeaderView(0).findViewById(R.id.main_email_tv);
        fragments = new ArrayList<>();
        fragments.add(new AccountingFragment());
        fragments.add(new ReportFragment());
        fragments.add(new OtherFragment());
        main_vp.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), fragments));
        BottomNavigationViewHelper.disableShiftMode(main_navigation);
        main_vp.addOnPageChangeListener(mOnPageChangeListener);
        main_vp.setonRefreshListener(mOnRefreshListener);
        main_navigation.setOnNavigationItemSelectedListener(main_navigationListener);
        main_user_name_tv.setText(spUtil.getString(Key.UserName.name(), ""));
        main_email_tv.setText(spUtil.getString(Key.Email.name(), this.getResources().getString(R.string.enter_the_mailbox)));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private NavigationView.OnNavigationItemSelectedListener navigationViewListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_myself:
                    ToastUtil.makeText(MainActivity.this, "nav_myself");
                    break;
                case R.id.nav_add_item:
                    ToastUtil.makeText(MainActivity.this, "nav_add_item");
                    break;
                case R.id.nav_set:
                    ToastUtil.makeText(MainActivity.this, "nav_set");
                    break;
                case R.id.nav_share:
                    ToastUtil.makeText(MainActivity.this, "nav_share");
                    break;
                case R.id.nav_send:
                    ToastUtil.makeText(MainActivity.this, "nav_send");
                    break;
                default:
                    break;
            }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    private OnTouchEventViewPager.OnPageChangeListener mOnPageChangeListener
            = new OnTouchEventViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (menuItem != null) {
                menuItem.setChecked(false);
            } else {
                main_navigation.getMenu().getItem(0).setChecked(false);
            }
            menuItem = main_navigation.getMenu().getItem(position);
            menuItem.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    private OnTouchEventViewPager.OnRefreshListener mOnRefreshListener = new OnTouchEventViewPager.OnRefreshListener() {
        @Override
        public void onRefresh() {
            animationDirectionStop();
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener main_navigationListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_accounting:
                    main_vp.setCurrentItem(0, true);
                    break;
                case R.id.navigation_report:
                    main_vp.setCurrentItem(1, true);
                    break;
                case R.id.navigation_other:
                    main_vp.setCurrentItem(2, true);
                    break;
                default:
                    break;
            }
            return true;
        }

    };

    private void animationDirectionStop(){
        if (fragments != null)
            if (fragments.get(0) != null)
                if (((AccountingFragment) fragments.get(0)).getAnimationDirectionIsRunning())
                    ((AccountingFragment) fragments.get(0)).animationDirectionStop();
    }
}
