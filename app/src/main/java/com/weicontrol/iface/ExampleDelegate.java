package com.weicontrol.iface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canutecore.net.RestClient;
import me.henry.canutecore.net.callback.ISuccess;


/**
 * Created by zj on 2017/8/14.
 * me.henry.canute
 */

public class ExampleDelegate extends CanuteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("caonimei","response="+response);
                        Toast.makeText(getContext(),response.substring(0,10),Toast.LENGTH_SHORT).show();
                    }
                }).build().get();
    }
}
