package me.henry.canutecore.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import me.henry.canutecore.app.Canute;
import me.henry.canutecore.net.RestClient;
import me.henry.canutecore.net.callback.ISuccess;
import me.henry.canutecore.ui.recycler.DataConverter;
import me.henry.canutecore.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by zj on 2017/9/8.
 * me.henry.canutecore.ui.refresh
 * 处理的方法和回调尽量放到一个类里面处理
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;
    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                           RecyclerView recyclerView,
                           DataConverter converter, PagingBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }
    //生成该类对象的静态工厂方法
    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());
    }


    public void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Canute.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }
public void firstPage(String url){
    BEAN.setDelayed(1000);
    RestClient.builder()
            .url(url)
            .success(new ISuccess() {
                @Override
                public void onSuccess(String response) {
                    final JSONObject object = JSON.parseObject(response);
                    BEAN.setTotal(object.getInteger("total")).setPageSize(object.getInteger("page_size"));
                    //设置Adapter
                    mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                    mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                    RECYCLERVIEW.setAdapter(mAdapter);
                    BEAN.addIndex();
                }
            })
            .build()
            .get();
}
    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
