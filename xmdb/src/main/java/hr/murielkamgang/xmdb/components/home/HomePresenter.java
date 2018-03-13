package hr.murielkamgang.xmdb.components.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.Repository;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.movie.MovieRepository;
import hr.murielkamgang.xmdb.helper.PreferenceHelper;
import hr.murielkamgang.xmdb.util.Utils;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.realm.RealmResults;

/**
 * Created by muriel on 3/3/18.
 */
class HomePresenter implements HomeContract.Presenter {

    private final Logger logger = LoggerFactory.getLogger(HomePresenter.class);
    private final Repository<Movie, BaseKVH> movieRepository;
    private final PreferenceHelper preferenceHelper;
    private RealmResults<Movie> movies;
    private HomeContract.View view;
    private Disposable disposable;

    @Inject
    HomePresenter(Repository<Movie, BaseKVH> movieRepository, PreferenceHelper preferenceHelper) {
        this.movieRepository = movieRepository;
        this.preferenceHelper = preferenceHelper;
    }

    @Override
    public void onDestroy() {
        this.view = null;
        dispose();
        removeListenersOnRealmResults();
    }

    @Override
    public void setView(HomeContract.View view) {
        this.view = view;
        internalLoad(false);
    }

    @Override
    public void load() {
        internalLoad(true);
    }

    @Override
    public void delegatePopular() {
        preferenceHelper.setPopularSorting();
        internalLoad(true);
    }

    @Override
    public void delegateTopRating() {
        preferenceHelper.setTopRatingSorting();
        internalLoad(true);
    }

    private void internalLoad(boolean sync) {
        final boolean isPopular = preferenceHelper.isPopularSorting();
        view.updateToolbarName(isPopular ? R.string.most_popular : R.string.highest_rated);
        internalLoad(isPopular ?
                        ((MovieRepository) movieRepository).getPopularAsObservable(sync) : ((MovieRepository) movieRepository).getTopRatedAsObservable(sync)
                , sync);
    }

    private void internalLoad(Observable<List<Movie>> observable, boolean sync) {
        observable
                .doOnSubscribe(disposable -> handleOnSubscribed(sync, disposable))
                .subscribe(this::handleOnNewMovies, this::handleOnError, this::onComplete);
    }

    private void handleOnNewMovies(List<Movie> movies) {
        if (view != null && this.movies == null) {
            this.movies = (RealmResults<Movie>) movies;
            this.movies.addChangeListener((movies1, changeSet) -> Utils.notifyAdapterView(movies1, changeSet, view));
            view.onItemLoaded(this.movies);
        }
    }

    private void handleOnError(Throwable throwable) {
        if (view != null) {
            view.toast(R.string.error_msg_something_went_wrong);
            view.swipeToRefresh(false);
        }
        logger.debug("", throwable);
    }

    private void onComplete() {
        if (view != null) {
            view.swipeToRefresh(false);
        }
    }

    private void handleOnSubscribed(boolean sync, Disposable disposable) {
        dispose();
        if (view != null) {
            if (!sync || Utils.isConnected(view.getContext())) {
                HomePresenter.this.disposable = disposable;
                view.swipeToRefresh(true);
                return;
            } else {
                view.toast(R.string.error_msg_not_connected);
            }
        }
        disposable.dispose();
    }

    private void dispose() {
        if (disposable != null) {
            disposable.dispose();
        }

        disposable = null;
    }

    private void removeListenersOnRealmResults() {
        if (movies != null) {
            movies.removeAllChangeListeners();
        }
    }
}
