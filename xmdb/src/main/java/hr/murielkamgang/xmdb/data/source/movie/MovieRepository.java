package hr.murielkamgang.xmdb.data.source.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.base.BaseLocalDataSource;
import hr.murielkamgang.xmdb.data.source.base.BaseRemoteDataSource;
import hr.murielkamgang.xmdb.data.source.base.BaseRepository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by muriel on 3/4/18.
 */

public class MovieRepository extends BaseRepository<Movie, BaseKVH> implements MovieSourceExtensionAsObservable, MovieSourcePagingExtensionAsObservable {

    private final Logger logger = LoggerFactory.getLogger(MovieRepository.class);
    private final MovieLocalSource movieLocalSource;
    private final MovieRemoteSource movieRemoteSource;

    @Inject
    public MovieRepository(MovieLocalSource movieLocalSource, MovieRemoteSource movieRemoteSource) {
        this.movieLocalSource = movieLocalSource;
        this.movieRemoteSource = movieRemoteSource;
    }

    @Override
    public BaseLocalDataSource<Movie, BaseKVH> getLocalDataSource() {
        return movieLocalSource;
    }

    @Override
    public BaseRemoteDataSource<Movie, BaseKVH> getRemoteDataSource() {
        return movieRemoteSource;
    }


    @Override
    public Observable<List<Movie>> getPopularAsObservable() {
        return getPopularAsObservable(false);
    }

    @Override
    public Observable<List<Movie>> getTopRatedAsObservable() {
        return getTopRatedAsObservable(false);
    }

    @Override
    public Observable<List<Movie>> getPopularAsObservable(int page) {
        return movieRemoteSource.getPopularAsObservable(page)
                .subscribeOn(Schedulers.io())
                .doOnNext(movieLocalSource::saveData)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Movie>> getTopRatedAsObservable(int page) {
        return movieRemoteSource.getTopRatedAsObservable(page)
                .subscribeOn(Schedulers.io())
                .doOnNext(movieLocalSource::saveData)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Movie>> getPopularAsObservable(boolean sync) {// Sync true will clean the database first prior adding new movies
        return sync ? getPopularAndSaveAsObservable(true) : movieLocalSource.getPopularAsObservable()
                .onErrorResumeNext(throwable -> {
                    logger.debug("", throwable);
                    return Observable.empty();
                })
                .filter(ts -> !ts.isEmpty())
                .concatWith(getPopularAndSaveAsObservable(false));
    }

    public Observable<List<Movie>> getTopRatedAsObservable(boolean sync) {// Sync true will clean the database first prior adding new movies
        return sync ? getTopRatedAndSaveAsObservable(true) : movieLocalSource.getTopRatedAsObservable()
                .onErrorResumeNext(throwable -> {
                    logger.debug("", throwable);
                    return Observable.empty();
                })
                .filter(ts -> !ts.isEmpty())
                .concatWith(getTopRatedAndSaveAsObservable(false));
    }

    private Observable<List<Movie>> getTopRatedAndSaveAsObservable(boolean sync) {// this will be used for pagination in future
        return movieRemoteSource.getTopRatedAsObservable()
                .subscribeOn(Schedulers.io())
                .doOnNext(ts -> handleOnNext(ts, sync))
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(newsList -> movieLocalSource.getTopRatedAsObservable());
    }

    private Observable<List<Movie>> getPopularAndSaveAsObservable(boolean sync) {// this will be used for pagination in the future
        return movieRemoteSource.getPopularAsObservable()
                .subscribeOn(Schedulers.io())
                .doOnNext(ts -> handleOnNext(ts, sync))
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(newsList -> movieLocalSource.getPopularAsObservable());
    }
}
