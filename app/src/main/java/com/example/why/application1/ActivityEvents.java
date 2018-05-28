package com.example.why.application1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Vibrator;
import android.widget.Toast;

import com.example.why.application1.UnitVIew.UnitEvents;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by why on 18/5/21.
 */

public class ActivityEvents {
    public static void shack(){
        Vibrator vibrator = (Vibrator) UnitEvents.mainActivity.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(33);
    }
    public static void toast(String msg){
        Toast.makeText(UnitEvents.mainActivity,msg,Toast.LENGTH_SHORT).show();
    }
    //放入剪切板
    public static void putIntoCilpboard(String str){
        ClipboardManager clipboardManager = (ClipboardManager) UnitEvents.mainActivity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Label", str);
        clipboardManager.setPrimaryClip(clipData);
    }
}
