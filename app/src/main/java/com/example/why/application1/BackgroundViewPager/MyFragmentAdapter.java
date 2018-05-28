package com.example.why.application1.BackgroundViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.example.why.application1.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by why on 18/5/3.
 */
public class MyFragmentAdapter extends FragmentStatePagerAdapter{
    public static final int CNT_FGM = 2;//num of fragment
    private List<Fragment> frgms = new ArrayList<>(CNT_FGM);
    public MyFragmentAdapter(FragmentManager fm,MainActivity mainActivity) {
        super(fm);
        frgms.add(MyFragment1.newInstance(1));
        frgms.add(MyFragment2.newInstance(2));
    }

    @Override
    public int getCount() {
        return CNT_FGM;
    }
    @Override
    public Fragment getItem(int arg0) {
        return frgms.get(arg0);
    }


}
