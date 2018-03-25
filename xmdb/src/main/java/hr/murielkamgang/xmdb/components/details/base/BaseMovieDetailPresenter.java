package hr.murielkamgang.xmdb.components.details.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.Repository;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import io.reactivex.disposables.Disposable;

/**
 * Created by muriel on 3/24/18.
 */
public class BaseMovieDetailPresenter<V extends BaseMovieDetailsContract.View> implements BaseMovieDetailsContract.Presenter<V> {

    protected final Logger logger = LoggerFactory.getLogger(BaseMovieDetailPresenter.class);
    protected final int movieId;
    protected final Repository<Movie, BaseKVH> movieRepository;
    protected V view;
    protected Disposable loadMovieDisposable;

    @Inject
    public BaseMovieDetailPresenter(Repository<Movie, BaseKVH> movieRepository, int movieId) {
        this.movieRepository = movieRepository;
        this.movieId = movieId;
    }

    @Override
    public void onDestroy() {
        view = null;
        if (loadMovieDisposable != null) {
            loadMovieDisposable = null;
        }
    }

    @Override
    public void setView(V view) {
        this.view = view;
    }

    @Override
    public void load() {
        loadMovie();
    }

    private void loadMovie() {
        movieRepository
                .getDataAsObservable(new BaseKVH("id", movieId))
                .doOnSubscribe(disposable -> {
                    if (BaseMovieDetailPresenter.this.loadMovieDisposable != null) {
                        BaseMovieDetailPresenter.this.loadMovieDisposable.dispose();
                    }

                    BaseMovieDetailPresenter.this.loadMovieDisposable = disposable;
                })
                .subscribe(movie -> {
                    logger.debug("loaded movie: {}", movie);
                    if (view != null) {
                        view.onMovieLoaded(movie);
                    }
                }, throwable -> logger.debug("", throwable));
    }
}
