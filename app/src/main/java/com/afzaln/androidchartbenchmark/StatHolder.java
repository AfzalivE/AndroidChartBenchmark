package com.afzaln.androidchartbenchmark;

import java.util.ArrayList;

/**
 * Created by afzal on 2017-08-21.
 */

class StatHolder {

    String name;
    int totalDroppedFrames;
    ArrayList<Long> frameTimes;
    ArrayList<Integer> indexes;

    StatHolder(String name) {
        this.name = name;
        frameTimes = new ArrayList<>();
        indexes = new ArrayList<>();
    }

    public void add(long currentFrameNS, int currentIndex, int droppedFrames) {
        totalDroppedFrames += droppedFrames;
        indexes.add(currentIndex);
        frameTimes.add(currentFrameNS);
    }
}
