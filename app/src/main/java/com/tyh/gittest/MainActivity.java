package com.tyh.gittest;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.tyh.gittest.drawcircleview.DrawCircleView;
import com.tyh.gittest.drawcircleview.OtherDrawCircleView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private OtherDrawCircleView otherDrawCircleView;

    private MyTimer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawCircleView drawCircleView = findViewById(R.id.drawCircleView);

        drawCircleView.setmTxtHint("312");
        drawCircleView.setmTxtNumber("213");
        drawCircleView.setProgress(0.4f, 0.3f, 0.3f);

        otherDrawCircleView = findViewById(R.id.otherDrawCircleView);
        myTimer = new MyTimer(100 * 1000, 1000);
        myTimer.start();
    }

    @Override
    protected void onDestroy() {
        myTimer.cancel();
        super.onDestroy();
    }

    class MyTimer extends CountDownTimer {

        MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (((100 * 1000) - millisUntilFinished) / 1000);
            otherDrawCircleView.setProgress(progress);
        }

        @Override
        public void onFinish() {
            otherDrawCircleView.setProgress(100);
            Toast.makeText(MainActivity.this, "Finish", Toast.LENGTH_SHORT).show();
        }
    }
}
