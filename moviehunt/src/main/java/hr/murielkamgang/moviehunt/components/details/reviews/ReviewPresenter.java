package hr.murielkamgang.moviehunt.components.details.reviews;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.components.base.BaseContentListPresenter;
import hr.murielkamgang.moviehunt.data.model.review.Review;
import hr.murielkamgang.moviehunt.data.source.Repository;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import io.reactivex.Observable;

/**
 * Created by muriel on 3/25/18.
 */
public class ReviewPresenter extends BaseContentListPresenter<Review, ReviewContract.View> implements ReviewContract.Presenter {

    private final Repository<Review, BaseKVH> reviewRepository;
    private final int movieId;

    @Inject
    public ReviewPresenter(Repository<Review, BaseKVH> reviewRepository, int movieId) {
        this.reviewRepository = reviewRepository;
        this.movieId = movieId;
    }

    @Override
    protected Observable<List<Review>> provideLoadObservable(boolean sync) {
        return reviewRepository.getAllDataAsObservable(new BaseKVH("movieId", movieId), sync);
    }
}
