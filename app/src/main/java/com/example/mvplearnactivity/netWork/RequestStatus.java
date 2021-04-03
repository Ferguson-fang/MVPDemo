package com.example.mvplearnactivity.netWork;

/**
 * 网络请求都是共通的，所以把网络部分单独抽象出来
 * 定义网络请求接口，包含开始，成功，不成功。
 * */
public interface RequestStatus<T> {
    void onStart();

    void onSuccess(T t);

    void onError();
}
