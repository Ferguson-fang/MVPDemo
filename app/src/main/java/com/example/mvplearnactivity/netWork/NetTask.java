package com.example.mvplearnactivity.netWork;
/**
 * 定义网络请求的方法接口
 * */
public interface NetTask<T> {
    /**
     *
     * @param t 请求参数类型不确定
     * @param requestStatus 网络请求状态回调
     * */
    //exec()中传入RequestStatus接口，是为了当网络请求有结果后回调给LoginPresenter
    void exec(T t,RequestStatus requestStatus);
}
