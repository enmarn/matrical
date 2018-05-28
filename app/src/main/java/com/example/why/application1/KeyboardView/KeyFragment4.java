package com.example.why.application1.KeyboardView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.why.application1.R;

/**
 * Created by why on 18/5/3.
 */

/**
 * test comkeyboard fragment(no using space now)
 */
public class KeyFragment4 extends Fragment{
    public static Fragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        Fragment fragment = new KeyFragment4();
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
            v = inflater.inflate(R.layout.symbolkeyboard, container, false);
            setAllSymbol(v);
        }
        return v;
    }
    static char[]alpha_symbol = {'!','@','#','$','%','^','&','*',
            '{','}','|',':','"','?','_','+',
            '[',']','\\',';','\'','/','-','=',
            '<','>','(',')',',','.','`','~'
    };
    private static void setAllSymbol(View v){
        int s=R.id.FOUR_SYMBOL_00,e=R.id.FOUR_SYMBOL_37;
        for(int i=s;i<=e;i++){
            Button b = v.findViewById(i);
            b.setText(String.valueOf(alpha_symbol[i-s]));
        }
    }
}
