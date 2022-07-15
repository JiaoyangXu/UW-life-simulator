package com.example.uw_life_simulator.MiniGame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uw_life_simulator.R;

public class CardGameActivity extends AppCompatActivity {
    private TextView countdowntext;
    private Button countdownbutton;
    private CountDownTimer timer;
    private long timeleft = 6000;
    private boolean start_game = false;
    private boolean same = true;
    int difficulty = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        String first = intent.getStringExtra("et1");
        difficulty = Integer.parseInt(first);
        if(difficulty == 1) {
            setContentView(R.layout.card_game1);
            countdowntext = findViewById(R.id.countdown);
            countdownbutton = findViewById(R.id.countdownbutton);
            countdownbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    start();
                    start_game = true;
                }
            });
            int[] count = {0};
            ImageView im1 = (ImageView) findViewById(R.id.imageView9);
            ImageView im2 = (ImageView) findViewById(R.id.imageView10);
            ImageView im3 = (ImageView) findViewById(R.id.imageView11);
            ImageView im4 = (ImageView) findViewById(R.id.imageView12);
            ImageView im5 = (ImageView) findViewById(R.id.imageView13);
            ImageView im6 = (ImageView) findViewById(R.id.imageView14);
            ImageView im7 = (ImageView) findViewById(R.id.imageView15);
            ImageView im8 = (ImageView) findViewById(R.id.imageView16);

            ImageView im9 = (ImageView) findViewById(R.id.imageView1);
            ImageView im10 = (ImageView) findViewById(R.id.imageView2);
            ImageView im11 = (ImageView) findViewById(R.id.imageView3);
            ImageView im12 = (ImageView) findViewById(R.id.imageView4);
            ImageView im13 = (ImageView) findViewById(R.id.imageView5);
            ImageView im14 = (ImageView) findViewById(R.id.imageView6);
            ImageView im15 = (ImageView) findViewById(R.id.imageView7);
            ImageView im16 = (ImageView) findViewById(R.id.imageView8);

            AlphaAnimation slow_disappear = new AlphaAnimation(1f, 0);
            slow_disappear.setDuration(3000);
            slow_disappear.setFillAfter(true);

            AlphaAnimation quick_disappear = new AlphaAnimation(1f, 0);
            quick_disappear.setDuration(1000);
            quick_disappear.setFillAfter(true);

            AlphaAnimation display = new AlphaAnimation(0, 1f);
            display.setDuration(1000);
            display.setFillAfter(true);

            ImageView[] recorded = {null};
            ImageView[] recorded1 = {null};
            int[] finish = {0};
            int[] score = {0};

            im1.setOnClickListener((v) -> {
                im1.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im1;
                    recorded1[0] = im9;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im1.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im9.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 4) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }

            });

            im2.setOnClickListener((v) -> {
                im2.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im2;
                    recorded1[0] = im10;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im2.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im10.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 4) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });

            im3.setOnClickListener((v) -> {
                im3.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im3;
                    recorded1[0] = im11;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im3.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im11.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 4) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im4.setOnClickListener((v) -> {
                im4.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im4;
                    recorded1[0] = im12;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im4.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im12.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 4) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im5.setOnClickListener((v) -> {
                im5.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im5;
                    recorded1[0] = im13;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im5.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im13.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 4) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im6.setOnClickListener((v) -> {
                im6.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im6;
                    recorded1[0] = im14;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im6.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im14.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 4) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im7.setOnClickListener((v) -> {
                im7.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im7;
                    recorded1[0] = im15;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im7.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im15.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 4) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im8.setOnClickListener((v) -> {
                im8.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im8;
                    recorded1[0] = im16;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im8.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im16.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 4) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
        }else{
            setContentView(R.layout.card_game2);
            countdowntext = findViewById(R.id.countdown);
            countdownbutton = findViewById(R.id.countdownbutton);
            countdownbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    start();
                    start_game = true;
                }
            });
            int[] count = {0};

            ImageView im13 = (ImageView) findViewById(R.id.imageView13);
            ImageView im14 = (ImageView) findViewById(R.id.imageView14);
            ImageView im15 = (ImageView) findViewById(R.id.imageView15);
            ImageView im16 = (ImageView) findViewById(R.id.imageView16);
            ImageView im17 = (ImageView) findViewById(R.id.imageView17);
            ImageView im18 = (ImageView) findViewById(R.id.imageView18);
            ImageView im19 = (ImageView) findViewById(R.id.imageView19);
            ImageView im20 = (ImageView) findViewById(R.id.imageView20);
            ImageView im21 = (ImageView) findViewById(R.id.imageView21);
            ImageView im22 = (ImageView) findViewById(R.id.imageView22);
            ImageView im23 = (ImageView) findViewById(R.id.imageView23);
            ImageView im24 = (ImageView) findViewById(R.id.imageView24);

            ImageView im1 = (ImageView) findViewById(R.id.imageView1);
            ImageView im2 = (ImageView) findViewById(R.id.imageView2);
            ImageView im3 = (ImageView) findViewById(R.id.imageView3);
            ImageView im4 = (ImageView) findViewById(R.id.imageView4);
            ImageView im5 = (ImageView) findViewById(R.id.imageView5);
            ImageView im6 = (ImageView) findViewById(R.id.imageView6);
            ImageView im7 = (ImageView) findViewById(R.id.imageView7);
            ImageView im8 = (ImageView) findViewById(R.id.imageView8);
            ImageView im9 = (ImageView) findViewById(R.id.imageView9);
            ImageView im10 = (ImageView) findViewById(R.id.imageView10);
            ImageView im11 = (ImageView) findViewById(R.id.imageView11);
            ImageView im12 = (ImageView) findViewById(R.id.imageView12);

            AlphaAnimation slow_disappear = new AlphaAnimation(1f, 0);
            slow_disappear.setDuration(3000);
            slow_disappear.setFillAfter(true);

            AlphaAnimation quick_disappear = new AlphaAnimation(1f, 0);
            quick_disappear.setDuration(1000);
            quick_disappear.setFillAfter(true);

            AlphaAnimation display = new AlphaAnimation(0, 1f);
            display.setDuration(1000);
            display.setFillAfter(true);

            ImageView[] recorded = {null};
            ImageView[] recorded1 = {null};
            int[] finish = {0};
            int[] score = {0};

            im13.setOnClickListener((v) -> {
                im13.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im13;
                    recorded1[0] = im1;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im13.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im1.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }

            });

            im14.setOnClickListener((v) -> {
                im14.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im14;
                    recorded1[0] = im2;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im14.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im14.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });

            im15.setOnClickListener((v) -> {
                im15.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im15;
                    recorded1[0] = im3;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im15.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im3.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im16.setOnClickListener((v) -> {
                im16.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im16;
                    recorded1[0] = im4;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im16.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im4.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im17.setOnClickListener((v) -> {
                im17.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im17;
                    recorded1[0] = im5;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im17.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im5.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im18.setOnClickListener((v) -> {
                im18.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im18;
                    recorded1[0] = im6;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im18.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im6.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im19.setOnClickListener((v) -> {
                im19.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im19;
                    recorded1[0] = im7;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im19.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im7.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im20.setOnClickListener((v) -> {
                im20.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im20;
                    recorded1[0] = im8;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im20.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im8.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im21.setOnClickListener((v) -> {
                im21.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im21;
                    recorded1[0] = im9;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im21.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im9.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im22.setOnClickListener((v) -> {
                im22.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im22;
                    recorded1[0] = im10;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im22.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im10.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im23.setOnClickListener((v) -> {
                im23.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im23;
                    recorded1[0] = im11;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im20.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im11.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            im24.setOnClickListener((v) -> {
                im24.startAnimation(quick_disappear);
                count[0]++;
                if (count[0] == 1) {
                    recorded[0] = im24;
                    recorded1[0] = im12;
                }
                if (count[0] == 2) {
                    if (!same) {
                        im24.startAnimation(display);
                        recorded[0].startAnimation(display);
                    } else {
                        recorded[0].setAlpha(0);
                        im12.startAnimation(slow_disappear);
                        recorded1[0].startAnimation(slow_disappear);
                        finish[0]++;
                        score[0] += 25;
                    }
                    count[0] = 0;

                }
                if (finish[0] == 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
        }

    }
    private void start(){
        timer = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long l) {
                timeleft = l;
                updatetimer();
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(CardGameActivity.this,
                        CardGameActivity.class);
                startActivity(intent);
            }
        }.start();

    }
    private void updatetimer(){
        int sec = (int) (timeleft % 60000 /1000);
        String text = "" + sec;
        countdowntext.setText(text);

    }
}
