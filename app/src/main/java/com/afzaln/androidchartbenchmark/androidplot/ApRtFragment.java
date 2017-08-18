package com.afzaln.androidchartbenchmark.androidplot;

import com.afzaln.androidchartbenchmark.R;

/**
 * Created by afzal on 2017-08-18.
 */

public class ApRtFragment extends ApFragment {
    private static final int NUM_SETS = 1;

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    @Override
    protected int getLayout() {
        return R.layout.ap_fragment;
    }
}
