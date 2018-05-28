package com.example.why.application1.BackgroundViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.why.application1.UnitVIew.UnitEvents;
import com.example.why.application1.R;

/**
 * Created by why on 18/5/3.
 */

public class MyFragment1 extends Fragment {
    public static Fragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        Fragment fragment = new MyFragment1();
        fragment.setArguments(bundle);
        return fragment;
    }
    int index;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("index");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentlayout1, container, false);
        listView = (ListView) v.findViewById(R.id.unitView);
        UnitEvents.setUnitAdapter(v.getContext());
        listView.setAdapter(UnitEvents.unitadapter);
        return v;
    }
    ListView listView;
}
