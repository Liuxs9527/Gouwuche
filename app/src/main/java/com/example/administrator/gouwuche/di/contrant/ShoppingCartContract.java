package com.example.administrator.gouwuche.di.contrant;

public interface ShoppingCartContract {

    public interface IView{

        public void showLoading();

        public void hideLoading();

        public void showData(String mCartString);

    }

    public interface IPresenter<T extends IView>{

        public void attachView(T t);

        public void detachView(T t);

        public void requestData();

    }

    public interface IModel{

        public void containData(OnCallBackListener onCallBackListener);

        public interface OnCallBackListener{
            public void onCallBack(String mCartString);
        }
    }



}
