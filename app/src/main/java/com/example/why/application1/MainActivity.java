package com.example.why.application1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.why.application1.BackgroundViewPager.MyFragmentAdapter;
import com.example.why.application1.KeyboardView.KeyFragmentAdapter;
import com.example.why.application1.KeyboardView.MyKeyEvent;
import com.example.why.application1.NavigationView.DataIO;
import com.example.why.application1.UnitVIew.UnitDialog;
import com.example.why.application1.UnitVIew.UnitEvents;

import li.yohan.parallax.ParallaxViewPager;

import static com.example.why.application1.ActivityEvents.putIntoCilpboard;
import static com.example.why.application1.ActivityEvents.toast;

public class MainActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new UnitEvents(this);
        setContentView(R.layout.activity_main);
        initMainActivity();
    }
    /**
     * 初始化各个listView、ViewPager的Adaper
     */
    protected void initMainActivity(){
        keyView = findViewById(R.id.keyBoardView);
        keyView.setVisibility(View.GONE);
        keyViewPager = findViewById(R.id.keyBoardViewPager);
        keyViewPager.setAdapter(new KeyFragmentAdapter(getSupportFragmentManager()));
        jsEngine = new JsEngine();
        ParallaxViewPager mPager = ((ParallaxViewPager) findViewById(R.id.viewPager));
        mPager.setSpeedRatio(0.20f);//偏移比例
        mPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),this));
        mPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(isKeyBoardShow()){
                    cancelKeyBoard();
                    FocusEditText.getEditText().clearFocus();
                }
                return false;
            }
        });
        ((Button)findViewById(R.id.MOVE_LEFT)).setText("<<");
        ((Button)findViewById(R.id.MOVE_RIGHT)).setText(">>");
    }
    public void newJSEngine(){
        jsEngine = new JsEngine();
        JsEngine.recompile();
    }
    //js引擎，可以运行js代码
    JsEngine jsEngine;
    //键盘所在的那个Viewpager
    ViewPager keyViewPager;
    //整个键盘视图
    LinearLayout keyView;
    //键盘是否可见
    boolean KeyBoardShow = false;
    //焦点文本框是否为单位区
    public boolean edittextflag;
    //是否显示键盘
    public boolean isKeyBoardShow(){
        return KeyBoardShow;
    }
    //显示键盘
    public void showKeyBoard(){
        keyView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_enter_anim));
        keyView.setVisibility(View.VISIBLE);
        keyViewPager.setCurrentItem(1);
        KeyBoardShow = true;
    }
    //收回键盘
    public void cancelKeyBoard(){
        keyView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_exit_anim));
        keyView.setVisibility(View.GONE);
        KeyBoardShow = false;
    }
    public void onClick(View view) {
        ActivityEvents.shack();
        edittextflag = FocusEditText.getId()==R.id.showText;
        MyKeyEvent.ButtonEvent(view.getId(),((Button)view).getText().toString());
    }

    public void copyButton(View view){
        ActivityEvents.shack();
        View v = (View)view.getParent();
        EditText e = v.findViewById(R.id.unitEditView);
        String str = e.getText().toString();
        putIntoCilpboard(str);
        toast(" put into clipboard");
    }
    @Override
    protected void onPause() {
        DataIO.appendFiles("log.txt",MyKeyEvent.programlog.toString());
        MyKeyEvent.programlog.clear();
        super.onPause();
    }

    //弹出单选框
    public void clickSingleSelect(View v) {
        UnitDialog.SingleSelect();
    }
    //弹出导航框
    public void clickNavigationWindow(View v){ UnitDialog.NavigationWindow();}
}
