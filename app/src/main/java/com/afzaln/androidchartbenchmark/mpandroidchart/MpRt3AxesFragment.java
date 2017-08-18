package com.afzaln.androidchartbenchmark.mpandroidchart;

import com.afzaln.androidchartbenchmark.R;

/**
 * Created by afzal on 2016-09-17.
 */

public class MpRt3AxesFragment extends MpFragment {
    private static final int NUM_SETS = 3;

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    @Override
    protected int getLayout() {
        return R.layout.mpandroid_fifo_fragment;
    }
}
