package com.example.why.application1;

/**
 * Created by why on 18/5/21.
 */

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.example.why.application1.KeyboardView.MyKeyEvent;
import com.example.why.application1.NavigationView.DataIO;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Scriptable;

/**
 *  JS解析封装
 */
public class JsEngine {
    private static Class clazz;

    public JsEngine() {

        this.clazz = JsEngine.class;
        importFunction("sin");
        importFunction("cos");
        importFunction("tan");
        importFunction("abs");
        importFunction("asin");
        importFunction("acos");
        importFunction("atan");
        importFunction("exp");
        importFunction("ln","log",1);
        importFunction("pow",2);
        importFunction("sqrt");
        importFunction("random",0);
        runScript("const PI = java.lang.Math.PI");
        runScript("const EXP = java.lang.Math.E");
    }
    public static void recompile(){
        MyKeyEvent.runStr(DataIO.readCompileFiles());
    }
    void importFunction(String fun){
        importFunction(fun,1);
    }
    void importFunction(String fun,int n){
        String args="";
        if (n==0) ;
        else if(n==1) args = "num";
        else if(n==2) args = "num1,num2";
        else if(n==3) args = "num1,num2,num3";
        else return;
        String string = "        function "+fun+" ("+args+")\n" +
                        "        {\n" +
                        "            return java.lang.Math."+fun+"("+args+");\n" +
                        "        }\n";
        runScript(string);
    }
    void importFunction(String fun,String fun1,int n){
        String args="";
        if (n==0) ;
        else if(n==1) args = "num";
        else if(n==2) args = "num1,num2";
        else if(n==3) args = "num1,num2,num3";
        else return;
        String string = "        function "+fun+" ("+args+")\n" +
                "        {\n" +
                "            return java.lang.Math."+fun1+"("+args+");\n" +
                "        }\n";
        runScript(string);
    }
    /**
     * 执行JS
     *
     * @param js js执行代码 eg: "var v1 = getValue('Ta');setValue(‘key’，v1);"
     */
    static Context ctx;
    static Scriptable scope;

    public static SpannableString runScript(String js) {
        if(ctx==null) {
            ctx = Context.enter();
            ctx.setOptimizationLevel(-1);
        }
        try {
            if(scope==null)scope = ctx.initStandardObjects();
            Object obj = ctx.evaluateString(scope, js, clazz.getSimpleName(), 1, null);
            if(obj!=null) {
                String str = obj.toString();
                if(obj instanceof Double) str = replaceZero(str);
                ans = str;
                SpannableString spannableString = new SpannableString(">> "+str);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#0000f0")), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                return spannableString;
            }
            return new SpannableString("");
        } catch (EvaluatorException e){
            System.out.println("Error");
            SpannableString spannableString = new SpannableString(e.toString());
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#f00000")), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new RelativeSizeSpan(0.7f), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        } catch (Exception e){
            System.out.println("Error");
            SpannableString spannableString = new SpannableString(e.toString());
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#f00000")), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new RelativeSizeSpan(0.7f), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }
    }
    private static String ans;
    public static String getAns(){
        return ans;
    }
    private static String replaceZero(String str){
        if(str.indexOf('E')>0){
            str = str.replaceAll("\\+", "");
            str = str.replaceAll("\\.?0+E0*", "E");
            str = str.replaceAll("E$", "");
        }else if(str.indexOf('e')>0){
            str = str.replaceAll("\\+", "");
            str = str.replaceAll("\\.?0+e0*", "e");
            str = str.replaceAll("e$", "");
        }else if(str.indexOf('.')>0){
            str = str.replaceAll("0+?$", "");
            str = str.replaceAll("[\\.]$", "");
        }
        return str;
    }
}