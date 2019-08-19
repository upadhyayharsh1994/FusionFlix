package com.example.harsh.fusionflix.Model;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.Window;

import com.example.harsh.fusionflix.R;

public class Constants {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String URL_POPULAR = "movie/popular";
    public static final String URL_TOP = "movie/top_rated";
    public static final String API_KEY = "3bd9383f572f6fca69d727681d03966a";
    public static final String LANGUAGE = "en-US";
    public static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w500";


    public  static Dialog get_dialog(Context context, int layoutid)
    {
        Dialog dialog = new  Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layoutid);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.transparent)));
        return dialog;
    }
}

