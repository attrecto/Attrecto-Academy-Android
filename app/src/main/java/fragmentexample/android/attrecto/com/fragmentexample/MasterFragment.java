package fragmentexample.android.attrecto.com.fragmentexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Copyright (c) 2018 Apps AS. All rights reserved.
 * Created by gabor.demko on 2018.04.12..
 */

public class MasterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        final MainActivity  mainActivity = (MainActivity) getActivity();
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.openDetail("Button 1 was clicked");
            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.openDetail("Button 2 was clicked");
            }
        });

        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.openDetail("Button 3 was clicked");
            }
        });
    }
}
