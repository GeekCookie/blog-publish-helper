package cn.ocookie;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author boris
 */
public class BlogApi {

    private static final String BASE_URL = "http://ocookie.cn/";

    private static ApiService sService;

    static {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String url = chain.request().url().encodedPath();
                Request.Builder request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");

                if (!url.contains("/ghost/api/v0.1/authentication/token") &&
                        BlogHelper.token != null && BlogHelper.token.access_token != null &&
                        !BlogHelper.token.access_token.trim().equals("")) {
                    request.addHeader("Authorization", BlogHelper.token.token_type + " " + BlogHelper.token.access_token);
                }
                Response response = chain.proceed(request.build());
                return response;
            }
        });
        client.addNetworkInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sService = retrofit.create(ApiService.class);
    }


    public static ApiService getService() {
        return sService;
    }

}
