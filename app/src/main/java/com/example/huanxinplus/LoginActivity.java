package com.example.huanxinplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText psw;
    private Button join;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.name);
        psw = (EditText) findViewById(R.id.psw);
        join = (Button) findViewById(R.id.join);
        create = (Button) findViewById(R.id.create);

        join.setOnClickListener(this);
        create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.join:
                login();
                break;
            case R.id.create:
                startActivity(new Intent(this,CreateActivity.class));
                break;
        }
    }

    private void login() {
        final String name = this.name.getText().toString();
        String psw = this.psw.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(psw)){
            EMClient.getInstance().login(name,psw,new EMCallBack() {//回调
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("titile",name);
                    startActivity(intent);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(LoginActivity.this, "登录聊天服务器成功", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                @Override
                public void onProgress(int progress, String status) {

                }

                @Override
                public void onError(int code, String message) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "登录聊天服务器失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }else {
            Toast.makeText(this, "不为空", Toast.LENGTH_SHORT).show();
        }
    }

}
