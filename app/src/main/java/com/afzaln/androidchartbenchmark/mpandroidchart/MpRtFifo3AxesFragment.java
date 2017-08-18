package com.afzaln.androidchartbenchmark.mpandroidchart;

import com.afzaln.androidchartbenchmark.R;

/**
 * Created by afzal on 2016-09-17.
 */

public class MpRtFifo3AxesFragment extends MpFragment {

    public static final int NUM_SETS = 3;

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    @Override
    protected void updateChart(float[] points) {
        super.updateChart(points);
        lineChartView.setVisibleXRangeMaximum(120);
    }

    @Override
    protected int getLayout() {
        return R.layout.mp_fragment;
    }
}
