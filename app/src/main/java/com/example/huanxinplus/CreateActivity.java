package com.example.huanxinplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText psw;
    private Button join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        initView();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.name);
        psw = (EditText) findViewById(R.id.psw);
        join = (Button) findViewById(R.id.join);

        join.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.join:
                regist();
                break;
        }
    }

    private void regist() {
        final String name = this.name.getText().toString();
        final String psw = this.psw.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(psw)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // Toast.makeText(CereateActivity.this, name+psw, Toast.LENGTH_SHORT).show();
                    try {
                        EMClient.getInstance().createAccount(name, psw);//同步方法

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CreateActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                CreateActivity.this.finish();
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else {
            Toast.makeText(this, "不为空", Toast.LENGTH_SHORT).show();
        }
    }
}
