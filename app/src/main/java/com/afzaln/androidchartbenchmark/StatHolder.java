package com.afzaln.androidchartbenchmark;

import java.util.ArrayList;

/**
 * Created by afzal on 2017-08-21.
 */

class StatHolder {

    String name;
    int totalDroppedFrames;
    ArrayList<Long> frameTimes;

    StatHolder(String name) {
        this.name = name;
        frameTimes = new ArrayList<>();
    }

    public void add(long currentFrameNS, int droppedFrames) {
        totalDroppedFrames += droppedFrames;
        frameTimes.add(currentFrameNS);
    }
}
