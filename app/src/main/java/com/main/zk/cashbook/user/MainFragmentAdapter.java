package com.main.zk.cashbook.user;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by ZK on 2017/3/28.
 */

public class MainFragmentAdapter extends FragmentStatePagerAdapter {
//    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentList;
    public MainFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
//        this.viewPager = viewPager;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

//    @Override
//    public void finishUpdate(ViewGroup container)
//    {
//        super.finishUpdate(container);
//        int currentItem=viewPager.getCurrentItem();
//        if (currentItem == currenttab)
//        {
//            return ;
//        }
//        imageMove(mViewPager.getCurrentItem());
//        currenttab=mViewPager.getCurrentItem();
//    }
}
