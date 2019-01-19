package com.example.administrator.gouwuche.di.presenter;

import com.example.administrator.gouwuche.di.contrant.ShoppingCartContract;
import com.example.administrator.gouwuche.di.model.ShoppinCartModel;

import java.lang.ref.SoftReference;

public class ShoppingCarPresenter implements ShoppingCartContract.IPresenter<ShoppingCartContract.IView> {

    ShoppingCartContract.IView iView;
    private SoftReference<ShoppingCartContract.IView> softReference;
    private ShoppingCartContract.IModel model;

    @Override
    public void attachView(ShoppingCartContract.IView view) {
        this.iView = view;
        softReference = new SoftReference<>(iView);
        model = new ShoppinCartModel();
    }

    @Override
    public void detachView(ShoppingCartContract.IView view) {
        softReference.clear();
    }

    @Override
    public void requestData() {
        //给用户等待提示
        iView.showLoading();
        model.containData(new ShoppingCartContract.IModel.OnCallBackListener() {
            @Override
            public void onCallBack(String mCartString) {
                //去掉进度条
                iView.hideLoading();
                //数据回显
                iView.showData(mCartString);
            }
        });
    }
}
