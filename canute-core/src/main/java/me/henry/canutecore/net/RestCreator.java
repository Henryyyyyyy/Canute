package me.henry.canutecore.net;


import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import me.henry.canutecore.app.Canute;
import me.henry.canutecore.app.ConfigKey;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/8/16.
 * 构建rest所需要创建的东西
 * 利用holder直接拿对应类里面已经创建好的静态东西
 */

public class RestCreator {
    private static final class ParamsHolder{
        public static final WeakHashMap<String,Object> PARAMS=new WeakHashMap<>();
    }

    /**
     * 配置OKHttp
     */
    private static final class OKHttpHolder{
        private static final int TIME_OUT=60;
        private static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 配置retrofit
     */
    private  static final class RetrofitHolder{
        private static final String BASE_URL = Canute.getConfiguration(ConfigKey.API_HOST);

        private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }
    /**
     * 生成对应的service
     */
    private static final class RestServiceHoler{
         private static final RestService REST_SERVICE= RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
    public static WeakHashMap<String,Object>getParams(){
        return ParamsHolder.PARAMS;
    }
    public static RestService getRestService(){
        return RestServiceHoler.REST_SERVICE;

    }
}
