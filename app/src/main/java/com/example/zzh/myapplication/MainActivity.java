package com.example.zzh.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册,跳转,传值
        Button btn = (Button) findViewById(R.id.sign);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t_nameEdit = (EditText) findViewById(R.id.login_name);
                EditText t_passwdEdit = (EditText) findViewById(R.id.login_passwd);
                SignActivity.actionstart(MainActivity.this,t_nameEdit.getText().toString(),
                        t_passwdEdit.getText().toString());
            }
        });
    }
}
