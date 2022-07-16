package com.example.uw_life_simulator.MiniGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.uw_life_simulator.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ManaTestMainAct extends AppCompatActivity {
    Button buttonLeft;
    Button buttonRight;
    Timer timer;
    List<ImageView> manaList = new ArrayList<ImageView>();
    List<Boolean> manaCounting = new ArrayList<Boolean>();

    int difficulty;
    int score;
    int curnum;
    int totalnum;
    int failednum;

    float playerX;

    ImageView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mana_test_main);

        timer = new Timer();
        difficulty = getIntent().getIntExtra("Diff", 1);
        totalnum = 10;
        curnum = 0;
        score = 0;

        player = findViewById(R.id.ball);
        playerX = player.getX();

        buttonLeft = findViewById(R.id.buttonLeft);
        buttonRight = findViewById(R.id.buttonRight);

        switch (difficulty)
        {
            case 5: manaList.add(findViewById(R.id.mana1));
            case 4: manaList.add(findViewById(R.id.mana2));
            case 3: manaList.add(findViewById(R.id.mana3));
            case 2: manaList.add(findViewById(R.id.mana4));
            case 1: manaList.add(findViewById(R.id.mana5));

            default: break;
        }

        for (ImageView img : manaList)
        {
            img.setVisibility(View.VISIBLE);
            manaCounting.add(false);
        }
        run();
    }

    private void run()
    {
        totalnum = 10 * difficulty;
        curnum = 0;
        score = 0;
        Random num = new Random();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (buttonRight.isPressed())
                {
                    player.setX(playerX + 5);
                    playerX = player.getX();
                }
                else if (buttonLeft.isPressed())
                {
                    player.setX(playerX - 5);
                    playerX = player.getX();
                }

                boolean all_gone = true;

                for(int i = 0; i < manaList.size(); i ++)
                {
                    if (!manaCounting.get(i))
                    {
                        if (totalnum > curnum)
                        {
                            double randX = Math.sqrt((num.nextInt(findViewById(R.id.mana_game_layout).getWidth() - 10)) *
                                    (num.nextInt(findViewById(R.id.mana_game_layout).getWidth() - 10)));
                            manaList.get(i).setX((float) randX);
                            manaList.get(i).setY(-i * 100 -60);

                            for(int j = 0; j < manaList.size(); j ++)
                            {
                                if (i != j && hit(manaList.get(i), manaList.get(j)))
                                {
                                    float newX = (float) randX + manaList.get(i).getWidth();
                                    if (newX > findViewById(R.id.mana_game_layout).getWidth())
                                    {
                                        newX = (float) randX - manaList.get(i).getWidth();
                                    }
                                    manaList.get(i).setX(newX);
                                }
                            }
                            curnum ++;
                            manaCounting.set(i, true);
                        }
                    }
                    else
                    {
                        all_gone = false;
                        manaList.get(i).setY(manaList.get(i).getY() + 10);
                        if (hit(player, manaList.get(i)))
                        {
                            score ++;
                            manaCounting.set(i, false);
                            manaList.get(i).setY(-60);
                        }
                        else if (manaList.get(i).getY() >= findViewById(R.id.mana_game_layout).getHeight())
                        {
                            manaCounting.set(i, false);
                            manaList.get(i).setY(-60);
                        }
                    }
                }

                if (curnum >= totalnum && all_gone)
                {
                    timer.cancel();
                    Intent intent = new Intent();
                    double res = score * 1.0/(0.75 * totalnum);
                    if (res > 1.0)
                    {
                        res = 1.0;
                    }

                    intent.putExtra("Result", res * 100);
                    setResult(RESULT_OK, intent);
                    finish();
                    //endGame(findViewById(R.id.layout));
                }
            }
        }, 100, 10);
    }

    private boolean hit(ImageView img1, ImageView img2){
        if((img1.getY() <= img2.getY() + img2.getHeight()) &&
                (img1.getY() + img1.getHeight() >= img2.getY()) &&
                (img1.getX() <= img2.getX() + img2.getWidth()) &&
                (img1.getX() + img1.getWidth() >= img2.getX()))
        {
            return true;
        }
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    public void assignDif(int dif)
    {
        this.difficulty = dif;
        totalnum = dif * 10;
    }

}