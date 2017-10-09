package com.afzaln.androidchartbenchmark.teechart;

import com.afzaln.androidchartbenchmark.R;
import com.afzaln.androidchartbenchmark.ui.BaseChartFragment;
import com.steema.teechart.TChart;

import butterknife.BindView;

/**
 * Created by afzal on 2017-08-18.
 */

public abstract class TcFragment extends BaseChartFragment {

    @BindView(R.id.chart)
    TChart chart;

    @Override
    protected void setupChart() {

    }

    @Override
    protected void setupAxes() {

    }

    @Override
    protected void setupDatasets() {

    }

    @Override
    protected void setupDataset(int color) {

    }

    @Override
    protected void updateChart(float[] points) {

    }
}
