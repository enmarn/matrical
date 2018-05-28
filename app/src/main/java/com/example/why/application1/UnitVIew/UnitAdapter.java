package com.example.why.application1.UnitVIew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.why.application1.MainActivity;
import com.example.why.application1.R;

import java.lang.reflect.Method;

/**
 * Created by why on 18/5/7.
 */

public class UnitAdapter extends BaseAdapter {
    private final int resourceId;
    private MainActivity mainActivity;
    private Context context ;
    public static int cnt=0;
    public UnitAdapter(Context context, int resource) {
        this.context = context;
        resourceId = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UnitFru fruit = (UnitFru) getItem(position); // 获取当前项的String实例
        View view = LayoutInflater.from(context).inflate(resourceId, null);//实例化一个对象
        Button but = view.findViewById(R.id.unitTextView);
        EditText edit = view.findViewById(R.id.unitEditView);
        System.out.println(fruit.toString());
        System.out.println(fruit.getUnitname());
        but.setText(fruit.unitname);
        fruit.setPoint(edit);
        edit.setOnFocusChangeListener(UnitEvents.listen1);
        if(UnitEvents.CompFlag)
            UnitFru.setDoubleEditText(fruit,Math.pow(2, UnitEvents.currentCore-fruit.getWeight())* UnitEvents.currentCom);
        else
            UnitFru.setDoubleEditText(fruit, UnitEvents.currentCore/fruit.getWeight());
        Class<EditText> cls = EditText.class;
        Method method;
        try {
            method = cls.getMethod("setShowSoftInputOnFocus",boolean.class);
            method.setAccessible(true);
            method.invoke(edit, false);
        }catch (Exception e) {
            // TODO: handle exception
        }
        return view;
    }
    boolean haveCnt = true;
    @Override
    public int getCount() {
        if(cnt!=0) return cnt;
        UnitEvents.unitlist.clear();
        UnitEvents.unitlist.add(new UnitFru("SELECT","0"));
        haveCnt = true;
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return UnitEvents.unitlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addOne(String string,String weight){
        cnt ++;
        if(haveCnt){
            UnitFru.remove(UnitEvents.unitlist,"SELECT");
            System.out.println(string);
            haveCnt = false;
        }
        UnitEvents.unitlist.add(new UnitFru(string,weight));
        notifyDataSetChanged();
    }
    public void deleteOne(String string){
        cnt --;
        System.out.println(string);
        UnitFru.remove(UnitEvents.unitlist,string);;
        notifyDataSetChanged();
    }
    public void initAdapter(){
        boolean[] a = UnitEvents.selectedItems;
        cnt = 0;
        for(int i=0;i<29;i++) a[i]=false;
        UnitEvents.unitlist.clear();
    }

}

