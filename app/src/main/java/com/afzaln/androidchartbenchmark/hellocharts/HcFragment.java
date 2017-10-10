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
 * Created by afzal on 2017-08-17.
 */

public abstract class HcFragment extends BaseChartFragment {
    private static int MAX_SIZE = 120;

    @BindView(R.id.chart)
    LineChartView lineChartView;

    private LineChartData lineChartData;
    private List<Line> lines;
    private List<PointValue>[] values;

    @Override
    protected void setupChart() {
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

        lineChartView.setZoomEnabled(false);
        lineChartView.setLineChartData(lineChartData);
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
            lineChartView.setLineChartData(lineChartData);
        }
    }

    protected boolean isFifo() {
        return false;
    }
}
