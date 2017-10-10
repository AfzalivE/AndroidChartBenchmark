package com.afzaln.androidchartbenchmark.scichart;

import android.os.Bundle;
import android.view.View;

import com.afzaln.androidchartbenchmark.PointsRunnable;
import com.afzaln.androidchartbenchmark.R;
import com.afzaln.androidchartbenchmark.ui.BaseChartFragment;
import com.scichart.charting.model.dataSeries.IXyDataSeries;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.visuals.axes.AutoRange;
import com.scichart.charting.visuals.axes.AxisAlignment;
import com.scichart.charting.visuals.axes.IAxis;
import com.scichart.charting.visuals.axes.NumericAxis;
import com.scichart.charting.visuals.renderableSeries.IRenderableSeries;
import com.scichart.core.framework.UpdateSuspender;
import com.scichart.extensions.builders.SciChartBuilder;

import butterknife.BindView;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * Created by afzal on 2017-08-17.
 */

public abstract class ScFragment extends BaseChartFragment {

    protected SciChartBuilder sciChartBuilder;

    @BindView(R.id.chart)
    SciChartSurface surface;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        SciChartBuilder.init(getActivity());
        sciChartBuilder = SciChartBuilder.instance();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setupChart() {
    }

    @Override
    protected void setupAxes() {
        IAxis xAxis = new NumericAxis(getActivity());
        xAxis.setAxisAlignment(AxisAlignment.Bottom);
        xAxis.setAutoRange(AutoRange.Always);
        xAxis.setAxisTitle("X Axis");
        surface.getXAxes().add(xAxis);

        IAxis yAxis = new NumericAxis(getActivity());
        yAxis.setAxisAlignment(AxisAlignment.Right);
        yAxis.setAutoRange(AutoRange.Always);
        yAxis.setAxisTitle("Y Axis");
        surface.getYAxes().add(yAxis);
    }

    @Override
    protected void setupDatasets() {
        UpdateSuspender.using(surface, new Runnable() {
            @Override
            public void run() {
                for (int i = 0, iSize = getSetSize(); i < iSize; i++) {
                    setupDataset(ChartUtils.COLORS[i]);
                }
            }
        });
    }

    @Override
    protected void setupDataset(int strokeColor) {
        IXyDataSeries<Integer, Float> dataSeries = sciChartBuilder.newXyDataSeries(Integer.class, Float.class).withFifoCapacity(200).build();
        final IRenderableSeries line = sciChartBuilder.newLineSeries()
                .withStrokeStyle(strokeColor)
                .withDataSeries(dataSeries)
                .build();
        surface.getRenderableSeries().add(line);
    }

    private PointsRunnable appendDataRunnable = new PointsRunnable() {
        @Override
        public void run(float[] points) {
            for (int i = 0, iSize = getSetSize(); i < iSize; i++) {
                final IXyDataSeries<Integer, Float> mainSeries = (IXyDataSeries<Integer, Float>) surface.getRenderableSeries().get(i).getDataSeries();
                int xValue = mainSeries.getCount() > 0 ? mainSeries.getXValues().get(mainSeries.getCount() - 1) : 0;
                xValue++;
                mainSeries.append(xValue, points[i]);
                updateCurrentIndex(xValue);
            }
        }
    };

    @Override
    protected void updateChart(float[] points) {
        appendDataRunnable.setPoints(points);
        UpdateSuspender.using(surface, appendDataRunnable);
    }
}
