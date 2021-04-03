package com.example.mvplearnactivity.model;

import android.text.TextUtils;

import com.example.mvplearnactivity.bean.LoginBean;
import com.example.mvplearnactivity.netWork.NetTask;
import com.example.mvplearnactivity.netWork.RequestStatus;

public class LoginModel implements NetTask<LoginBean> {
    @Override
    public void exec(LoginBean loginBean, RequestStatus requestStatus) {
        String name = loginBean.getUserName();
        String pwd = loginBean.getUserPwd();

        if(name == null && TextUtils.isEmpty(name)){
            requestStatus.onError();
            return;
        }
        if(pwd == null && TextUtils.isEmpty(pwd)){
            requestStatus.onError();
            return;
        }

        //这里处理和网络交互的逻辑
        requestStatus.onStart();
        //模拟网络请求

        if (!name.equals("18888888888")){
            requestStatus.onError();
        }else {
            if(!pwd.equals("123456")){
                requestStatus.onError();
                return;
            }else {
                requestStatus.onSuccess("恭喜你登录成功");
            }
        }
    }
}
