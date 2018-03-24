package hr.murielkamgang.xmdb.data.source.credits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import hr.murielkamgang.xmdb.data.model.credits.Credits;
import hr.murielkamgang.xmdb.data.source.DataSourceException;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.base.BaseRemoteDataSource;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by muriel on 3/24/18.
 */
public class CreditsRemoteSource extends BaseRemoteDataSource<Credits, BaseKVH> {

    private final Logger logger = LoggerFactory.getLogger(CreditsRemoteSource.class);
    private final CreditsApi creditsApi;
    private final String apiKey;

    public CreditsRemoteSource(Retrofit retrofit, String apiKey) {
        this.creditsApi = retrofit.create(CreditsApi.class);
        this.apiKey = apiKey;
    }


    @Override
    public Credits getData(BaseKVH baseKVH) {
        try {
            return creditsApi.creditForMovie(baseKVH.getFieldValue(), apiKey).execute().body();
        } catch (IOException e) {
            logger.debug("", e);

            throw new DataSourceException("getData credit for movieId: " + baseKVH.getFieldValue(), e);
        }
    }

    @Override
    public Observable<Credits> getDataAsObservable(BaseKVH baseKVH) {
        return Observable.fromCallable(() -> getData(baseKVH));
    }

    private interface CreditsApi {

        @GET("movie/{movie_id}/credits")
        Call<Credits> creditForMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    }
}
