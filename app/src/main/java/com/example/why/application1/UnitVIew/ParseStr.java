package com.example.why.application1.UnitVIew;

import android.widget.EditText;

import com.example.why.application1.ActivityEvents;
import com.example.why.application1.FocusEditText;

/**
 * Created by why on 18/5/21.
 */

public class ParseStr {
    public static void parserStr(String str){
        int flag = UnitEvents.UnitFlag;
        if(flag==4) parseUnit4Str(str);      //解析字符串
        else if(flag==3) parseUnit3Str(str);
        else if(flag==5) parseUnit5Str(str);
    }
    private static void parseUnit3Str(String str){//温度
        EditText foucsEditText = FocusEditText.getEditText();
        if(str.equals("E")||str.equals("e")||str.equals("+")||str.equals("-")) return;
        try {
            double a = Double.parseDouble(foucsEditText.getText().toString());
            UnitFru.parseTmp(UnitEvents.unitlist,foucsEditText,a);
            System.out.println(a);
        } catch(NumberFormatException e){
            ActivityEvents.toast("Error Input");
        }
    }
    private static void parseUnit4Str(String str){
        EditText foucsEditText = FocusEditText.getEditText();
        if(str.equals("E")||str.equals("e")||str.equals("+")||str.equals("-")) return;
        try {
            double a = Double.parseDouble(foucsEditText.getText().toString());
            UnitFru.parse(UnitEvents.unitlist,foucsEditText,a);
            System.out.println(a);
        } catch(NumberFormatException e){
            ActivityEvents.toast("Error Input");
        }
    }
    private static void parseUnit5Str(String str){//计算机存储
        EditText foucsEditText = FocusEditText.getEditText();
        if(str.equals("E")||str.equals("e")||str.equals("+")||str.equals("-")) return;
        try {
            double a = Double.parseDouble(foucsEditText.getText().toString());
            UnitFru.parseCom(UnitEvents.unitlist,foucsEditText,a);
            System.out.println(a);
        } catch(NumberFormatException e){
            ActivityEvents.toast("Error Input");
        }
    }

}
