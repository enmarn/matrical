package com.example.why.application1.NavigationView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.why.application1.KeyboardView.MyKeyEvent;
import com.example.why.application1.R;
import com.example.why.application1.UnitVIew.UnitEvents;

import java.lang.reflect.Method;

/**
 * Created by why on 18/5/23.
 */

public class Navigation {
    public static void openHelpView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UnitEvents.mainActivity);
        View v = View.inflate(UnitEvents.mainActivity, R.layout.helperlayout,null);
        EditText ed = ((EditText)v.findViewById(R.id.HELPERTEXT));
        ed.setText("无");
        ((EditText)v.findViewById(R.id.HELPERTEXT)).setInputType(InputType.TYPE_NULL);
        builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setView(v);
        builder.show();
    }
    public static void openLogView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UnitEvents.mainActivity);
        View v = View.inflate(UnitEvents.mainActivity, R.layout.helperlayout,null);
        EditText ed = ((EditText)v.findViewById(R.id.HELPERTEXT));
        Class<EditText> cls = EditText.class;
        ed.setCursorVisible(false);
        Method method;
        try {
            method = cls.getMethod("setShowSoftInputOnFocus",boolean.class);
            method.setAccessible(true);
            method.invoke(ed, false);
        }catch (Exception e) {
            // TODO: handle exception
        }
        ed.setText(DataIO.readFiles("log.txt")+MyKeyEvent.programlog);
        ed.setSelection(ed.getText().length());
        builder.setNeutralButton("清空", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataIO.writeFiles("log.txt","");
                MyKeyEvent.programlog.clear();
            }
        });
        builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setView(v);
        builder.show();
    }
    public static void openForeView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UnitEvents.mainActivity);
        View v = View.inflate(UnitEvents.mainActivity, R.layout.helperlayout,null);
        EditText ed = (v.findViewById(R.id.HELPERTEXT));
        ed.setCursorVisible(true);
        final String str = DataIO.readCompileFiles();
        ed.setText(str);
        final EditText p = ed;
        builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if(!p.getText().toString().equals(str)){
                    DataIO.writeFiles("recompile.txt",p.getText().toString());
                    UnitEvents.mainActivity.newJSEngine();
                }
            }
        });
        builder.setView(v);
        builder.show();
    }
    public static void openSettingView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UnitEvents.mainActivity);
        final View v = View.inflate(UnitEvents.mainActivity, R.layout.selectlayout,null);
        MyKeyEvent.getString(UnitEvents.mainActivity,"funcs",selectlist);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button a=(Button)view;
                EditText b = v.findViewById(R.id.SELECT9);
                a.setText(b.getText());
                selectlist[view.getId()-R.id.SELECT0] = a.getText().toString();
            }
        };
        MyKeyEvent.setAllFunctionButtonText(v,listener,selectlist);
        builder.setNegativeButton("应用", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                MyKeyEvent.setAllFunction();
                MyKeyEvent.setSharedPreference("funcs",selectlist);
            }
        });
        builder.setView(v);
        builder.show();
    }
    public static String[]selectlist= new String[]{"","","","","","","",""};
}
