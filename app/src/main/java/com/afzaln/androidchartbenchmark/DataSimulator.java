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
    private static final int DEFAULT_PERIOD = 20000000; // 2e7 ns = 50Hz
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private final DataListener dataListener;
    private int period = DEFAULT_PERIOD;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            float x = RANDOM.nextFloat();
            float y = RANDOM.nextFloat();
            float z = RANDOM.nextFloat();
            dataListener.onDataAvailable(new float[]{x, y, z});
        }
    };

    /**
     * Rate in Hz, period in nanoseconds
     */
    public DataSimulator(DataListener dataListener, int rate) {
        this.dataListener = dataListener;
        this.period = 1000000000 / rate;
    }

    public void start() {
        executorService.scheduleAtFixedRate(runnable, 0, period, TimeUnit.NANOSECONDS);
    }

    public void stop() {
        executorService.shutdown();
    }

    public interface DataListener {
        void onDataAvailable(float[] points);
    }
}
