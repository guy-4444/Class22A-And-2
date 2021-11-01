package com.guy.class22a_and_2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.guy.class22a_and_2.DataManager;
import com.guy.class22a_and_2.Question;
import com.guy.class22a_and_2.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_Panel extends AppCompatActivity {

    final int DELAY = 1000;

    private ImageView panel_IMG_question;
    private ProgressBar panel_PRG_time;
    private Button[] panel_BTN_answers;

    private Timer timer = new Timer();
    private int clock = 10;

    private ArrayList<Question> questions;
    private int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        findViews();
        initViews();

        panel_PRG_time.setMax(10);
        panel_PRG_time.setProgress(10);

        questions = DataManager.generateQuestions();
    }

    private void initViews() {
        for (int i = 0; i < panel_BTN_answers.length; i++) {
            int finalI = i;
            panel_BTN_answers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answered(finalI);
                }
            });
        }
    }

    private void answered(int buttonIndex) {
        vibrate();
        if (buttonIndex == 0) {

        }

        stopTicker();
        nextQuestion();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    private void nextQuestion() {
        clock = 10;
        currentQuestion++;
        updateUI();
    }

    private void updateUI() {
        startTicker();
        Question q = questions.get(currentQuestion);

        Glide
                .with(this)
                .load(q.getImage())
                .into(panel_IMG_question);

        for (int i = 0; i < q.getAnswers().size(); i++) {
            panel_BTN_answers[i].setText(q.getAnswers().get(i));
        }
    }

    private void startTicker() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d("pttt", "A clock=" + clock + " Thread=" + Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("pttt", "B clock=" + clock + " Thread=" + Thread.currentThread().getName());
                        updateClockView();
                    }
                });
            }
        }, 0, 1000);
    }

    private void stopTicker() {
        timer.cancel();
    }

    private void updateClockView() {
        clock--;

        if (clock == 0) {
            answered(-1);
        }

        panel_PRG_time.setProgress(clock);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTicker();
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