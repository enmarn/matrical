package com.example.why.application1.NavigationView;

import android.content.Context;

import com.example.why.application1.R;
import com.example.why.application1.UnitVIew.UnitEvents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by why on 18/5/23.
 */

public class DataIO {
    public static void writeFiles(String FILENAME, String content) {
        try {
            // 打开文件获取输出流，文件不存在则自动创建
            createIfNotExist(FILENAME);
            FileOutputStream fos = UnitEvents.mainActivity.openFileOutput(FILENAME,
                    Context.MODE_WORLD_WRITEABLE);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void appendFiles(String FILENAME, String content) {
        try {
            // 打开文件获取输出流，文件不存在则自动创建
            createIfNotExist(FILENAME);
            FileOutputStream fos = UnitEvents.mainActivity.openFileOutput(FILENAME,
                    Context.MODE_APPEND);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createIfNotExist(String filename){
        File file=new File("data/data/com.example.why.application1/"+filename);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void deleteIfExist(String filename){
        File file=new File("data/data/com.example.why.application1/"+filename);
        if(file.exists()){
            file.delete();
        }
    }
    // 读取文件内容
    public static String readFiles(String FILENAME) {
        try {
            FileInputStream inStream = UnitEvents.mainActivity.openFileInput(FILENAME);
            byte[] buffer = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((hasRead = inStream.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, hasRead));
            }

            inStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String readCompileFiles(){
        File file=new File("data/data/com.example.why.application1/recompile.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
                String str = UnitEvents.mainActivity.getResources().getString(R.string.compile);
                writeFiles("recompile.txt",str);
                return str;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return readFiles("recompile.txt");
    }
}
