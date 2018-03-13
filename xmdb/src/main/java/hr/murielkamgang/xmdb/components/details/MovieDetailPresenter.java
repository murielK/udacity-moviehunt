package hr.murielkamgang.xmdb.components.details;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.Repository;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import io.reactivex.disposables.Disposable;

/**
 * Created by muriel on 3/10/18.
 */
class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private final Logger logger = LoggerFactory.getLogger(MovieDetailPresenter.class);
    private final int movieId;
    private final Repository<Movie, BaseKVH> movieRepository;
    private MovieDetailContract.View view;
    private Disposable disposable;

    @Inject
    public MovieDetailPresenter(Repository<Movie, BaseKVH> movieRepository, int movieId) {
        this.movieRepository = movieRepository;
        this.movieId = movieId;
    }

    @Override
    public void onDestroy() {
        view = null;
        if (disposable != null) {
            disposable = null;
        }
    }

    @Override
    public void setView(MovieDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void loadMovie() {
        movieRepository
                .getDataAsObservable(new BaseKVH("id", movieId))
                .doOnSubscribe(disposable -> {
                    if (MovieDetailPresenter.this.disposable != null) {
                        MovieDetailPresenter.this.disposable.dispose();
                    }

                    MovieDetailPresenter.this.disposable = disposable;
                })
                .subscribe(movie -> {
                    if (view != null) {
                        view.onMovieLoaded(movie);
                    }
                }, throwable -> logger.debug("", throwable));
    }
}
