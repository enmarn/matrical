package com.example.why.application1.BackgroundViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.why.application1.JsEngine;
import com.example.why.application1.KeyboardView.MyKeyEvent;
import com.example.why.application1.R;
import com.example.why.application1.UnitVIew.UnitEvents;

import java.lang.reflect.Method;

/**
 * Created by why on 18/5/3.
 */

public class MyFragment2 extends Fragment {
    public static Fragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        Fragment fragment = new MyFragment2();
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
        View v = inflater.inflate(R.layout.fragmentlayout2, container, false);
        EditText codetext = v.findViewById(R.id.codeText);
        codetext.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                System.out.println(menu.getItem(1).toString());
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });
        MyKeyEvent.logcat = codetext;
        JsEngine.recompile();
        Class<EditText> cls = EditText.class;
        Method method;
        EditText showtext = v.findViewById(R.id.showText);
        showtext.setOnFocusChangeListener(UnitEvents.listen1);
        try {
            method = cls.getMethod("setShowSoftInputOnFocus",boolean.class);
            method.setAccessible(true);
            method.invoke(showtext, false);
            method.setAccessible(false);
            method.invoke(codetext, false);
        }catch (Exception e) {
            // TODO: handle exception
        }
        return v;
    }
}
