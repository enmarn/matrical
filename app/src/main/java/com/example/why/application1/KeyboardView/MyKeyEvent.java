package com.example.why.application1.KeyboardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.why.application1.FocusEditText;
import com.example.why.application1.JsEngine;
import com.example.why.application1.NavigationView.Navigation;
import com.example.why.application1.R;
import com.example.why.application1.UnitVIew.UnitEvents;

import java.util.Date;

import static com.example.why.application1.UnitVIew.ParseStr.parserStr;

/**
 * Created by why on 18/5/21.
 */

enum InsertKeyType{
    NUM,ALPHABET,SYMBOL,OTHER
}
public class MyKeyEvent {
    static CheckBox upper;
    public MyKeyEvent(final View v){
        upper = (CheckBox) v.findViewById(R.id.UPPER);
        setAllCaps(false,v);
        setAllSymbol(v);
        if(upper!=null) {
            System.out.println("not NULL");
            upper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        MyKeyEvent.setAlphaBetString(b , v);
                }
            });
        }
    }
    private static void insertStr(String key,InsertKeyType type){
        if(type==InsertKeyType.ALPHABET){
            if(upper.isChecked()) key = key.toUpperCase();
        }
        FocusEditText.insertStringatCurser(key);
    }
    public static void ButtonEvent(int id,String str){
        if(id>=R.id.NUM_0&&id<=R.id.NUM_9){
            String a = String.valueOf((char)(id-R.id.NUM_0+'0'));
            insertStr(a,InsertKeyType.NUM);
            return;
        }else if(id>=R.id.ALPHA_A&&id<=R.id.ALPHA_Z){
            String a = String.valueOf((char)(id-R.id.ALPHA_A+'a'));
            insertStr(a,InsertKeyType.ALPHABET);
            return;
        }else if(id>=R.id.SYMBOL_0&&id<=R.id.SYMBOL_5){
            insertStr(String.valueOf(alpha_hot_symbol[id-R.id.SYMBOL_0]),InsertKeyType.SYMBOL);
            return;
        }else if(id>=R.id.FUN_00&&id<=R.id.FUN_14){
            insertStr(str,InsertKeyType.OTHER);
            FocusEditText.moveLeft();
            return;
        }else if(id>=R.id.FUN_CONST_00&&id<=R.id.FUN_CONST_01){
            insertStr(str,InsertKeyType.OTHER);
            return;
        }else if(id>=R.id.FOUR_SYMBOL_00&&id<=R.id.FOUR_SYMBOL_37){
            insertStr(str,InsertKeyType.SYMBOL);
            return;
        }else if(id>=R.id.FUN_OTHER_00&&id<=R.id.FUN_OTHER_07){
            insertStr(str,InsertKeyType.OTHER);
            return;
        }
        switch (id) {
            case R.id.OP_ANS:
                insertStr(JsEngine.getAns(),InsertKeyType.OTHER);
                return;
            case R.id.RUN:
            case R.id.EXE:
                run();
                return;
            case R.id.BLANK:
                insertStr(" ",InsertKeyType.SYMBOL);
                return;
            case R.id.OP_MOD:
                insertStr("%",InsertKeyType.SYMBOL);
                return;
            case R.id.RETURN1:
                insertStr("\n",InsertKeyType.OTHER);
                return;
            case R.id.OP_AND:
                insertStr("+",InsertKeyType.SYMBOL);
                return;
            case R.id.OP_DEC:
                insertStr("-",InsertKeyType.SYMBOL);
                return;
            case R.id.OP_DIV:
                insertStr("/",InsertKeyType.SYMBOL);
                return;
            case R.id.OP_DOT:
                insertStr(".",InsertKeyType.SYMBOL);
                return;
            case R.id.OP_EXP:
                insertStr("E",InsertKeyType.SYMBOL);
                return;
            case R.id.OP_IS:
                insertStr("=",InsertKeyType.SYMBOL);
                return;
            case R.id.OP_MUL:
                insertStr("*",InsertKeyType.SYMBOL);
                return;
            case R.id.LEFT_PARENTHESES:
                insertStr("()",InsertKeyType.SYMBOL);
                FocusEditText.moveLeft();
                return;
            case R.id.RIGHT_PARENTHESES:
                insertStr(")",InsertKeyType.SYMBOL);
                return;
            case R.id.SYMBOLQ:
                insertStr(";",InsertKeyType.SYMBOL);
                return;
            case R.id.DEL:
            case R.id.DEL1:
                FocusEditText.deleteStringatCurser();
                return;
            case R.id.AC:
                FocusEditText.clear();
                return;
            case R.id.MOVE_LEFT:
                FocusEditText.moveLeft();
                return;
            case R.id.MOVE_RIGHT:
                FocusEditText.moveRight();
                return;
            case R.id.REDO:
                FocusEditText.getEditText().setText(Redo);
        }
    }
    private static void setAllCaps(boolean flag,View v){
        int s=R.id.ALPHA_A,e=R.id.ALPHA_Z;
        for(int i=s;i<=e;i++){
            Button b = v.findViewById(i);
            b.setAllCaps(false);
        }
    }
    static char[]alpha_hot_symbol = {'{','}','\'','"',',',';'};
    private static void setAllSymbol(View v){
        int s=R.id.SYMBOL_0,e=R.id.SYMBOL_5;
        for(int i=s;i<=e;i++){
            Button b = v.findViewById(i);
            b.setText(String.valueOf(alpha_hot_symbol[i-s]));
        }
    }
    public static  void getString(Context context, String key,String[] value){
        SharedPreferences preferences = context.getSharedPreferences(
                "data", Context.MODE_PRIVATE);
        String regularEx = "#";
        String[] str;
//一开始是没值的，默认是空的
        String values = preferences.getString(key, "");
        System.out.println("getString() 函数－－ "+values);
        str = values.split(regularEx);
        for(int i=0;i<str.length;i++){
            value[i] = str[i];
        }
    }
    public static void setSharedPreference(String key, String[] values) {
        String regularEx = "#";
        String str = "";
        SharedPreferences sp = UnitEvents.mainActivity.getSharedPreferences("data", Context.MODE_PRIVATE);
        if (values != null && values.length > 0) {
            for (String value : values) {
                str += value;
                str += regularEx;
            }
            SharedPreferences.Editor et = sp.edit();
            et.putString(key, str);
            et.commit();
        }
    }
    public static void setAllFunction(){
        String[]values = Navigation.selectlist;
        int s=R.id.FUN_OTHER_00,e=R.id.FUN_OTHER_07;
        for(int i=s;i<=e;i++){
            Button b = UnitEvents.mainActivity.findViewById(i);
            if(b!=null)b.setText(values[i-s]);
        }
    }
    public static void setAllFunction(View v){
        String[]values = Navigation.selectlist;
        int s=R.id.FUN_OTHER_00,e=R.id.FUN_OTHER_07;
        for(int i=s;i<=e;i++){
            Button b = v.findViewById(i);
            if(b!=null)b.setText(values[i-s]);
        }
    }
    public static void setAllFunctionButtonText(View v, View.OnClickListener listener,String[]values){
        int s=R.id.SELECT0,e=R.id.SELECT7;
        for (int i=s;i<=e;i++){
            Button b = v.findViewById(i);
            b.setOnClickListener(listener);
            b.setAllCaps(false);
        }
        for(int i=s,j=7;i<=e&&j>0;i++,j--){
            Button b = v.findViewById(i);
            String str = values[i-s];
            b.setText(str);
        }
    }
    public static void setAlphaBetString(boolean upperCase, View v){
        int s=R.id.ALPHA_A,e=R.id.ALPHA_Z;
        for(int i=s;i<=e;i++){
            Button b = v.findViewById(i);
            String p = String.valueOf((char)(i-0x7f080001+'a'));
            if(upperCase) p = p.toUpperCase();
            b.setText(p);
        }
    }
    public static void run(){
        String str = FocusEditText.getEditText().getText().toString();
        programlog.append(new Date().toString());
        programlog.append("  :\n"+str+"\n\n\n");
        if(!UnitEvents.mainActivity.edittextflag) parserStr(str);
        else{
            Redo = str;
            SpannableString ans = JsEngine.runScript(str);
            str = str.replaceAll("\n","\n\t\t\t\t");
            logcat.append("<<\t"+str+"\n");
            logcat.append(ans);
            logcat.append("\n");
            logcat.setSelection(logcat.getText().length());
            FocusEditText.clear();
        }
    }
    public static String Redo = "";
    public static void runStr(String str){
        SpannableString ans = JsEngine.runScript(str);
        logcat.append(ans);
        logcat.append("\n");
        logcat.setSelection(logcat.getText().length());
    }
    public static EditText logcat;
    public static Editable programlog = new SpannableStringBuilder();
}
