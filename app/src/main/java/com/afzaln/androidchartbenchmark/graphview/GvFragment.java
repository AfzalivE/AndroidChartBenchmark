package com.afzaln.androidchartbenchmark.graphview;

import com.afzaln.androidchartbenchmark.R;
import com.afzaln.androidchartbenchmark.ui.BaseChartFragment;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * Created by afzal on 2017-08-18.
 */

public abstract class GvFragment extends BaseChartFragment {
    private static int MAX_SIZE = 120;

    @BindView(R.id.chart)
    GraphView graphView;

    List<LineGraphSeries<DataPoint>> seriesList;
    private int seriesSize;
    private double graphLastXValue;

    @Override
    protected void setupChart() {
        seriesList = new ArrayList<>();

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(MAX_SIZE);

        graphView.getGridLabelRenderer().setLabelVerticalWidth(100);
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
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        series.setColor(color);
        seriesList.add(series);
        graphView.addSeries(series);
    }

    @Override
    protected void updateChart(float[] points) {
        for (int i = 0, iSize = getSetSize(); i < iSize; i++) {
            LineGraphSeries series = seriesList.get(i);
            if (isFifo() && seriesSize > MAX_SIZE) {
                series.appendData(new DataPoint(graphLastXValue, points[i]), true, MAX_SIZE);
            } else {
                // keep all the points in memory and don't scroll to end
                series.appendData(new DataPoint(graphLastXValue, points[i]), false, 999999999);
            }
            graphLastXValue += 1;
            seriesSize++;
            graphLastXValue += 0.25d;
            updateCurrentIndex(seriesSize - 1);
        }
    }

    protected boolean isFifo() {
        return false;
    }
}
