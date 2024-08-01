package com.wk.dialogutils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;

public class DialogBase extends Dialog implements DialogInterface.OnShowListener {
    protected MDRoo
    public DialogBase(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onShow(DialogInterface dialog) {

    }
}
