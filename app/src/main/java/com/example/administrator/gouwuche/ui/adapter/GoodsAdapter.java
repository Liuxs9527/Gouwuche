package com.example.administrator.gouwuche.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.gouwuche.R;
import com.example.administrator.gouwuche.data.bean.ShoppingCarBean;
import com.example.administrator.gouwuche.ui.Layout.CalculatorView;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<ShoppingCarBean.DataBean.ListBean,BaseViewHolder> {

    OnGoodsItemClickListener onGoodsItemClickListener;

    public void setOnGoodsItemClickListener(OnGoodsItemClickListener onGoodsItemClickListener){
        this.onGoodsItemClickListener = onGoodsItemClickListener;
    }

    //接口回调
    public interface OnGoodsItemClickListener{
        public void onCallBack();
    }

    public GoodsAdapter(int layoutResId, @Nullable List<ShoppingCarBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingCarBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_goodsPrice,"￥:"+item.getPrice());
        helper.setText(R.id.tv_goodsTitle,item.getTitle());
        ImageView iv_goodsIcon = helper.getView(R.id.iv_goodsIcon);
        String imagesString = item.getImages();
        String[] imagesStr = imagesString.split("\\|");
        Glide.with(mContext).load(imagesStr[0]).into(iv_goodsIcon);
        //避免焦点抢占
        final CheckBox cb_goods = helper.getView(R.id.cb_goods);
        cb_goods.setChecked(item.getGoodsChecked());

        cb_goods.setChecked(item.getGoodsChecked());
        cb_goods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //内部条目点击时，将状态及时在bean对象中进行更新
                cb_goods.setOnCheckedChangeListener(null);
                item.setGoodsChecked(isChecked);
                onGoodsItemClickListener.onCallBack();
            }
        });
        //加减器
        CalculatorView calculatorView = helper.getView(R.id.cv_calculatorView);
        calculatorView.setOnCalCulatorLisenter(new CalculatorView.OnCalCulatorLisenter() {
            @Override
            public void onDecrese(int number) {
                //对新增的字段进行减
                item.setDefalutNumber(number);
                onGoodsItemClickListener.onCallBack();
            }

            @Override
            public void onAdd(int number) {
                //对新增的字段进行改动
                item.setDefalutNumber(number);
                onGoodsItemClickListener.onCallBack();
            }
        });


    }
}
