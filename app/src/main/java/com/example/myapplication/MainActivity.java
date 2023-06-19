package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    // x=1
//    o=0
//    null=2
    Boolean active = true;
    Boolean gameActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void playerTap(View view) {

        ImageView img = (ImageView) view;
        TextView text = findViewById(R.id.status);
        int tappedNum = Integer.parseInt(img.getTag().toString());
        if (gameActive == false) {
            reset(view);
            Handler handler = new Handler();
            int delayInMillis = 2000; // 2 seconds

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // Code to execute after the delay
                    text.setText("O's Turn -Tap to Play");
                    gameActive = true;

                }
            };

            handler.postDelayed(runnable, delayInMillis);
        }
        if (gameState[tappedNum - 1] == 2 && gameActive) {
            Log.d("TAG", "playerTap: " + active.toString());
            if (active) {
                img.setImageResource(R.drawable.x);
                active = false;
                gameState[tappedNum - 1] = 1;
                text.setText("O's Turn -Tap to Play");
            } else {
                img.setImageResource(R.drawable.circle);
                active = true;
                gameState[tappedNum - 1] = 0;
                text.setText("X's Turn -Tap to Play");
            }

            for (int[] winPosition : winPosition) {
                Log.d("TAG", "playerTap: " + Arrays.toString(winPosition));
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]]) {
//some one has one
                    String winner;
                    if (gameState[winPosition[0]] == 0) {
                        winner = "O is the winner";
                        text.setText(winner);
                        gameActive = false;
                    } else if (gameState[winPosition[0]] == 1) {
                        winner = "X is the winner";
                        text.setText(winner);
                        gameActive = false;
                    }

                }

            }
        }


    }


    private void reset(View view) {
        for (int j = 0; j < 9; j++) {
            Log.d("TAG", "reset: " + j);
            gameState[j] = 2;
        }
        for (int j = 1; j < 10; j++) {
            int imageViewId = getResources().getIdentifier("imageView" + j, "id", getPackageName());
            ImageView imageView = findViewById(imageViewId);
            imageView.setImageResource(0);
        }

    }
}