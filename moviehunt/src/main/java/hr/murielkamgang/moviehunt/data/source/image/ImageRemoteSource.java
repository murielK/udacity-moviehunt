package hr.murielkamgang.moviehunt.data.source.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import hr.murielkamgang.moviehunt.constant.ImageType;
import hr.murielkamgang.moviehunt.data.model.image.Image;
import hr.murielkamgang.moviehunt.data.source.DataSourceException;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import hr.murielkamgang.moviehunt.data.source.base.BaseRemoteDataSource;
import hr.murielkamgang.moviehunt.util.Utils;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by muriel on 3/25/18.
 */
public class ImageRemoteSource extends BaseRemoteDataSource<Image, BaseKVH> {

    private final Logger logger = LoggerFactory.getLogger(ImageRemoteSource.class);
    private final ImageApi imageApi;
    private final String apiKey;

    public ImageRemoteSource(Retrofit retrofit, String apiKey) {
        this.imageApi = retrofit.create(ImageApi.class);
        this.apiKey = apiKey;
    }

    @Override
    public List<Image> getAllData(BaseKVH baseKVH) {
        try {
            final Images images = imageApi.getImageForMovie(baseKVH.getFieldValue(), apiKey).execute().body();
            final List<Image> result = new ArrayList<>();
            result.addAll(getImages(images, baseKVH.getFieldValue(), true));// we only cares about backdrop images for now
            return result;
        } catch (Exception e) {
            logger.debug("", e);
            throw new DataSourceException("get all images for movie: " + baseKVH.getFieldValue(), e);
        }
    }

    @Override
    public Observable<List<Image>> getAllDataAsObservable(BaseKVH baseKVH) {
        return Observable.fromCallable(() -> getAllData(baseKVH));
    }

    private List<Image> getImages(Images images, int movieId, boolean backdrop) {
        final List<Image> result = backdrop ? images.backdrops : images.posters;
        Utils.updateIdFor(result, movieId);
        tune(result, backdrop);
        return result;
    }

    private void tune(List<Image> images, boolean backdrop) {
        if (images == null) {
            return;
        }

        for (final Image image : images) {
            image.setType(backdrop ? ImageType.IMAGE_TYPE_BACKDROP : ImageType.IMAGE_TYPE_POSTER);
        }
    }

    interface ImageApi {

        @GET("movie/{movie_id}/images")
        Call<Images> getImageForMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey);
    }

    static class Images {
        int id;
        List<Image> backdrops;
        List<Image> posters;
    }
}
