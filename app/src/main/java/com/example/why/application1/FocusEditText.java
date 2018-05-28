package com.example.why.application1;

import android.widget.EditText;

import com.example.why.application1.UnitVIew.UnitEvents;

import static com.example.why.application1.UnitVIew.ParseStr.parserStr;

/**
 * Created by why on 18/5/21.
 */

public class FocusEditText {
    //处于焦点的文本框
    private static EditText foucsEditText;

    public static EditText getEditText(){
        return foucsEditText;
    }
    public static int getId(){
        return foucsEditText.getId();
    }
    //设置处于焦点的文本框
    public static void setEditText(EditText foucseditText) {
        foucsEditText = foucseditText;
    }
    //焦点文本框光标处删除字符串
    public static void deleteStringatCurser(){
        if(foucsEditText==null)return;
        int index = foucsEditText.getSelectionStart();
        if(index>0) foucsEditText.getEditableText().delete(index-1,index);
    }
    //焦点文本框光标处插入字符串
    public static void insertStringatCurser(String str){
        if(foucsEditText==null)return;
        int index = foucsEditText.getSelectionStart();
        foucsEditText.getEditableText().insert(index,str);
        if(!UnitEvents.mainActivity.edittextflag) parserStr(str);
    }
    //焦点文本框清空
    public static void clear(){
        foucsEditText.setText("");
    }
    public static void clear(String str){
        foucsEditText.setText(str);
    }
    //焦点文本框光标左移
    public static void moveLeft(){
        if(foucsEditText==null)return;
        int index = foucsEditText.getSelectionStart();
        if(index>0)foucsEditText.setSelection(index-1);
    }
    //焦点文本框光标右移
    public static void moveRight(){
        if(foucsEditText==null)return;
        int index = foucsEditText.getSelectionStart();
        if(index<foucsEditText.getText().length())foucsEditText.setSelection(index+1);
    }
}
