package com.wk.learn.preference;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;

import com.wk.ateutils.ATE;
import com.wk.learn.R;

public class KwColorPreference extends Preference {
    private View mView;
    private int color;
    private int border;

    public KwColorPreference(Context context){
        this(context,null,0);
    }

    public KwColorPreference(Context context,AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public KwColorPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutResource(R.layout.preference_custom);
    }


    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        mView = view;
        ATE.apply(view.getContext(), view);
        invalidateColor();
    }

    public void setColor(int color, int border) {
        this.color = color;
        this.border = border;
        invalidateColor();
    }

    private void invalidateColor() {
        if (mView != null) {
            BorderCircleView circle = (BorderCircleView) mView.findViewById(R.id.circle);
            if (this.color != 0) {
                circle.setVisibility(View.VISIBLE);
                circle.setBackgroundColor(color);
                circle.setBorderColor(border);
            } else {
                circle.setVisibility(View.GONE);
            }
        }
    }
}
