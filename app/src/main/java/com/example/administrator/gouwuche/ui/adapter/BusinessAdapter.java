package com.example.administrator.gouwuche.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.gouwuche.R;
import com.example.administrator.gouwuche.data.bean.ShoppingCarBean;

import java.util.List;

public class BusinessAdapter extends BaseQuickAdapter<ShoppingCarBean.DataBean,BaseViewHolder> {
    OnBusinessItemClickLisenter onBusinessItemClickLisenter;

    public interface OnBusinessItemClickLisenter{
        public void onCallBack();
    }

    public void  setOnBusinessItemClickLisenter(OnBusinessItemClickLisenter onBusinessItemClickLisenter){
        this.onBusinessItemClickLisenter = onBusinessItemClickLisenter;
    }

    private List<ShoppingCarBean.DataBean> businessList;

    public BusinessAdapter(int layoutResId, @Nullable List<ShoppingCarBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingCarBean.DataBean item) {
        //设置商家名称
        helper.setText(R.id.tv_business_name,item.getSellerName());
        //设置商家下的子商品条目
        RecyclerView rv_goods = helper.getView(R.id.rv_goods);
        //避免焦点抢占
        final CheckBox cb_business = helper.getView(R.id.cb_business);
        cb_business.setOnCheckedChangeListener(null);


        //获取商家条目是否选中状态
        cb_business.setChecked(item.getBusinessChecked());
        //子商品条目数据源
        List<ShoppingCarBean.DataBean.ListBean> goodsList = item.getList();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        //设置子条目适配器
        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.recycler_goods_item, goodsList);
        rv_goods.setLayoutManager(manager);
        rv_goods.setAdapter(goodsAdapter);

        //内层商品条目和外层的商品类别联动
        goodsAdapter.setOnGoodsItemClickListener(new GoodsAdapter.OnGoodsItemClickListener() {
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i < item.getList().size(); i++) {
                    result = result & item.getList().get(i).getGoodsChecked();
                }
                cb_business.setChecked(result);
                goodsAdapter.notifyDataSetChanged();
                onBusinessItemClickLisenter.onCallBack();
            }
        });
        cb_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < item.getList().size(); i++) {
                    item.getList().get(i).setGoodsChecked(cb_business.isChecked());
                }
                item.setBusinessChecked(cb_business.isChecked());
                notifyDataSetChanged();
                onBusinessItemClickLisenter.onCallBack();
            }
        });

    }
}
