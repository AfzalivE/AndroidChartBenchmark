package com.afzaln.androidchartbenchmark.scichart;

import com.afzaln.androidchartbenchmark.R;

/**
 * Created by afzal on 2016-09-17.
 */

public class ScRt3AxesFragment extends ScFragment {
    private static final int NUM_SETS = 3;

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    @Override
    protected int getLayout() {
        return R.layout.sci_fragment;
    }
}
