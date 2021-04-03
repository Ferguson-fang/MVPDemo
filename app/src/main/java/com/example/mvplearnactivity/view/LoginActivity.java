package com.example.mvplearnactivity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvplearnactivity.Login.LoginBaseInterface;
import com.example.mvplearnactivity.MainActivity;
import com.example.mvplearnactivity.R;
import com.example.mvplearnactivity.bean.LoginBean;
import com.example.mvplearnactivity.model.LoginModel;
import com.example.mvplearnactivity.present.LoginPresenter;

/**
 * 1 实现LoginBaseInterface中的LoginViewInterface并重载里面的方法
 * 2 得到网络请求的对象，将它和自己传给LoginPresenter以得到LoginPresenter对象
 * 3 点击登录按钮的时候，调用LoginPresenter里面的方法
 * */
public class LoginActivity extends AppCompatActivity implements
        LoginBaseInterface.LoginViewInterface, View.OnClickListener {



    /**
     * 手机号
     * */
    private ProgressBar mLoginPb;

    /**
     * 密码
     * */
    private EditText mUserMobileEt;

    /**
     * 登录
     * */
    private EditText mUserPwdEt;

    private TextView mLoginConfirmTv;
    private CheckBox checkBox;
    private LoginPresenter loginPresenter;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean isRemember;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initPreferences();
        LoginModel loginModel = new LoginModel();
        loginPresenter = new LoginPresenter(loginModel,this);
    }

        @Override
        public void showRequestNetDialog() {

        }

        @Override
        public void cancelRequestNetDialog() {
            if(!mLoginPb.isShown()){
                mLoginPb.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void LoginSuccess(Object o) {
            String account = mUserMobileEt.getText().toString();
            String password = mUserPwdEt.getText().toString();
            if(mLoginPb.isShown()){
                mLoginPb.setVisibility(View.INVISIBLE);
            }
            editor = pref.edit();
            /*
            mLoginConfirmTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String account = mUserMobileEt.getText().toString();
                    String password = mUserPwdEt.getText().toString();
                    if(account == "18888888888" && password == "123456"){
                        if(checkBox.isChecked()){
                            editor.putBoolean("remember_password",true);
                            editor.putString("account",account);
                            editor.putString("password",password);
                        }else {
                            editor.clear();
                        }
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"account or password is invalid",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });*/
            Toast.makeText(getApplicationContext(),(String)o,Toast.LENGTH_SHORT).show();
            if(checkBox.isChecked()){
                editor.putBoolean("remember_password",true);
                editor.putString("account",account);
                editor.putString("password",password);
            }else {
                editor.clear();
            }
            editor.apply();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        @Override
        public void LoginUnSuccess() {
            if(mLoginPb.isShown()){
                mLoginPb.setVisibility(View.INVISIBLE);
            }
            Toast.makeText(LoginActivity.this,"account or password is invalid",
                    Toast.LENGTH_SHORT).show();
        }

        private void initView(){
            mLoginPb = findViewById(R.id.login_pb);
            mUserMobileEt = findViewById(R.id.user_mobile_et);
            mUserPwdEt = findViewById(R.id.user_pwd_et);
            mLoginConfirmTv = findViewById(R.id.login_confirm_tv);
            mLoginConfirmTv.setOnClickListener(this);
            checkBox = findViewById(R.id.checkbox);
        }
        private void initPreferences(){
            pref = getPreferences(Context.MODE_PRIVATE);
            isRemember = pref.getBoolean("remember_password",false);
            if(isRemember){
                //将账号密码都设置在文本内
                String account = pref.getString("account","");
                String password = pref.getString("password","");
                mUserMobileEt.setText(account);
                mUserPwdEt.setText(password);
                checkBox.setChecked(true);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                default:
                    break;
                case R.id.login_confirm_tv:
                    loginPresenter.requestToLogin(new LoginBean(mUserMobileEt.getText().
                            toString().trim(),mUserPwdEt.getText().toString().trim()));
                    break;
            }
        }

}