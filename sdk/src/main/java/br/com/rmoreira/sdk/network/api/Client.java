package br.com.rmoreira.sdk.network.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import br.com.rmoreira.sdk.BuildConfig;
import br.com.rmoreira.sdk.SdkApplication;
import br.com.rmoreira.sdk.helpers.SdkHelpers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robsonmoreira on 03/12/17.
 */

class Client {

    private static Retrofit mClientUserData = null;
    private static Retrofit mClientAddress = null;

    static Retrofit getClientUserData() {
        Cache cache = new Cache(SdkApplication.getFileCacheDir(), 10 * 1024 * 1024);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();
                        if (SdkHelpers.isNetworkAvailable(SdkApplication.getContext())) {
                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                        } else {
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                        }
                        return chain.proceed(request);
                    }
                })
                .build();

        if (mClientUserData == null) {
            mClientUserData = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return mClientUserData;
    }

    static Retrofit getClientAddress() {
        Cache cache = new Cache(SdkApplication.getFileCacheDir(), 10 * 1024 * 1024);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();
                        if (SdkHelpers.isNetworkAvailable(SdkApplication.getContext())) {
                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                        } else {
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                        }
                        return chain.proceed(request);
                    }
                })
                .build();

        if (mClientAddress == null) {
            mClientAddress = new Retrofit.Builder()
                    .baseUrl(BuildConfig.MAPS_GEOCODE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return mClientAddress;
    }

}
