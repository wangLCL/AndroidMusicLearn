package com.wk.dialogutils.internal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import com.wk.dialogutils.GravityEnum;
import com.wk.dialogutils.R;

public class MDButton extends AppCompatTextView {

    private boolean stacked = false;
    private GravityEnum stackedGravity;

    private int stackedEndPadding;
    private Drawable stackedBackground;
    private Drawable defaultBackground;

    public MDButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public MDButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        stackedEndPadding = context.getResources()
                .getDimensionPixelSize(R.dimen.md_dialog_frame_margin);
        stackedGravity = GravityEnum.END;
    }

    /**
     * Set if the button should be displayed in stacked mode.
     * This should only be called from MDRootLayout's onMeasure, and we must be measured
     * after calling this.
     */
    /* package */ void setStacked(boolean stacked, boolean force) {
        if (this.stacked != stacked || force) {

            setGravity(stacked ? (Gravity.CENTER_VERTICAL | stackedGravity.getGravityInt()) : Gravity.CENTER);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                //noinspection ResourceType
                setTextAlignment(stacked ? stackedGravity.getTextAlignment() : TEXT_ALIGNMENT_CENTER);
            }

            setBackground(stacked ? stackedBackground : defaultBackground);
            if (stacked) {
                setPadding(stackedEndPadding, getPaddingTop(), stackedEndPadding, getPaddingBottom());
            } /* Else the padding was properly reset by the drawable */

            this.stacked = stacked;
        }
    }

    public void setStackedGravity(GravityEnum gravity) {
        stackedGravity = gravity;
    }

    public void setStackedSelector(Drawable d) {
        stackedBackground = d;
        if (stacked)
            setStacked(true, true);
    }

    public void setDefaultSelector(Drawable d) {
        defaultBackground = d;
        if (!stacked)
            setStacked(false, true);
    }

    public void setAllCapsCompat(boolean allCaps) {
        setAllCaps(allCaps);
    }
}
