package com.guy.class22a_and_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Activity_Panel extends AppCompatActivity {

    final int DELAY = 1000;

    private ImageView panel_IMG_question;
    private ProgressBar panel_PRG_time;
    private Button[] panel_BTN_answers;

    private int clock = 10;

    final Handler handler = new Handler();
    private Runnable r = new Runnable() {
        public void run() {
            Log.d("pttt", "Tick: " + clock);
            updateClockView();
            handler.postDelayed(r, DELAY);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        findViews();

        panel_PRG_time.setMax(10);
        panel_PRG_time.setProgress(10);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTicker();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTicker();
    }

    private void startTicker() {
        handler.postDelayed(r, DELAY);
    }

    private void stopTicker() {
        handler.removeCallbacks(r);
    }

    private void updateClockView() {
        clock--;
        panel_PRG_time.setProgress(clock);
    }

    private void findViews() {
        panel_IMG_question = findViewById(R.id.panel_IMG_question);
        panel_PRG_time = findViewById(R.id.panel_PRG_time);

        panel_BTN_answers = new Button[]{
                findViewById(R.id.panel_BTN_answer1),
                findViewById(R.id.panel_BTN_answer2),
                findViewById(R.id.panel_BTN_answer3),
                findViewById(R.id.panel_BTN_answer4)
        };
    }
}