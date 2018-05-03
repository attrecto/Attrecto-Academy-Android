package com.attrecto.room.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class BindingAdapters {
    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}
