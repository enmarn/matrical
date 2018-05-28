package com.example.why.application1.UnitVIew;

import android.widget.EditText;

import java.util.List;

/**
 * Created by why on 18/5/13.
 */

public class UnitFru {
    public UnitFru(String unitname,String weight) {
        this.unitname = unitname;
        setWeight(weight);
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        double a = 1;
        try {
            a = Double.parseDouble(weight);
        } catch(NumberFormatException e){}
        this.weight = a;
    }

    public EditText getPoint() {
        return point;
    }

    public void setPoint(EditText point) {
        this.point = point;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    double weight;
    EditText point;
    String unitname;
    public static void remove(List<UnitFru> list, String target){
        for(int i = list.size() - 1; i >= 0; i--){
            UnitFru item = list.get(i);
            if(target.equals(item.getUnitname())){
                list.remove(item);
                return;
            }
        }
    }
    public static void parse(List<UnitFru> list,EditText current,double parsecurrent){
        for(int i = list.size() - 1; i >= 0; i--){
            UnitFru item = list.get(i);
            EditText ed = item.getPoint();
            if(ed==current){
                UnitEvents.currentCore=parsecurrent*item.getWeight();
                break;
            }
        }
        for(int i = list.size() - 1; i >= 0; i--){
            UnitFru item = list.get(i);
            EditText ed = item.getPoint();
            if(ed!=current) {
                double a = UnitEvents.currentCore/item.getWeight();
                setDoubleEditText(item,a);
            }
        }
    }
    static public void setDoubleEditText(UnitFru uf,double a){
        if(uf.getPoint()==null)return;
        String str = String.format("%.6g", a);
        if(str.indexOf('e')>0){
            str = str.replaceAll("\\+", "");
            str = str.replaceAll("\\.?0+e0*", "e");
            str = str.replaceAll("e$", "");
        }else if(str.indexOf('.')>0){
            str = str.replaceAll("0+?$", "");
            str = str.replaceAll("[\\.]$", "");
        }
        uf.getPoint().setText(str);
    }
    public static void parseTmp(List<UnitFru> list,EditText current,double currentCore){
        double[] ans=new double[3];
        int q=0;
        for(int i = list.size() - 1; i >= 0; i--){
            UnitFru item = list.get(i);
            EditText ed = item.getPoint();
            if(ed==current){
                q = (int)item.getWeight();
                if(q==0){
                    ans[0] = currentCore;
                    ans[1] = currentCore*1.8+32;
                    ans[2] = currentCore+273.15;
                }else if(q==1){
                    ans[0] = (currentCore-32)/1.8;
                    ans[1] = currentCore;
                    ans[2] = ans[0]+273.15;
                }else{
                    ans[0] = currentCore-273.15;
                    ans[1] = ans[0]*1.8+32;
                    ans[2] = currentCore;
                }
                break;
            }
        }
        for(int i = list.size() - 1; i >= 0; i--){
            UnitFru item = list.get(i);
            EditText ed = item.getPoint();
            q = (int)item.getWeight();
            if(ed!=current) setDoubleEditText(item,ans[q]);
        }
    }
    public static void parseCom(List<UnitFru> list,EditText current,double currentCore){
        int q=0,p=0;
        for(int i = list.size() - 1; i >= 0; i--){
            UnitFru item = list.get(i);
            EditText ed = item.getPoint();
            if(ed==current){
                q = (int)item.getWeight();
                UnitEvents.currentCore = q;
                UnitEvents.currentCom = currentCore;
                break;
            }
        }
        for(int i = list.size() - 1; i >= 0; i--){
            UnitFru item = list.get(i);
            EditText ed = item.getPoint();
            p = (int)item.getWeight();
            if(ed!=current) setDoubleEditText(item,Math.pow(2,q-p)*currentCore);
        }
    }
}
