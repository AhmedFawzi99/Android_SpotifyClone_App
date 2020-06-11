package com.example.spotifyclone;

import java.time.Duration;

/**
 * This Class is Mainly to Calculate the Duration of the music and the Current Duration Time.
 * Helping Source: <a href="https://www.youtube.com/watch?v=Qr88MxPijN4">https://www.youtube.com/watch?v=Qr88MxPijN4</a> <br>
 * Helping Source: <a href="https://www.youtube.com/watch?v=ZRffTnk8ug8&list=PL9vy4y29rrd4x5pAbowit8gpjsXAai0yF">https://www.youtube.com/watch?v=ZRffTnk8ug8&list=PL9vy4y29rrd4x5pAbowit8gpjsXAai0yF</a> <br>
 * Helping Source: <a href="https://www.youtube.com/watch?v=cbDHgoCVWak https://www.youtube.com/watch?v=bslTj2zDARc">https://www.youtube.com/watch?v=cbDHgoCVWak https://www.youtube.com/watch?v=bslTj2zDARc</a> <br>
 * Helping Source <a href="https://developer.android.com/guide/topics/media-apps/volume-and-earphones">https://developer.android.com/guide/topics/media-apps/volume-and-earphones</a>
 * @author Ahmed Mahmoud Fawzi <br>
 * @version 1.0
 */
public class MusicControl {


    public static final int MAX_PROGRESS = 10000;

    /**
     * This Function Converts the Milliseconds of the music Time and Reformats it into minutes seconds and Hours .
     * @param milliSeconds  The Time in Milliseconds
     * @return
     */
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

    /**
     * Getting the seekbar progress.
     * @param currentDuration
     * @param totalDuration
     * @return
     */
    public int getSeekBarProgress(long currentDuration, long totalDuration) {

        Double progrees = (double) 0;
        progrees = (((double) currentDuration) / totalDuration) * MAX_PROGRESS;
        return progrees.intValue();
    }

    /**
     * Returning the Current Position
     * @param progress
     * @param totalDuration
     * @return
     */
    public int prig(int progress , int totalDuration){
        int currentDuration=0;
        totalDuration=(int) (totalDuration/1000);
        currentDuration=(int)((double) progress/MAX_PROGRESS)*totalDuration;
        return currentDuration*1000;
    }
}
