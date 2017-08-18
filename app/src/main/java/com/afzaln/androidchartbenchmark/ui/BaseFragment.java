package com.afzaln.androidchartbenchmark.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by afzal on 2016-10-13.
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout = getLayout();
        View view = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, view);

        if (ViewConfiguration.get(getActivity()).hasPermanentMenuKey()) {
            setHasOptionsMenu(true);
        }
        return view;
    }

    protected abstract int getLayout();
}
