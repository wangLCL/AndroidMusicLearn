package com.wk.ateutils.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.wk.ateutils.ATE;


public class ATECheckBox extends AppCompatCheckBox {

    public ATECheckBox(Context context) {
        super(context);
        init(context);
    }

    public ATECheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ATECheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        setTag("tint_accent_color"); //指定tag 然后去设置
        ATE.apply(context,this);
    }
}





























