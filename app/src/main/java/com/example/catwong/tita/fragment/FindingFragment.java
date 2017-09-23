package com.example.catwong.tita.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.catwong.tita.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindingFragment extends Fragment {


    public FindingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_finding, container, false);
    }

}
