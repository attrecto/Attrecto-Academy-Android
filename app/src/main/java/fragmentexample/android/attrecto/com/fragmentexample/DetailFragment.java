package fragmentexample.android.attrecto.com.fragmentexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Copyright (c) 2018 Apps AS. All rights reserved.
 * Created by gabor.demko on 2018.04.12..
 */

public class DetailFragment extends Fragment {
    public void setParam(String param){
        Bundle args = new Bundle();
        args.putString("parameter", param);
        setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();

        String param = null;
        if(args != null) {
            param = getArguments().getString("parameter");
        }

        if(param == null){
            param = "No button pressed, yet";
        }
        ((TextView)getView().findViewById(R.id.textView)).setText(param);
    }
}
