package com.afzaln.androidchartbenchmark;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by afzal on 2016-10-13.
 */

public class DataSimulator {

    private static final Random RANDOM = new Random();
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private final DataListener dataListener;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            float x = RANDOM.nextFloat();
            float y = RANDOM.nextFloat();
            float z = RANDOM.nextFloat();
            dataListener.onDataAvailable(new float[]{x, y, z});
        }
    };

    public DataSimulator(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    public void start() {
        executorService.scheduleAtFixedRate(runnable, 0, 20, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        executorService.shutdown();
    }

    public interface DataListener {
        void onDataAvailable(float[] points);
    }
}
