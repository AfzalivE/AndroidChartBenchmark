package com.afzaln.androidchartbenchmark.hellocharts;

import com.afzaln.androidchartbenchmark.R;

/**
 * Created by afzal on 2016-09-17.
 */

public class HcRtFifoFragment extends HcFragment {

    private static final int NUM_SETS = 1;

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    @Override
    protected boolean isFifo() {
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.hellocharts_fifo_fragment;
    }
}
