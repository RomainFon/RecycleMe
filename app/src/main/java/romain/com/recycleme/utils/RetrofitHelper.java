package romain.com.recycleme.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final int MIN_TIMEOUT = 20_000;
    private static final String SERVER_URL = "http://www.recipepuppy.com";

    private static String getBaseUrl() {
        return SERVER_URL;
    }

    public static Retrofit buildRetrofit() {
        return buildRetrofit(getBaseUrl(), MIN_TIMEOUT);
    }
    public static Retrofit buildRetrofit(String baseUrl, long timeOut) {
        OkHttpClient.Builder httpClient = new OkHttpClient
                .Builder()
                .readTimeout(timeOut < MIN_TIMEOUT ? MIN_TIMEOUT : timeOut, TimeUnit.MILLISECONDS)
                .connectTimeout(timeOut < MIN_TIMEOUT ? MIN_TIMEOUT : timeOut, TimeUnit.MILLISECONDS);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}