package com.afzaln.androidchartbenchmark.androidplot;

import com.afzaln.androidchartbenchmark.R;

/**
 * Created by afzal on 2017-08-18.
 */

public class ApRtFifoFragment extends ApFragment {
    private static final int NUM_SETS = 1;

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    protected boolean isFifo() {
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.ap_fragment;
    }
}
