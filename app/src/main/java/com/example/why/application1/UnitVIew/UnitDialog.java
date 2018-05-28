package com.example.why.application1.UnitVIew;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.why.application1.NavigationView.Navigation;
import com.example.why.application1.R;

/**
 * Created by why on 18/5/21.
 */

public class UnitDialog {
    //弹出单选框
    public static void SingleSelect() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UnitEvents.mainActivity);
        builder.setSingleChoiceItems(UnitEvents.getUnitsArray(), -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                dialog.dismiss();
                UnitEvents.CompFlag = false;
//                if(pos == 0) {
//                    UnitEvents.UnitFlag = 1;
//                    MultSelect(R.array.MONEY, R.array.MONEY_W);
//                }
//                else
                if(pos == 0) {
                    UnitEvents.UnitFlag = 4;
                    MultSelect(R.array.ARNGLE,R.array.ARNGLE_W);
                }
                else if(pos == 1) {
                    UnitEvents.UnitFlag = 4;
                    MultSelect(R.array.LENGHT,R.array.LENGHT_W);
                }
                else if(pos == 2) {
                    UnitEvents.UnitFlag = 4;
                    MultSelect(R.array.AREA,R.array.AREA_W);
                }
                else if(pos == 3) {
                    UnitEvents.UnitFlag = 4;
                    MultSelect(R.array.VOLONM,R.array.VOLONM_W);
                }
                else if(pos == 4) {
                    UnitEvents.UnitFlag = 4;
                    MultSelect(R.array.WEIGHT,R.array.WEIGHT_W);
                }
                else if(pos == 5) {
                    UnitEvents.UnitFlag = 4;
                    MultSelect(R.array.SPEED,R.array.SPEED_W);
                }
                else if(pos == 6) {
                    UnitEvents.UnitFlag = 3;
                    MultSelect(R.array.TEMPER,R.array.TEMPER_W);
                }
                else if(pos == 7) {
                    UnitEvents.UnitFlag = 4;
                    MultSelect(R.array.ENERGE,R.array.ENERGE_W);
                }
                else if(pos == 8) {
                    UnitEvents.UnitFlag = 4;
                    MultSelect(R.array.POWER,R.array.POWER_W);
                }
                else {
                    UnitEvents.UnitFlag = 5;
                    MultSelect(R.array.COMPUTER, R.array.COMPUTER_W);
                    UnitEvents.CompFlag = true;
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    public static void NavigationWindow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UnitEvents.mainActivity);
        builder.setSingleChoiceItems(UnitEvents.getArray(R.array.Navigation), -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                dialog.dismiss();
                if(pos == 0) {
                    Navigation.openHelpView();
                }else if(pos == 1) {
                    Navigation.openLogView();
                }else if(pos == 2) {
                    Navigation.openForeView();
                }else if(pos == 3){
                    Navigation.openSettingView();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    //弹出多选框
    private static void MultSelect(int ID,int weightID){
        if(ID!= UnitEvents.CurrentUnit){
            UnitEvents.unitadapter.initAdapter();
            UnitEvents.CurrentUnit = ID;
            UnitEvents.currentCore = 0;
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(UnitEvents.mainActivity);
        final String[] items = UnitEvents.getArray(ID);
        final String[] weights = UnitEvents.getArray(weightID);
        builder.setMultiChoiceItems(UnitEvents.getArray(ID), UnitEvents.selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                UnitEvents.selectedItems[i] = b;
                if(b) UnitEvents.unitadapter.addOne(items[i],weights[i]);
                else UnitEvents.unitadapter.deleteOne(items[i]);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
