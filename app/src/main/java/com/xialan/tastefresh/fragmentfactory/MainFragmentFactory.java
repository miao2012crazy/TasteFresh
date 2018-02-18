package com.xialan.tastefresh.fragmentfactory;

import android.support.v4.app.Fragment;

import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.view.HaveBeenEvaluatedFragment;
import com.xialan.tastefresh.view.ToBeEvaluatedFragment;

import java.util.HashMap;
import java.util.Map;

/**  主工厂类
 * Created by Administrator on 2017/7/18.
 */
public class MainFragmentFactory {
    private static Map<Integer, BaseFragment> mFragments = new HashMap<>();
    public static Fragment getFragment(int position) {
        BaseFragment fragment = null;
        fragment = mFragments.get(position);  //在集合中取出来Fragment
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new ToBeEvaluatedFragment();
                    break;
                case 1:
                    fragment = new HaveBeenEvaluatedFragment();
                    break;
            }
        }
        return fragment;
    }
}
