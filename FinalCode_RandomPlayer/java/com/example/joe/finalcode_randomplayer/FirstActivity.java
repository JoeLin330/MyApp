package com.example.joe.finalcode_randomplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends Activity {

    private Button start_button, end_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        processViews();
        processControllers();
    }

    private void processViews() {

        start_button = findViewById(R.id.start_button);
        end_button = findViewById(R.id.end_button);
    }

    private void processControllers() {

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FirstActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
