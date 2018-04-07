package hr.murielkamgang.xmdb.data.source.review;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.data.model.review.Review;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.base.BaseLocalDataSource;
import hr.murielkamgang.xmdb.data.source.base.BaseRemoteDataSource;
import hr.murielkamgang.xmdb.data.source.base.BaseRepository;

/**
 * Created by muriel on 3/25/18.
 */
public class ReviewRepository extends BaseRepository<Review, BaseKVH> {

    private final ReviewLocalSource reviewLocalSource;
    private final ReviewRemoteSource reviewRemoteSource;

    @Inject
    public ReviewRepository(ReviewLocalSource reviewLocalSource, ReviewRemoteSource reviewRemoteSource) {
        this.reviewLocalSource = reviewLocalSource;
        this.reviewRemoteSource = reviewRemoteSource;
    }

    @Override
    public BaseLocalDataSource<Review, BaseKVH> getLocalDataSource() {
        return reviewLocalSource;
    }

    @Override
    public BaseRemoteDataSource<Review, BaseKVH> getRemoteDataSource() {
        return reviewRemoteSource;
    }
}
