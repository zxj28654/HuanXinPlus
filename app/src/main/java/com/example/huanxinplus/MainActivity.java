package com.example.huanxinplus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        String titl = getIntent().getStringExtra("title");
       title.setText(titl);
    }
}
