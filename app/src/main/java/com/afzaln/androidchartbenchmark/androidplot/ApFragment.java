package com.afzaln.androidchartbenchmark.androidplot;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.afzaln.androidchartbenchmark.R;
import com.afzaln.androidchartbenchmark.ui.BaseChartFragment;
import com.androidplot.Plot;
import com.androidplot.util.Redrawer;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.StepMode;
import com.androidplot.xy.XYPlot;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * Created by afzal on 2017-08-18.
 */

public abstract class ApFragment extends BaseChartFragment {
    private static int MAX_SIZE = 120;

    private Redrawer redrawer;
    @BindView(R.id.chart)
    XYPlot lineChartView;
    private ArrayList<SimpleXYSeries> seriesList;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        redrawer = new Redrawer(
                Arrays.asList(new Plot[]{lineChartView}),
                100, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        redrawer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        redrawer.pause();
    }

    @Override
    protected void setupChart() {
        seriesList = new ArrayList<>();
        lineChartView.getGraph().getBackgroundPaint().setColor(Color.WHITE);
        lineChartView.getGraph().getGridBackgroundPaint().setColor(Color.WHITE);
        lineChartView.getGraph().getDomainGridLinePaint().setColor(Color.WHITE);
        lineChartView.getGraph().getRangeGridLinePaint().setColor(Color.WHITE);
        lineChartView.getLegend().setVisible(false);
        lineChartView.setPadding(0, 0, 0, 0);

        lineChartView.setRangeBoundaries(0, 1, BoundaryMode.FIXED);
        lineChartView.setDomainBoundaries(0, 120, BoundaryMode.FIXED);

        lineChartView.setDomainStepMode(StepMode.INCREMENT_BY_VAL);
        lineChartView.setDomainStepValue(120 / 10);
        lineChartView.setLinesPerRangeLabel(3);
    }

    @Override
    protected void setupAxes() {

    }

    @Override
    protected void setupDatasets() {
        for (int i = 0, iSize = getSetSize(); i < iSize; i++) {
            setupDataset(ChartUtils.COLORS[i]);
        }
    }

    @Override
    protected void setupDataset(int color) {
        // create our series from our array of nums:
        SimpleXYSeries series = new SimpleXYSeries("");
        series.useImplicitXVals();
        LineAndPointFormatter formatter = new LineAndPointFormatter(color, null, null, null);
        lineChartView.addSeries(series, formatter);
        seriesList.add(series);
    }

    @Override
    protected void updateChart(float[] points) {
        for (int i = 0, iSize = getSetSize(); i < iSize; i++) {
            SimpleXYSeries series = seriesList.get(i);
            if (isFifo() && series.size() > MAX_SIZE) {
                series.removeFirst(); // removing instead of moving the frame, kinda cheats with memory
            }

            series.addLast(null, points[i]);
        }
    }

    protected boolean isFifo() {
        return false;
    }
}
