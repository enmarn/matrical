package com.example.why.application1.KeyboardView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.why.application1.MainActivity;
import com.example.why.application1.R;

/**
 * Created by why on 18/5/3.
 */

public class KeyFragment3 extends Fragment {//implements View.OnClickListener{
    public static Fragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        Fragment fragment = new KeyFragment3();
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
            v = inflater.inflate(R.layout.alphakeyboard, container, false);
            new MyKeyEvent(v);
            this.activity = (MainActivity) getActivity();
        }
        return v;
    }
    MainActivity activity;
//    @Override
//    public void onClick(View view) {
//        activity.shack();
//
//        switch (view.getId()){
//            case R.id.ALPHA_A:break;
//            case R.id.ALPHA_B:break;
//            case R.id.ALPHA_C:break;
//            case R.id.ALPHA_D:break;
//            case R.id.ALPHA_E:break;
//            case R.id.ALPHA_F:break;
//            case R.id.ALPHA_G:break;
//            case R.id.ALPHA_H:break;
//            case R.id.ALPHA_I:break;
//            case R.id.ALPHA_J:break;
//            case R.id.ALPHA_K:break;
//            case R.id.ALPHA_L:break;
//            case R.id.ALPHA_M:break;
//            case R.id.ALPHA_N:break;
//            case R.id.ALPHA_O:break;
//            case R.id.ALPHA_P:break;
//            case R.id.ALPHA_Q:break;
//            case R.id.ALPHA_R:break;
//            case R.id.ALPHA_S:break;
//            case R.id.ALPHA_T:break;
//            case R.id.ALPHA_U:break;
//            case R.id.ALPHA_V:break;
//            case R.id.ALPHA_W:break;
//            case R.id.ALPHA_X:break;
//            case R.id.ALPHA_Y:break;
//            case R.id.ALPHA_Z:break;
//        }
//    }
}
