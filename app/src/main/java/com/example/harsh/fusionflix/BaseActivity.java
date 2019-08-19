package com.example.harsh.fusionflix;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.harsh.fusionflix.Model.Constants;

public  class BaseActivity extends AppCompatActivity {
    Dialog pb_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void ShowDialog() {
        if(pb_dialog==null) {

            pb_dialog = Constants.get_dialog(BaseActivity.this, R.layout.progress_dialog);
            pb_dialog.setCancelable(false);
            pb_dialog.show();
        }else
        {
            pb_dialog.dismiss();


        }
    }

    public void DismissDialog() {


        pb_dialog.dismiss();

    }


}
