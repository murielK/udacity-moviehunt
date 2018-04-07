package hr.murielkamgang.xmdb.data.source.review;

import com.google.gson.annotations.SerializedName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import hr.murielkamgang.xmdb.data.model.review.Review;
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
 * Created by muriel on 3/25/18.
 */
public class ReviewRemoteSource extends BaseRemoteDataSource<Review, BaseKVH> {

    private final Logger logger = LoggerFactory.getLogger(ReviewRemoteSource.class);
    private final ReviewApi reviewApi;
    private final String apiKey;

    public ReviewRemoteSource(Retrofit retrofit, String apiKey) {
        this.reviewApi = retrofit.create(ReviewApi.class);
        this.apiKey = apiKey;
    }

    @Override
    public List<Review> getAllData(BaseKVH baseKVH) {
        try {
            final List<Review> results = reviewApi.getReviewsForMovies(baseKVH.getFieldValue(), apiKey).execute().body().results;
            Utils.updateIdFor(results, baseKVH.getFieldValue());
            return results;
        } catch (Exception e) {
            logger.debug("", e);

            throw new DataSourceException("get reviews for movieId: " + baseKVH.getFieldValue(), e);
        }
    }

    @Override
    public Observable<List<Review>> getAllDataAsObservable(BaseKVH baseKVH) {
        return Observable.fromCallable(() -> getAllData(baseKVH));
    }

    interface ReviewApi {

        @GET("movie/{movie_id}/reviews")
        Call<ReviewPage> getReviewsForMovies(@Path("movie_id") int movieId, @Query("api_key") String apiKey);
    }

    static class ReviewPage {
        int id;
        int page;
        @SerializedName("total_pages")
        int totalPage;
        @SerializedName("total_results")
        int totalResults;
        List<Review> results;
    }
}
