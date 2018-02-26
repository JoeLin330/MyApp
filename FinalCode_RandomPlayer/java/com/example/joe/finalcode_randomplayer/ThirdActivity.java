package com.example.joe.finalcode_randomplayer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends Activity {

    private TextView third_title, times01, times02, times03, times04, times05;
    private int[] array = new int[5];
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        processViews();

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            array = bundle.getIntArray("array");
            name = bundle.getString("name");
        }

        String a = String.valueOf(array[0]);
        String b = String.valueOf(array[1]);
        String c = String.valueOf(array[2]);
        String d = String.valueOf(array[3]);
        String e = String.valueOf(array[4]);

        third_title.setText("本日戰績(" + name + ")");
        times01.setText(a);
        times02.setText(b);
        times03.setText(c);
        times04.setText(d);
        times05.setText(e);
    }

    private void processViews() {

        this.setFinishOnTouchOutside(false);

        third_title = findViewById(R.id.third_title);
        times01 = findViewById(R.id.times01);
        times02 = findViewById(R.id.times02);
        times03 = findViewById(R.id.times03);
        times04 = findViewById(R.id.times04);
        times05 = findViewById(R.id.times05);
    }

    public void clickButton05(View view) {
        finish();
    }
}
