package com.afzaln.androidchartbenchmark.mpandroidchart;

import android.graphics.Color;

import com.afzaln.androidchartbenchmark.R;
import com.afzaln.androidchartbenchmark.ui.BaseChartFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import butterknife.BindView;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * Created by afzal on 2017-08-17.
 */

public abstract class MpFragment extends BaseChartFragment {

    @BindView(R.id.chart)
    LineChart lineChartView;

    @Override
    protected void setupChart() {
        LineData data = new LineData();
        lineChartView.setData(data);
    }

    @Override
    protected void setupAxes() {
        XAxis xl = lineChartView.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(false);

        YAxis leftAxis = lineChartView.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMaximum(1f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = lineChartView.getAxisRight();
        rightAxis.setEnabled(false);
    }

    @Override
    protected void setupDatasets() {
        for (int i = 0, iSize = getSetSize(); i < iSize; i++) {
            setupDataset(ChartUtils.COLORS[i]);
        }
    }

    @Override
    protected void setupDataset(int color) {
        LineDataSet lineDataSet = new LineDataSet(null, "Dynamic Data");
        lineDataSet.addEntry(new Entry(0, 0));
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setColor(color);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setFillAlpha(65);
        lineDataSet.setFillColor(color);
        lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(9f);
        lineDataSet.setDrawValues(false);

        LineData lineData = lineChartView.getLineData();
        lineData.addDataSet(lineDataSet);
    }

    @Override
    protected void updateChart(float[] points) {
        LineData data = lineChartView.getData();
        if (data != null) {
            for (int i = 0, iSize = getSetSize(); i < iSize; i++) {
                ILineDataSet set = data.getDataSetByIndex(i);
                data.addEntry(new Entry(set.getEntryCount(), points[i]), i);
            }
            lineChartView.notifyDataSetChanged();
            lineChartView.moveViewToX(data.getEntryCount() - 121);
            updateCurrentIndex(data.getEntryCount() - 1);
        }
    }
}
