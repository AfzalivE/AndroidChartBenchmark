package com.afzaln.androidchartbenchmark;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by afzal on 2016-10-13.
 */

public class ChartBenchmarkApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
//        TinyDancer.create().show(this);

        //you can add a callback to get frame times and the calculated
        //number of dropped frames within that window
//        TinyDancer.create()
//                .addFrameDataCallback(new FrameDataCallback() {
//                    @Override
//                    public void doFrame(long previousFrameNS, long currentFrameNS, int droppedFrames) {
//                        //collect your stats here
//                    }
//                })
//                .show(this);
    }
}
