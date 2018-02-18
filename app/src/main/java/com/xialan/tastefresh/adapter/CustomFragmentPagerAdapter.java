package com.xialan.tastefresh.adapter;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xialan.tastefresh.base.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2018/2/3.
 */

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> mFragmentList;

    public CustomFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.mFragmentList=fragmentList;
    }

    @Override
    public BaseFragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
