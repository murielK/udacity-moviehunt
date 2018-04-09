package hr.murielkamgang.moviehunt.components.details.trailers;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.components.base.BaseContentListPresenter;
import hr.murielkamgang.moviehunt.data.model.video.Video;
import hr.murielkamgang.moviehunt.data.source.Repository;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import io.reactivex.Observable;

/**
 * Created by muriel on 3/24/18.
 */
public class TrailerPresenter extends BaseContentListPresenter<Video, TrailerContract.View> implements TrailerContract.Presenter {

    private final Repository<Video, BaseKVH> videoRepository;
    private final int movieId;

    @Inject
    public TrailerPresenter(Repository<Video, BaseKVH> videoRepository, int movieId) {
        this.videoRepository = videoRepository;
        this.movieId = movieId;
    }

    @Override
    protected Observable<List<Video>> provideLoadObservable(boolean sync) {
        return videoRepository.getAllDataAsObservable(new BaseKVH("movieId", movieId), sync);
    }
}
