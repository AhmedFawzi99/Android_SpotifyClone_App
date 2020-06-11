package com.example.spotifyclone;

import java.time.Duration;

public class MusicControl {


    public static final int MAX_PROGRESS = 10000;

    public String milliSecondsToTIme(long milliSeconds) {


        String finalTimeString = "";
        String secondsString = "";

        int hours = (int) (milliSeconds / (1000 * 60 * 60));
        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {

            finalTimeString = hours + ":";
        }
        if (seconds < 10) {

            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        finalTimeString = finalTimeString + minutes + ":" + secondsString;
        return finalTimeString;
    }

    public int getSeekBarProgress(long currentDuration, long totalDuration) {

        Double progrees = (double) 0;
        progrees = (((double) currentDuration) / totalDuration) * MAX_PROGRESS;
        return progrees.intValue();
    }
    public int prig(int progress , int totalDuration){
        int currentDuration=0;
        totalDuration=(int) (totalDuration/1000);
        currentDuration=(int)((double) progress/MAX_PROGRESS)*totalDuration;
        return currentDuration*1000;
    }
}
