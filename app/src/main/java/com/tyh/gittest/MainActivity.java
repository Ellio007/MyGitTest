package com.tyh.gittest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tyh.gittest.drawcircleview.DrawCircleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawCircleView drawCircleView = findViewById(R.id.drawCircleView);

        drawCircleView.setmTxtHint("哈哈哈");
        drawCircleView.setmTxtNumber("213");
        drawCircleView.setProgress(0.4f, 0.3f, 0.3f);
    }
}
