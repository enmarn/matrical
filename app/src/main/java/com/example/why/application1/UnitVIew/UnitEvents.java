package com.example.why.application1.UnitVIew;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;

import com.example.why.application1.FocusEditText;
import com.example.why.application1.MainActivity;
import com.example.why.application1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by why on 18/5/4.
 */

public class UnitEvents {
    static public MainActivity mainActivity;
    static public View.OnFocusChangeListener listen1;
    public UnitEvents(MainActivity m){
        mainActivity = m;
        listen1 = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    if(!UnitEvents.mainActivity.isKeyBoardShow()) UnitEvents.mainActivity.showKeyBoard();
                    FocusEditText.setEditText((EditText) view);
                }
            }

        };
        unitArray = getArray(R.array.UNITS_NAME);
    }
    public static String[] getArray(int ID){
        Resources res = mainActivity.getResources();
        return res.getStringArray(ID);
    }
    static String[] unitArray;
    static String[] weightArray;
    public static String[] getUnitsArray(){
        return unitArray;
    }
    public static String[] getWeighrArray(){
        return weightArray;
    }
    static public boolean[] selectedItems = new boolean[30];
    public static UnitAdapter unitadapter;
    public static List unitlist = new ArrayList<UnitFru>();
    public static void setUnitAdapter(Context context){
        unitadapter = new UnitAdapter(context, R.layout.unit_items);
    }
    public static int CurrentUnit;
    public static int UnitFlag=-1;
    public static boolean CompFlag = false;
    static public double currentCore = 0;
    static public double currentCom = 0;

}
