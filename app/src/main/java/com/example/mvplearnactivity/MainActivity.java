package com.example.mvplearnactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvplearnactivity.view.LoginActivity;

import java.util.List;

/**
 * 核心思想：把原来的UI逻辑抽象成VIEW接口，把原来的业务逻辑抽象成PRESENTER接口，MODEL不变
 *
 * 三层结构: View presenter model
 * */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        login = findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.login:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
}