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
    private boolean same = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
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

        AlphaAnimation alphaAnim3 = new AlphaAnimation(1f, 0);
        alphaAnim3.setDuration(3000);
        alphaAnim3.setFillAfter(true);
        AlphaAnimation alphaAnim = new AlphaAnimation(1f, 0);
        alphaAnim.setDuration(1000);
        alphaAnim.setFillAfter(true);
        AlphaAnimation alphaAnim1 = new AlphaAnimation(0, 1f);
        alphaAnim1.setDuration(1000);
        alphaAnim1.setFillAfter(true);
        AlphaAnimation alphaAnim2 = new AlphaAnimation(1f, 1f);
        alphaAnim1.setDuration(3000);
        alphaAnim1.setFillAfter(true);
        ImageView[] recorded = {null};
        ImageView[] recorded1 = {null};
        int[] finish = {0};
        int[] score = {0};
        im1.setOnClickListener((v) -> {
            im1.startAnimation(alphaAnim);
            count[0]++;
            if (count[0] == 1) {
                recorded[0] = im1;
                recorded1[0] = im9;
            }
            if (count[0] == 2) {
                if (!same) {
                    im1.startAnimation(alphaAnim1);
                    recorded[0].startAnimation(alphaAnim1);
                } else {
                    im1.startAnimation(alphaAnim);

                    im9.startAnimation(alphaAnim3);
                    recorded1[0].startAnimation(alphaAnim3);
                    finish[0]++;
                    score[0] += 25;
                }
                count[0] = 0;
                if(finish[0] == 4){

                }

            }

        });

        im2.setOnClickListener((v) -> {
            im2.startAnimation(alphaAnim);
            count[0]++;
            if (count[0] == 1) {
                recorded[0] = im2;
                recorded1[0] = im10;
            }
            if (count[0] == 2) {
                if (!same) {
                    im2.startAnimation(alphaAnim1);
                    recorded[0].startAnimation(alphaAnim1);
                } else {
                    im10.startAnimation(alphaAnim);
                    recorded1[0].startAnimation(alphaAnim);
                    finish[0]++;
                    score[0] += 25;
                }
                count[0] = 0;
                if(finish[0] == 4){

                }

            }
        });

        im3.setOnClickListener((v) -> {
            im3.startAnimation(alphaAnim);
            count[0]++;
            if (count[0] == 1) {
                recorded[0] = im3;
                recorded1[0] = im11;
            }
            if (count[0] == 2) {
                if (!same) {
                    im3.startAnimation(alphaAnim1);
                    recorded[0].startAnimation(alphaAnim1);
                } else {
                    im3.startAnimation(alphaAnim1);
                    im11.startAnimation(alphaAnim);
                    recorded1[0].startAnimation(alphaAnim);
                    finish[0]++;
                    score[0] += 25;
                }
                count[0] = 0;
                if(finish[0] == 4){

                }

            }
        });
        im4.setOnClickListener((v) -> {
            im4.startAnimation(alphaAnim);
            count[0]++;
            if (count[0] == 1) {
                recorded[0] = im4;
                recorded1[0] = im12;
            }
            if (count[0] == 2) {
                if (!same) {
                    im4.startAnimation(alphaAnim1);
                    recorded[0].startAnimation(alphaAnim1);
                } else {
                    im12.startAnimation(alphaAnim);
                    recorded1[0].startAnimation(alphaAnim);
                    finish[0]++;
                    score[0] += 25;
                }
                count[0] = 0;
                if(finish[0] == 4){

                }

            }
        });
        im5.setOnClickListener((v) -> {
            im5.startAnimation(alphaAnim);
            count[0]++;
            if (count[0] == 1) {
                recorded[0] = im5;
                recorded1[0] = im13;
            }
            if (count[0] == 2) {
                if (!same) {
                    im5.startAnimation(alphaAnim1);
                    recorded[0].startAnimation(alphaAnim1);
                } else {
                    im13.startAnimation(alphaAnim);
                    recorded1[0].startAnimation(alphaAnim);
                    finish[0]++;
                    score[0] += 25;
                }
                count[0] = 0;
                if(finish[0] == 4){

                }

            }
        });
        im6.setOnClickListener((v) -> {
            im6.startAnimation(alphaAnim);
            count[0]++;
            if (count[0] == 1) {
                recorded[0] = im6;
                recorded1[0] = im14;
            }
            if (count[0] == 2) {
                if (!same) {
                    im6.startAnimation(alphaAnim1);
                    recorded[0].startAnimation(alphaAnim1);
                } else {
                    im14.startAnimation(alphaAnim);
                    recorded1[0].startAnimation(alphaAnim);
                    finish[0]++;
                    score[0] += 25;
                }
                count[0] = 0;
                if(finish[0] == 4){

                }

            }
        });
        im7.setOnClickListener((v) -> {
            im7.startAnimation(alphaAnim);
            count[0]++;
            if (count[0] == 1) {
                recorded[0] = im7;
                recorded1[0] = im15;
            }
            if (count[0] == 2) {
                if (!same) {
                    im7.startAnimation(alphaAnim1);
                    recorded[0].startAnimation(alphaAnim1);
                } else {
                    im15.startAnimation(alphaAnim);
                    recorded1[0].startAnimation(alphaAnim);
                    finish[0]++;
                    score[0] += 25;
                }
                count[0] = 0;
                if(finish[0] == 4){

                }

            }
        });
        im8.setOnClickListener((v) -> {
            im8.startAnimation(alphaAnim);
            count[0]++;
            if (count[0] == 1) {
                recorded[0] = im8;
                recorded1[0] = im16;
            }
            if (count[0] == 2) {
                if (!same) {
                    im8.startAnimation(alphaAnim1);
                    recorded[0].startAnimation(alphaAnim1);
                } else {
                    im16.startAnimation(alphaAnim);
                    recorded1[0].startAnimation(alphaAnim);
                    finish[0]++;
                    score[0] += 25;
                }
                count[0] = 0;
                if(finish[0] == 4){

                }

            }
        });

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

//    private AppBarConfiguration appBarConfiguration;
//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.card_game);
//            int[] count = {0};
//            ImageView im1 = (ImageView) findViewById(R.id.imageView18);
//            ImageView im2 = (ImageView) findViewById(R.id.imageView10);
//            ImageView im3 = (ImageView) findViewById(R.id.imageView11);
//            ImageView im4 = (ImageView) findViewById(R.id.imageView12);
//            ImageView im5 = (ImageView) findViewById(R.id.imageView13);
//            ImageView im6 = (ImageView) findViewById(R.id.imageView14);
//            ImageView im7 = (ImageView) findViewById(R.id.imageView15);
//            ImageView im8 = (ImageView) findViewById(R.id.imageView16);
//            ImageView im9 = (ImageView) findViewById(R.id.imageView17);
//
//            AlphaAnimation alphaAnim = new AlphaAnimation(1f, 0);
//            alphaAnim.setDuration(1000);
//            alphaAnim.setFillAfter(true);
//            AlphaAnimation alphaAnim1 = new AlphaAnimation(0, 1f);
//            alphaAnim1.setDuration(2000);
//            alphaAnim1.setFillAfter(true);
//       ImageView[] recorded = {null};
//
//        im1.setOnClickListener((v) -> {
//            im1.startAnimation(alphaAnim);
//            count[0]++;
//            if(count[0] == 1){
//                recorded[0] = im1;
//
//            }
//            if(count[0] == 2) {
//                im1.startAnimation(alphaAnim1);
//                recorded[0].startAnimation(alphaAnim1);
//                count[0] = 0;
//            }
//        });
//
//        im2.setOnClickListener((v) -> {
//            im2.startAnimation(alphaAnim);
//            count[0]++;
//            if(count[0] == 1){
//                recorded[0] = im2;
//            }
//            if(count[0] == 2) {
//                im2.startAnimation(alphaAnim1);
//                recorded[0].startAnimation(alphaAnim1);
//                count[0] = 0;
//            }
//        });
//
//        im3.setOnClickListener((v) -> {
//            im3.startAnimation(alphaAnim);
//            count[0]++;
//            if(count[0] == 1){
//                recorded[0] = im3;
//            }
//            if(count[0] == 2) {
//                im3.startAnimation(alphaAnim1);
//                recorded[0].startAnimation(alphaAnim1);
//                count[0] = 0;
//            }
//        });
//        im4.setOnClickListener((v) -> {
//            im4.startAnimation(alphaAnim);
//            count[0]++;
//            if(count[0] == 1){
//                recorded[0] = im4;
//            }
//            if(count[0] == 2) {
//                im4.startAnimation(alphaAnim1);
//                recorded[0].startAnimation(alphaAnim1);
//                count[0] = 0;
//            }
//        });
//        im5.setOnClickListener((v) -> {
//            im5.startAnimation(alphaAnim);
//            count[0]++;
//            if(count[0] == 1){
//                recorded[0] = im5;
//            }
//            if(count[0] == 2) {
//                im5.startAnimation(alphaAnim1);
//                recorded[0].startAnimation(alphaAnim1);
//                count[0] = 0;
//            }
//        });
//        im6.setOnClickListener((v) -> {
//            im6.startAnimation(alphaAnim);
//            count[0]++;
//            if(count[0] == 1){
//                recorded[0] = im6;
//            }
//            if(count[0] == 2) {
//                im6.startAnimation(alphaAnim1);
//                recorded[0].startAnimation(alphaAnim1);
//                count[0] = 0;
//            }
//        });
//        im7.setOnClickListener((v) -> {
//            im7.startAnimation(alphaAnim);
//            count[0]++;
//            if(count[0] == 1){
//                recorded[0] = im7;
//            }
//            if(count[0] == 2) {
//                im7.startAnimation(alphaAnim1);
//                recorded[0].startAnimation(alphaAnim1);
//                count[0] = 0;
//            }
//        });
//        im8.setOnClickListener((v) -> {
//            im8.startAnimation(alphaAnim);
//            count[0]++;
//            if(count[0] == 1){
//                recorded[0] = im8;
//            }
//            if(count[0] == 2) {
//                im8.startAnimation(alphaAnim1);
//                recorded[0].startAnimation(alphaAnim1);
//                count[0] = 0;
//            }
//        });
//        im9.setOnClickListener((v) -> {
//            im9.startAnimation(alphaAnim);
//            count[0]++;
//            if(count[0] == 1){
//                recorded[0] = im9;
//            }
//            if(count[0] == 2) {
//                im9.startAnimation(alphaAnim1);
//                recorded[0].startAnimation(alphaAnim1);
//                count[0] = 0;
//            }
//        });
//
//
//
//
//
//
//
//
//        }
}
