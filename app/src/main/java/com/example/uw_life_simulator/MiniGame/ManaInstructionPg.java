package com.example.uw_life_simulator.MiniGame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.uw_life_simulator.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManaInstructionPg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mana_instruction_pg);
    }

    public void startGame(View view) {
        int difficulty = getIntent().getIntExtra("Diff", 1);

        Intent intent = new Intent(this, ManaTestMainAct.class);
        intent.putExtra("Diff", difficulty);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                double res = data.getDoubleExtra("Result", 0.0);
                System.out.println(res);

                Intent intent = new Intent();
                intent.putExtra("Result", res);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}