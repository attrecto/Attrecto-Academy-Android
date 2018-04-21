package example.android.attrecto.com.asynchnetworkcomplex.openweathermap;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by user on 2018. 04. 21..
 */

public class DataBindingAdapters {
    @BindingAdapter({"android:src"})
    public static void setImageViewUrl(ImageView imageView, String url) {
        if (url != null) {
            Picasso.get().load(url).into(imageView);
        }
    }
}
