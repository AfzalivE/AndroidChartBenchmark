package com.afzaln.androidchartbenchmark.hellocharts;

import com.afzaln.androidchartbenchmark.R;
import com.afzaln.androidchartbenchmark.ui.BaseChartFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by afzal on 2016-09-17.
 */

public class Hc5GraphsFifoFragment extends BaseChartFragment {
    private static final int NUM_SETS = 1;

    private static int MAX_SIZE = 120;

    @BindView(R.id.chart1)
    LineChartView lineChartView1;
    @BindView(R.id.chart2)
    LineChartView lineChartView2;
    @BindView(R.id.chart3)
    LineChartView lineChartView3;
    @BindView(R.id.chart4)
    LineChartView lineChartView4;
    @BindView(R.id.chart5)
    LineChartView lineChartView5;

    private LineChartView[] chartViews;
    private LineChartData lineChartData;
    private List<Line> lines;
    private List<PointValue>[] values;

    @Override
    protected void setupChart() {
        chartViews = new LineChartView[]{lineChartView1, lineChartView2, lineChartView3, lineChartView4, lineChartView5};
        lines = new ArrayList<>();
        values = new List[getSetSize()];
    }

    @Override
    protected void setupAxes() {
        // doesn't need this
    }

    @Override
    protected void setupDatasets() {
        for (int i = 0, iSize = getSetSize(); i < iSize; i++) {
            setupDataset(i);
        }

        lineChartData = new LineChartData(lines);

        for (int i = 0, iSize = chartViews.length; i < iSize; i++) {
            LineChartView lineChartView = chartViews[i];
            lineChartView.setZoomEnabled(false);
            lineChartView.setLineChartData(lineChartData);
        }
    }

    protected void setupDataset(int i) {
        values[i] = new ArrayList<>();
        Line line = new Line();
        line.setValues(values[i]);
        line.setColor(ChartUtils.COLORS[i])
                .setCubic(true)
                .setHasPoints(false)
                .setAreaTransparency(50)
                .setFilled(true);

        lines.add(line);
    }

    @Override
    protected void updateChart(float[] points) {
        for (int i = 0; i < values.length; i++) {
            List<PointValue> pointValues = values[i];
            if (isFifo()) {
                if (values[i].size() > MAX_SIZE) {
                    ArrayList<Integer> toRemove = new ArrayList<>();
                    for (int j = 0; j < pointValues.size(); j++) {
                        PointValue value = pointValues.get(j);
                        float newX = value.getX() - 1;
                        if (newX < 0) {
                            toRemove.add(j);
                        } else {
                            value.set(newX, value.getY());
                            pointValues.set(j, value);
                        }
                    }

                    // remove points that have negative X
                    for (int j = toRemove.size() - 1; j >= 0; j--) {
                        int index = toRemove.get(j);
                        pointValues.remove(index);
                    }
                }
            }
            // add the new value in
            pointValues.add(new PointValue(pointValues.size() - 1, points[i]));
            updateCurrentIndex(pointValues.size() - 1);

            for (int j = 0, jSize = chartViews.length; j < jSize; j++) {
                LineChartView lineChartView = chartViews[j];
                lineChartView.setLineChartData(lineChartData);
            }
        }
    }

    protected boolean isFifo() {
        return true;
    }

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    @Override
    protected int getLayout() {
        return R.layout.hc5_fragment;
    }
}
