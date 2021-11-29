package com.fitranda.qurbanku.Helper;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingDialog extends ProgressDialog {
    public LoadingDialog(Context context) {
        super(context);
        setCancelable(false);
        setMessage("Loading...");
    }
}
