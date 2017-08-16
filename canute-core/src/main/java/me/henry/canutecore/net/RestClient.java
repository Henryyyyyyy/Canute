package me.henry.canutecore.net;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

import me.henry.canutecore.net.callback.IError;
import me.henry.canutecore.net.callback.IFailure;
import me.henry.canutecore.net.callback.IRequest;
import me.henry.canutecore.net.callback.ISuccess;

import me.henry.canutecore.net.callback.RequestCallbacks;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/8/16.
 */

public class RestClient {
    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final IError ERROR;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final RequestBody BODY;

    public RestClient(String url,
                      Map<String, Object> params,
                      IError error,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      RequestBody body) {
        this.URL = url;
        PARAMS.putAll(params);
        this.ERROR = error;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.BODY = body;
    }
    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }
    private void request(HttpMethod method){
        final RestService service=RestCreator.getRestService();
        Call<String> call=null;
        //***
        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }
        switch (method){
            case GET:
                //**
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            default:
                break;
        }
        if (call!=null){
            call.enqueue(getRequestCallback());
        }
    }
    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(ERROR,REQUEST,SUCCESS,FAILURE);
    }
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }

}
