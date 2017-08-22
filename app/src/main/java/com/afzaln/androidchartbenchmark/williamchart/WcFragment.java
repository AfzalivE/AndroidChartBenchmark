package com.afzaln.androidchartbenchmark.williamchart;

import com.afzaln.androidchartbenchmark.ui.BaseChartFragment;
import com.db.chart.model.LineSet;

/**
 * Created by afzal on 2017-08-18.
 */

public abstract class WcFragment extends BaseChartFragment {
    @Override
    protected void setupChart() {

    }

    @Override
    protected void setupAxes() {

    }

    @Override
    protected void setupDatasets() {
        LineSet dataset = new LineSet();
    }

    @Override
    protected void setupDataset(int color) {

    }

    @Override
    protected void updateChart(float[] points) {

    }
}
