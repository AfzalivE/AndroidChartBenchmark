package com.afzaln.androidchartbenchmark;

/**
 * Created by afzal on 2016-10-13.
 */
public abstract class PointsRunnable implements Runnable {
    private float[] points;

    public void setPoints(float[] points) {
        this.points = points;
    }

    public abstract void run(float[] points);

    public void run() {
        run(points);
    }

}
