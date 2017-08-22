package com.afzaln.androidchartbenchmark;

import android.app.Application;
import android.content.Context;

import com.codemonkeylabs.fpslibrary.FrameDataCallback;
import com.codemonkeylabs.fpslibrary.TinyDancer;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by afzal on 2016-10-13.
 */

public class ChartBenchmarkApp extends Application {

    private List<StatHolder> stats;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
//        TinyDancer.create().show(this);

        //you can add a callback to get frame times and the calculated
        //number of dropped frames within that window

        stats = new ArrayList<>();

        TinyDancer.create()
                .addFrameDataCallback(callback)
                .show(this);
    }

    private FrameDataCallback callback = new FrameDataCallback() {
        @Override
        public void doFrame(long previousFrameNS, long currentFrameNS, int droppedFrames) {
            if (!stats.isEmpty()) {
                StatHolder currentStats = stats.get(stats.size() - 1);
                currentStats.add(currentFrameNS, droppedFrames);
            }
        }
    };

    public static ChartBenchmarkApp get(Context context) {
        return (ChartBenchmarkApp) context.getApplicationContext();
    }

    public void addStatHolder(String name) {
        stats.add(new StatHolder(name));
    }

    public void finishStats() {
        Timber.d("Final stats: " + stats);
    }
}
