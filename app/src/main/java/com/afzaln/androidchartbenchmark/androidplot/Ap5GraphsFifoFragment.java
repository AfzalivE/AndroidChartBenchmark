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

public class Ap5GraphsFifoFragment extends BaseChartFragment {
    private static int MAX_SIZE = 120;
    private static final int NUM_SETS = 1;

    private Redrawer redrawer;
    @BindView(R.id.chart1)
    XYPlot lineChartView1;
    @BindView(R.id.chart2)
    XYPlot lineChartView2;
    @BindView(R.id.chart3)
    XYPlot lineChartView3;
    @BindView(R.id.chart4)
    XYPlot lineChartView4;
    @BindView(R.id.chart5)
    XYPlot lineChartView5;
    private Plot[] chartViews;

    private ArrayList<SimpleXYSeries> seriesList;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        chartViews = new Plot[]{lineChartView1, lineChartView2, lineChartView3, lineChartView4, lineChartView5};
        super.onViewCreated(view, savedInstanceState);
        redrawer = new Redrawer(
                Arrays.asList(chartViews),
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
        for (int i = 0; i < chartViews.length; i++) {
            XYPlot lineChartView = (XYPlot) chartViews[i];
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
        for (int i = 0; i < chartViews.length; i++) {
            XYPlot lineChartView = (XYPlot) chartViews[i];
            lineChartView.addSeries(series, formatter);
        }
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
            updateCurrentIndex(series.size() - 1);
        }
    }

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    protected boolean isFifo() {
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.ap5_fragment;
    }
}
