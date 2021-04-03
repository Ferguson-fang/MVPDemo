package com.example.mvplearnactivity.Login;

/**
 * 1 定义LoginActivity需要实现的接口LoginViewInterface
 * 2 定义LoginPresenter需要实现的接口LoginPresenterInterface,
 *   用于LoginActivity和LoginPresenter交互
 * */
public class LoginBaseInterface {
    public interface LoginPresenterInterface<T>{
        void requestToLogin(T t);
    }

    public interface LoginViewInterface<T>{
        //展示网络请求的dialog
        void showRequestNetDialog();
        //finish掉dialog
        void cancelRequestNetDialog();
        //登录成功
        void LoginSuccess(T t);
        //登录不成功
        void LoginUnSuccess();
    }
}
