package com.afzaln.androidchartbenchmark.scichart;

import com.afzaln.androidchartbenchmark.R;

/**
 * Created by afzal on 2016-09-17.
 */

public class SciRtFifoFragment extends SciFragment {

    private static final int NUM_SETS = 1;

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    @Override
    protected int getLayout() {
        return R.layout.scichart_fifo_fragment;
    }
}
