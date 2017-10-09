package com.afzaln.androidchartbenchmark.graphview;

import com.afzaln.androidchartbenchmark.R;

/**
 * Created by afzal on 2017-08-18.
 */

public class GvRtFifo3AxesFragment  extends GvFragment {
    private static final int NUM_SETS = 3;

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    protected boolean isFifo() {
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.gv_fragment;
    }
}
