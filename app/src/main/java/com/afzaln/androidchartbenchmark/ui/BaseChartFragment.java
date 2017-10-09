package com.afzaln.androidchartbenchmark.ui;

import android.os.Bundle;
import android.view.View;

import com.afzaln.androidchartbenchmark.DataSimulator;
import com.afzaln.androidchartbenchmark.PointsRunnable;

import timber.log.Timber;

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

    private int rate;
    private DataSimulator dataSimulator;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataSimulator = new DataSimulator(dataListener, rate);
        Timber.d("Running at %d Hz", rate);
        setupChart();
        setupAxes();
        setupDatasets();

        dataSimulator.start();

    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    protected abstract int getSetSize();

    protected abstract void setupChart();

    protected abstract void setupAxes();

    protected abstract void setupDatasets();

    protected abstract void setupDataset(int color);

    protected abstract void updateChart(float[] points);
}
