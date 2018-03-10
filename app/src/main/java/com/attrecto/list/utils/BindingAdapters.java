package com.attrecto.list.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by balazsnemeth on 2018. 03. 10..
 */

public class BindingAdapters {
    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}
