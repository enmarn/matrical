package com.example.why.application1.KeyboardView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by why on 18/5/3.
 */
public class KeyFragmentAdapter extends FragmentStatePagerAdapter{
    public static final int CNT_FGM = 4;//num of fragment
    private List<Fragment> frgms = new ArrayList<>();
    public KeyFragmentAdapter(FragmentManager fm) {
        super(fm);
        frgms.add(KeyFragment2.newInstance(3));
        frgms.add(KeyFragment1.newInstance(1));
        //frgms.add(KeyFragment2.newInstance(2));
        frgms.add(KeyFragment3.newInstance(2));
        frgms.add(KeyFragment4.newInstance(4));

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
