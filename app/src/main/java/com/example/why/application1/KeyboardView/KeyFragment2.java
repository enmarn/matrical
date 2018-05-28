package com.example.why.application1.KeyboardView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.why.application1.R;
import com.example.why.application1.UnitVIew.UnitEvents;

import static com.example.why.application1.NavigationView.Navigation.selectlist;

/**
 * Created by why on 18/5/3.
 */
/**
 * test comkeyboard fragment(no using space now)
 */
public class KeyFragment2 extends Fragment{
    public static Fragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        Fragment fragment = new KeyFragment2();
        fragment.setArguments(bundle);
        return fragment;
    }
    int index;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("index");
    }
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.funkeyboard, container, false);
        }
        MyKeyEvent.getString(UnitEvents.mainActivity,"funcs",selectlist);
        MyKeyEvent.setAllFunction(v);
        return v;
    }
}
