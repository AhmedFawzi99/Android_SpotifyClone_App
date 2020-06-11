package com.example.spotifyclone;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Locale;

/**
 * TimerClass is Responsible for opening and making the Timer Function to Make the music Stop after a certain amount of time the user chooses. <br>
 * Source: <a href="https://codinginflow.com/tutorials/android/countdowntimer/part-1-countdown-timer">https://codinginflow.com/tutorials/android/countdowntimer/part-1-countdown-timer</a> <br>
 * Modified By : Ahmed Mahmoud Fawzi <br>
 * @version 1.0
 */
public class TimerClass extends BottomSheetDialogFragment {

    private TextView five2; private LinearLayout five1;
    private TextView ten2; private LinearLayout ten1;
    private TextView fiften2; private LinearLayout fiften1;
    private TextView thirty2; private LinearLayout thirty1;
    private TextView forty2; private LinearLayout forty1;
    private TextView hour2; private LinearLayout hour1;
    private TextView turnoff2; private LinearLayout turnoff1;
    private LinearLayout endOF;
    public static int check=0;
    private RelativeLayout layoutti;
    private TextView timer3;
    private static CountDownTimer mCountDownTimer;
    private Handler mHandler = new Handler();
    public static int mTimerRunning=0;

    /**
     * This is Variable that with each option the variable is changed to count down in reverse with the assigned time.
     */
    private long mTimeLeftInMillis;




    /**
     * The OnCreateView in this Class is responsible for assigning the variables and opening the layout assigned. <br>
     * Inside the OnCreateView the onclicklistener for the buttons is called to call its Function. <br>
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View t = inflater.inflate(R.layout.timerpage, container, false);
        five1=t.findViewById(R.id.five1);
        five2=t.findViewById(R.id.five2);
        ten1=t.findViewById(R.id.ten1);
        ten2=t.findViewById(R.id.ten2);
        fiften1=t.findViewById(R.id.fiften1);
        fiften2=t.findViewById(R.id.fiften2);
        thirty1=t.findViewById(R.id.thirty1);
        thirty2=t.findViewById(R.id.thirty2);
        forty1=t.findViewById(R.id.forty1);
        forty2=t.findViewById(R.id.forty2);
        hour1=t.findViewById(R.id.hour1);
        hour2=t.findViewById(R.id.hour2);
        turnoff1=t.findViewById(R.id.turnoff1);
        turnoff2=t.findViewById(R.id.turnoff2);
        layoutti=t.findViewById(R.id.timerpage);

        if(check==0){
            turnoff1.setVisibility(View.GONE);
        }else{

            turnoff1.setVisibility(View.VISIBLE);
        }


        /**
         * When Timer five is pressed the milliseconds is set to 5 minutes and the function is called.
         */
        five1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer(300000);
                mTimeLeftInMillis=300000;
                startTimer();
                check=1;
                dismiss();
            }
        });

        /**
         * When Timer ten is pressed the milliseconds is set to 10 minutes and the function is called.
         */
        ten1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    stopTimer(600000);
                    mTimeLeftInMillis = 600000;
                    startTimer();
                check=1;
                dismiss();

            }
        });

        /**
         * When Timer fifteen is pressed the milliseconds is set to 15 minutes and the function is called.
         */
        fiften1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer(900000);
                mTimeLeftInMillis=900000;
                startTimer();
                check=1;
                dismiss();
            }
        });

        /**
         * When Timer thirty is pressed the milliseconds is set to 30 minutes and the function is called.
         */
        thirty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer(1800000);
                mTimeLeftInMillis=1800000;
                startTimer();
                check=1;
                dismiss();
            }
        });

        /**
         * When Timer forty is pressed the milliseconds is set to 45 minutes and the function is called.
         */
        forty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer(2400000);
                mTimeLeftInMillis=2400000;
                startTimer();
                check=1;
                dismiss();
            }
        });

        /**
         * When Timer hour is pressed the milliseconds is set to 60 minutes and the function is called.
         */
        hour1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer(3600000);
                mTimeLeftInMillis=3600000;
                startTimer();
                check=1;
                dismiss();
            }
        });

        /**
         * Turning off timer
         */
        turnoff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer(0);
                check=0;
                dismiss();
            }
        });



        layoutti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return t;
    }

    @Override
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setStyle(BottomSheetDialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);


    }

    /**
     * This Function is responsible to do an action after a certain amount of time. <br>
     * It creates an  object of the CountDownTimer CLass giving it the time assigned with the click of each button. <br>
     * It then Does a function when a time reaches and sets the Text and the drawable resource for the Timer button in the MorePageDown. <br>
     */
    private void startTimer() {
        final MusicActivity main = (MusicActivity) getActivity();
         mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                MorePageDown more = MorePageDown.getInstance();
                more.timer2.setImageResource(R.drawable.moon_waning_crescent);
                more.timer3.setText("Sleep timer");
                main.stopTimer();
                mTimerRunning=0;
                check=0;
            }
        }.start();
        mTimerRunning=1;

    }

    /**
     * A function to calculate to stop timer at end of music
     */
    private void endstartTimer() {
        final MusicActivity main = (MusicActivity) getActivity();
        mCountDownTimer = new CountDownTimer(main.getRemaining(), 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                mTimeLeftInMillis = main.getRemaining();
                updateCountDownText();


            }

            @Override
            public void onFinish() {
                MorePageDown more = MorePageDown.getInstance();
                more.timer2.setImageResource(R.drawable.moon_waning_crescent);
                more.timer3.setText("Sleep timer");
                main.stopTimer();
                mTimerRunning=0;
                check=0;
            }
        }.start();
        mTimerRunning=1;
    }

    /**
     * This Function is Responsible for stopping the timer. <br>
     * @param a
     */
    private void stopTimer(int a) {
        if(this.mTimerRunning==1) {
            mCountDownTimer.cancel();
            mTimerRunning = 0;
            resetTimer(a);
        }
        else{
            return;
        }
    }
    /**
     * a function responsible for Resetting the timer and giving a new Time parameter. <br>
     * @param a New Time Parameter
     */
    private void resetTimer(int a) {
        mTimeLeftInMillis = a;
        updateCountDownText();
    }

    /**
     * A Function Responsible to pause the Timer. <br>
     */
    public void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = 0;
    }

    /**
     * This Function is Responsible to Continuously update how many Min left and Output them as text for the user in the Sleep Timer button and TextView. <br>
     */
    public void updateCountDownText() {

        MorePageDown more = MorePageDown.getInstance();
        more.timer2.setImageResource(R.drawable.cresecentgreen);
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        minutes=minutes+1;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%d", minutes);
        more.timer3.setText("Sleep timer"+" - " +timeLeftFormatted+" min left");
    }
    public void updateendCountDownText() {

        MorePageDown more = MorePageDown.getInstance();
        more.timer2.setImageResource(R.drawable.cresecentgreen);
        more.timer3.setText("Sleep timer"+" - " +"End of track");
    }

}