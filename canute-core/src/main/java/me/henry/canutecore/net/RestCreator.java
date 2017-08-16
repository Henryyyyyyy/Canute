package me.henry.canutecore.net;



import android.util.Log;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import me.henry.canutecore.app.Canute;
import me.henry.canutecore.app.ConfigType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/8/16.
 * 从上到下的一个创建依赖关系，最后只需要用restservice静态类就好了
 */

public class RestCreator {
    private static final class ParamsHolder{

        public static final WeakHashMap<String,Object> PARAMS=new WeakHashMap<>();
    }
    public static WeakHashMap<String,Object>getParams(){
        return ParamsHolder.PARAMS;
    }
    public static RestService getRestService(){
        return RestServiceHoler.REST_SERVICE;

    }
    private  static final class RetrofitHolder{
        private static  final String BASE_URL= (String) Canute.getConfigurations().get(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }
    private static final class OKHttpHolder{
        private static final int TIME_OUT=60;
        private static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    private static final class RestServiceHoler{
         private static final RestService REST_SERVICE=
                 RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
