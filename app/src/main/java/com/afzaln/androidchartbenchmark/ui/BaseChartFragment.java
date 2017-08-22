package com.afzaln.androidchartbenchmark.ui;

import android.os.Bundle;
import android.view.View;

import com.afzaln.androidchartbenchmark.DataSimulator;
import com.afzaln.androidchartbenchmark.PointsRunnable;

/**
 * Created by afzal on 2016-10-13.
 */

public abstract class BaseChartFragment extends BaseFragment {
    protected PointsRunnable updateChartRunnable = new PointsRunnable() {
        @Override
        public void run(float[] points) {
//            Timber.d(String.format("Points: %s", Arrays.toString(points)));
            updateChart(points);

        }
    };

    protected DataSimulator.DataListener dataListener = new DataSimulator.DataListener() {
        @Override
        public void onDataAvailable(float[] points) {
            updateChartRunnable.setPoints(points);
            getActivity().runOnUiThread(updateChartRunnable);
        }
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataSimulator dataSimulator = new DataSimulator(dataListener);
        setupChart();
        setupAxes();
        setupDatasets();

        dataSimulator.start();
    }

    protected abstract int getSetSize();

    protected abstract void setupChart();

    protected abstract void setupAxes();

    protected abstract void setupDatasets();

    protected abstract void setupDataset(int color);

    protected abstract void updateChart(float[] points);
}
