
package com.example.mvplearnactivity.present;

import com.example.mvplearnactivity.Login.LoginBaseInterface;
import com.example.mvplearnactivity.bean.LoginBean;
import com.example.mvplearnactivity.netWork.NetTask;
import com.example.mvplearnactivity.netWork.RequestStatus;


/**
 * 1 实现LoginBaseInterface.LoginPresenterInterface接口和网络请求状态接口RequestStatus
 * 2 创建构造方法(参数含有网络请求对象和LoginBaseInterface.LoginViewInterface接口)
 * 3 在网络请求的地方调用网络请求中的方法
 * 4 将网络请求接口回调给view
 * */
public class LoginPresenter implements LoginBaseInterface.
        LoginPresenterInterface<LoginBean>, RequestStatus<String> {

    private NetTask<LoginBean> netTask;
    private LoginBaseInterface.LoginViewInterface viewInterface;

    public LoginPresenter(NetTask<LoginBean> netTask,
                          LoginBaseInterface.LoginViewInterface viewInterface)      {
        this.netTask = netTask;
        this.viewInterface = viewInterface;
    }

    @Override
    public void requestToLogin(LoginBean loginBean) {
        netTask.exec(loginBean,this);
    }

    @Override
    public void onStart() {
        viewInterface.showRequestNetDialog();
    }

    @Override
    public void onSuccess(String s) {
        viewInterface.cancelRequestNetDialog();
        viewInterface.LoginSuccess(s);
    }

    @Override
    public void onError() {
        viewInterface.cancelRequestNetDialog();
        viewInterface.LoginUnSuccess();
    }
}
