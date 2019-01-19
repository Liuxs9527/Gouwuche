package com.example.administrator.gouwuche.di.model;

import com.example.administrator.gouwuche.data.Constant;
import com.example.administrator.gouwuche.di.contrant.ShoppingCartContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class ShoppinCartModel implements ShoppingCartContract.IModel {
    @Override
    public void containData(final OnCallBackListener onCallBackListener) {
        OkGo.<String>get(Constant.SHOPPINGCART_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String resposneData = response.body().toString();
                //回传给P层
                onCallBackListener.onCallBack(resposneData);
            }
        });
    }
}
