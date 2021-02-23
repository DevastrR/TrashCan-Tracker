package com.example.tracker.post_main;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.tracker.R;

public class LoadingDialog {
    Activity activity ;
    AlertDialog dialog ;
    public LoadingDialog(Activity myActivity) {
        activity = myActivity ;
    }
    void  starLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity) ;
        LayoutInflater inflater = activity.getLayoutInflater() ;
        builder.setView(inflater.inflate(R.layout.custom_dialog , null)) ;
        builder.setCancelable(false) ;

        dialog = builder.create() ;
        dialog.show();
    }
    void dismissDialog(){
        dialog.dismiss();
    }
}
