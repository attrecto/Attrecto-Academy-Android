package fragmentexample.android.attrecto.com.fragmentexample;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String param = getActivity().getIntent().getStringExtra("parameter");
        if(param == null){
            param = "No button pressed, yet";
        }
        ((TextView)getView().findViewById(R.id.textView)).setText(param);
    }
}
