package hr.murielkamgang.xmdb.components.details.info;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.components.details.BaseMovieDetailPresenter;
import hr.murielkamgang.xmdb.data.model.credits.Credits;
import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.Repository;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import io.reactivex.disposables.Disposable;

/**
 * Created by muriel on 3/24/18.
 */
public class MovieInfoPresenter extends BaseMovieDetailPresenter<MovieInfoContract.View> implements MovieInfoContract.Presenter {

    private final Repository<Credits, BaseKVH> creditsRepository;
    private Disposable creditsDisposable;

    @Inject
    MovieInfoPresenter(Repository<Movie, BaseKVH> movieRepository, Repository<Credits, BaseKVH> creditsRepository, int movieId) {
        super(movieRepository, movieId);
        this.creditsRepository = creditsRepository;
    }

    @Override
    public void load() {
        super.load();
        loadCredits();
    }

    private void loadCredits() {
        creditsRepository
                .getDataAsObservable(new BaseKVH("id", movieId))
                .doOnSubscribe(disposable -> {
                    if (MovieInfoPresenter.this.creditsDisposable != null) {
                        MovieInfoPresenter.this.creditsDisposable.dispose();
                    }

                    MovieInfoPresenter.this.creditsDisposable = disposable;
                })
                .subscribe(credits -> {
                    logger.debug("loaded credits: {}", credits);
                    if (view != null) {
                        view.onCreditLoaded(credits);
                    }
                }, throwable -> logger.debug("", throwable));
    }
}
