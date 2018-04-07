package hr.murielkamgang.xmdb.data.source.video;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import hr.murielkamgang.xmdb.data.model.video.Video;
import hr.murielkamgang.xmdb.data.source.DataSourceException;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.base.BaseRemoteDataSource;
import hr.murielkamgang.xmdb.util.Utils;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by muriel on 3/24/18.
 */
public class VideoRemoteSource extends BaseRemoteDataSource<Video, BaseKVH> {

    private final Logger logger = LoggerFactory.getLogger(VideoRemoteSource.class);
    private final String apiKey;
    private final VideoApi videoApi;

    public VideoRemoteSource(Retrofit retrofit, String apiKey) {
        this.apiKey = apiKey;
        this.videoApi = retrofit.create(VideoApi.class);
    }

    @Override
    public List<Video> getAllData(BaseKVH baseKVH) {
        try {
            final List<Video> results = videoApi.getVideoForMovie(baseKVH.getFieldValue(), apiKey).execute().body().results;
            Utils.updateIdFor(results, baseKVH.getFieldValue());
            return results;
        } catch (IOException e) {
            logger.debug("", e);

            throw new DataSourceException("getAllData videos for movieId: " + baseKVH.getFieldValue(), e);
        }
    }

    @Override
    public Observable<List<Video>> getAllDataAsObservable(BaseKVH baseKVH) {
        return Observable.fromCallable(() -> getAllData(baseKVH));
    }

    interface VideoApi {

        @GET("movie/{movie_id}/videos")
        Call<Videos> getVideoForMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey);
    }

    static class Videos {
        int id;
        List<Video> results;
    }
}
