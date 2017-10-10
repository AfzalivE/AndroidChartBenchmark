package com.afzaln.androidchartbenchmark;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;
import android.widget.Toast;

import com.codemonkeylabs.fpslibrary.FrameDataCallback;
import com.codemonkeylabs.fpslibrary.TinyDancer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by afzal on 2016-10-13.
 */

public class ChartBenchmarkApp extends Application {

    private List<StatHolder> stats;
    private int currentIndex; // The shared index of the current value being drawn on the screen

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Timber.d("Uptime: " +String.valueOf(SystemClock.uptimeMillis()));

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
                currentStats.add(currentFrameNS, currentIndex, droppedFrames);
            }
        }
    };

    public static ChartBenchmarkApp get(Context context) {
        return (ChartBenchmarkApp) context.getApplicationContext();
    }

    public void addStatHolder(String name) {
        stats.add(new StatHolder(name));
    }

    public void updateCurrentIndex(int index) {
         currentIndex = index;
    }

    public void finishStats() {
        Timber.d("Final stats: " + stats);
        try {
            writeToFile(stats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(List<StatHolder> stats) throws IOException {
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "chart_benchmark.csv";
        File file = new File(filePath);
        file.setWritable(true);
        FileWriter fileWriter = new FileWriter(filePath);

        int maxFrametimeCount = 0;

        for (int i = 0; i < stats.size(); i++) {
            StatHolder stat = stats.get(i);
            // write csv header
            fileWriter.write(stat.name);
            if (i < stats.size() - 1) {
                fileWriter.write(",");
            }

            // get the max count of frametime arrays
            if (maxFrametimeCount < stat.frameTimes.size()) {
                maxFrametimeCount = stat.frameTimes.size();
            }
        }

        fileWriter.write("\n");

        for (int i = 0; i < maxFrametimeCount; i++) {
            for (int j = 0; j < stats.size(); j++) {
                StatHolder stat = stats.get(j);
                if (i < stat.frameTimes.size()) {
                    fileWriter.write(String.valueOf(stat.frameTimes.get(i)));
                }
                if (j < stats.size() - 1) {
                    fileWriter.write(",");
                }
            }
            fileWriter.write("\n");
        }


        fileWriter.flush();
        fileWriter.close();
        Timber.d("Stats file saved");
        Toast.makeText(this, "Results file saved", Toast.LENGTH_SHORT).show();
    }
}
