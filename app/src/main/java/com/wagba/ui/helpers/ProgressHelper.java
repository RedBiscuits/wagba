package com.wagba.ui.helpers;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressHelper {

    public static ProgressDialog progress;
    public static void show(Context context){
        progress = new ProgressDialog(context);

        progress.setMessage("Loading");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    public static void dismiss(){
        if (progress != null)
        {
            progress.dismiss();
        }
    }
}
