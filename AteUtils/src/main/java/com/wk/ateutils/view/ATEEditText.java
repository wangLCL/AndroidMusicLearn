package com.wk.ateutils.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.wk.ateutils.ATE;

@SuppressLint("AppCompatCustomView")
public class ATEEditText extends EditText {
    public ATEEditText(Context context) {
        this(context,null);
    }

    public ATEEditText(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ATEEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ATE.apply(context,this);
    }
}
