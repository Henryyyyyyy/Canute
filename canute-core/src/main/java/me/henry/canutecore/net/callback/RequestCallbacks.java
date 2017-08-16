package me.henry.canutecore.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/8/17.
 */

public class RequestCallbacks implements Callback<String> {
    private final IError ERROR;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;

    public RequestCallbacks(IError error, IRequest request, ISuccess success, IFailure failure) {
        this.ERROR = error;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
    }
}
