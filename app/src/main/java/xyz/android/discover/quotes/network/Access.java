package xyz.android.discover.quotes.network;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import xyz.android.discover.quotes.network.api.QuotesApiService;

/**
 * Created by Govarthanan on 10/9/2017.
 */

public class Access {
    private static final int TIMEOUT = 60;
    private static Access mInstance;
    private final OkHttpClient mOkHttpClient;
    private JacksonConverterFactory mConverterFactory;
    private QuotesApiService mquotesApiService;

    private Access() {
        //Set Logging Interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Set okhttpclient and it's properties
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        //Setup JacksonConverterFactory properties
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mConverterFactory = JacksonConverterFactory.create(objectMapper);
    }

    public static synchronized Access getInstance() {
        if (mInstance == null) {
            mInstance = new Access();

        }
        return mInstance;
    }

    public QuotesApiService getQuotesApi(String apiUrl) {
        if (mquotesApiService != null) {
            return mquotesApiService;
        }
        if (apiUrl != null) {
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder.baseUrl(apiUrl);
            retrofitBuilder.addConverterFactory(mConverterFactory);
            retrofitBuilder.client(mOkHttpClient);
            Retrofit retrofit = retrofitBuilder.build();
            mquotesApiService = retrofit.create(QuotesApiService.class);
        }
        return mquotesApiService;
    }
}
