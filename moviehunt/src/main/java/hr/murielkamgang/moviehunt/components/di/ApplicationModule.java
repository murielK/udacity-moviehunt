package hr.murielkamgang.moviehunt.components.di;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import hr.murielkamgang.moviehunt.BuildConfig;
import hr.murielkamgang.moviehunt.R;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by muriel on 9/20/17.
 */

@Module
abstract class ApplicationModule {

    private final static boolean ENABLE_DEBUG = false;
    private static final int CACHE_SIZE = 1024 * 1024 * 12; //12MB
    private static final Logger logger = LoggerFactory.getLogger(ApplicationModule.class);

    @Singleton
    @Provides
    static Picasso providePicasso(Context context) {
        return new Picasso.Builder(context)
                .loggingEnabled(ENABLE_DEBUG)
                .listener((picasso, uri, exception) -> {
                    if (BuildConfig.DEBUG && ENABLE_DEBUG) {
                        LoggerFactory.getLogger(ApplicationModule.class).debug("Picasso Error with uri: {} {}", uri, exception);
                    }
                })
                .build();
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(Context context) {
        final File cacheFile = new File(context.getCacheDir(), "okHttpClient");
        logger.debug("cacheFile: {}", cacheFile);

        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(logger::debug);

        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.HEADERS);
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .cache(new Cache(cacheFile, CACHE_SIZE))
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(Context context, OkHttpClient okHttpClient) {
        final String baseUrl = context.getResources().getString(R.string.base_url);
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Binds
    abstract Context bindContext(Application application);

}
