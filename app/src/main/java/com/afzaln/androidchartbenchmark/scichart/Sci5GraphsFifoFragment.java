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
 * Created by afzal on 2016-09-17.
 */

public class Sci5GraphsFifoFragment extends BaseChartFragment {
    private static final int NUM_SETS = 1;
    protected SciChartBuilder sciChartBuilder;

    @BindView(R.id.chart1)
    SciChartSurface surface1;
    @BindView(R.id.chart2)
    SciChartSurface surface2;
    @BindView(R.id.chart3)
    SciChartSurface surface3;
    @BindView(R.id.chart4)
    SciChartSurface surface4;
    @BindView(R.id.chart5)
    SciChartSurface surface5;

    private SciChartSurface[] surfaceArr;
    private IRenderableSeries line;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        SciChartBuilder.init(getActivity());
        sciChartBuilder = SciChartBuilder.instance();
        surfaceArr = new SciChartSurface[]{surface1, surface2, surface3, surface4, surface5};
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setupChart() {
    }

    @Override
    protected void setupAxes() {
        for (int i = 0, iSize = surfaceArr.length; i < iSize; i++) {
            SciChartSurface surface = surfaceArr[i];
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
    }

    @Override
    protected void setupDatasets() {
        for (int i = 0, iSize = surfaceArr.length; i < iSize; i++) {
            final SciChartSurface surface = surfaceArr[i];
            UpdateSuspender.using(surface, new Runnable() {
                @Override
                public void run() {
                    for (int i = 0, iSize = getSetSize(); i < iSize; i++) {
                        setupDataset(ChartUtils.COLORS[i]);
                        surface.getRenderableSeries().add(line);
                    }
                }
            });
        }
    }

    @Override
    protected void setupDataset(int strokeColor) {
        IXyDataSeries<Integer, Float> dataSeries = sciChartBuilder.newXyDataSeries(Integer.class, Float.class).withFifoCapacity(200).build();
        line = sciChartBuilder.newLineSeries()
                .withStrokeStyle(strokeColor)
                .withDataSeries(dataSeries)
                .build();
    }

    private PointsRunnable appendDataRunnable = new PointsRunnable() {
        @Override
        public void run(float[] points) {
            for (int i = 0, iSize = getSetSize(); i < iSize; i++) {
                for (int j = 0, jSize = surfaceArr.length; j < jSize; i++) {
                    final SciChartSurface surface = surfaceArr[j];
                    final IXyDataSeries<Integer, Float> mainSeries = (IXyDataSeries<Integer, Float>) surface.getRenderableSeries().get(i).getDataSeries();
                    int xValue = mainSeries.getCount() > 0 ? mainSeries.getXValues().get(mainSeries.getCount() - 1) : 0;
                    xValue++;
                    mainSeries.append(xValue, points[i]);
                }
            }
        }
    };

    @Override
    protected void updateChart(float[] points) {
        appendDataRunnable.setPoints(points);
        for (int i = 0, iSize = surfaceArr.length; i < iSize; i++) {
            final SciChartSurface surface = surfaceArr[i];
            UpdateSuspender.using(surface, appendDataRunnable);
        }
    }

    @Override
    protected int getSetSize() {
        return NUM_SETS;
    }

    @Override
    protected int getLayout() {
        return R.layout.sci5_fragment;
    }
}
