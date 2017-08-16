package me.henry.canutecore.net;

import android.content.Context;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

import me.henry.canutecore.net.callback.IError;
import me.henry.canutecore.net.callback.IFailure;
import me.henry.canutecore.net.callback.IRequest;
import me.henry.canutecore.net.callback.ISuccess;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/8/16.
 */

public class RestClientBuilder {

    private static final Map<String, Object> PARAMS=RestCreator.getParams();
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;



    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 强制放weakhashmap
     * @param params
     * @return
     */
    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
      PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }
public final RestClient build(){
    return new RestClient(mUrl,PARAMS,mIError,mIRequest,mISuccess,mIFailure,mBody);
}


}
