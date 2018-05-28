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

public class KeyFragment1 extends Fragment {//implements View.OnClickListener {
    public static Fragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        Fragment fragment = new KeyFragment1();
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
            v = inflater.inflate(R.layout.numkeyboard, container, false);
//            v.findViewById(R.id.NUM_0).setOnClickListener(this);
//            v.findViewById(R.id.NUM_1).setOnClickListener(this);
//            v.findViewById(R.id.NUM_2).setOnClickListener(this);
//            v.findViewById(R.id.NUM_3).setOnClickListener(this);
//            v.findViewById(R.id.NUM_4).setOnClickListener(this);
//            v.findViewById(R.id.NUM_5).setOnClickListener(this);
//            v.findViewById(R.id.NUM_6).setOnClickListener(this);
//            v.findViewById(R.id.NUM_7).setOnClickListener(this);
//            v.findViewById(R.id.NUM_8).setOnClickListener(this);
//            v.findViewById(R.id.NUM_9).setOnClickListener(this);
//            v.findViewById(R.id.OP_AND).setOnClickListener(this);
//            v.findViewById(R.id.OP_ANS).setOnClickListener(this);
//            v.findViewById(R.id.OP_DEC).setOnClickListener(this);
//            v.findViewById(R.id.OP_DIV).setOnClickListener(this);
//            v.findViewById(R.id.OP_DOT).setOnClickListener(this);
//            v.findViewById(R.id.OP_EXP).setOnClickListener(this);
//            v.findViewById(R.id.OP_IS).setOnClickListener(this);
//            v.findViewById(R.id.OP_MUL).setOnClickListener(this);
//            v.findViewById(R.id.LEFT_PARENTHESES).setOnClickListener(this);
//            v.findViewById(R.id.RIGHT_PARENTHESES).setOnClickListener(this);
            this.activity = (MainActivity) getActivity();
        }
        return v;
    }
    MainActivity activity;
//    @Override
//    public void onClick(View view) {
//        activity.shack();
//        System.out.println("this is 22222");
//        switch (view.getId()) {
//            case R.id.NUM_0:break;
//            case R.id.NUM_1:break;
//            case R.id.NUM_2:break;
//            case R.id.NUM_3:break;
//            case R.id.NUM_4:break;
//            case R.id.NUM_5:break;
//            case R.id.NUM_6:break;
//            case R.id.NUM_7:break;
//            case R.id.NUM_8:break;
//            case R.id.NUM_9:break;
//            case R.id.OP_AND:break;
//            case R.id.OP_ANS:break;
//            case R.id.OP_DEC:break;
//            case R.id.OP_DIV:break;
//            case R.id.OP_DOT:break;
//            case R.id.OP_EXP:break;
//            case R.id.OP_IS:break;
//            case R.id.OP_MUL:break;
//            case R.id.LEFT_PARENTHESES:break;
//            case R.id.RIGHT_PARENTHESES:break;
//        }
//    }
}
