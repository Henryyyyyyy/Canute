package me.henry.canutecore.net.callback;

import android.os.Handler;

import me.henry.canutecore.ui.loader.CanuteLoader;
import me.henry.canutecore.ui.loader.LoaderStyle;
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
    private final LoaderStyle LOADER_STYLE;
    //声明成static避免内存泄漏
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IError error, IRequest request, ISuccess success, IFailure failure, LoaderStyle style) {
        this.ERROR = error;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.LOADER_STYLE = style;
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
     stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }
    private void stopLoading(){
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CanuteLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
