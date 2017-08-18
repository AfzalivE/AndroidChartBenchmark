package com.afzaln.androidchartbenchmark.mpandroidchart;

import com.afzaln.androidchartbenchmark.R;

/**
 * Created by afzal on 2016-09-17.
 */

public class MpRtFragment extends MpFragment {
    private static final int NUM_SETS = 1;

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    @Override
    protected int getLayout() {
        return R.layout.mpandroid_fifo_fragment;
    }
}
