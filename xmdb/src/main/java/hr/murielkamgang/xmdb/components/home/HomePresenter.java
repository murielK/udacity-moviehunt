package hr.murielkamgang.xmdb.components.home;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.BaseContentListPresenter;
import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.Repository;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.movie.MovieRepository;
import hr.murielkamgang.xmdb.helper.PreferenceHelper;
import hr.murielkamgang.xmdb.util.Utils;
import io.reactivex.Observable;
import io.realm.RealmResults;

/**
 * Created by muriel on 3/3/18.
 */
class HomePresenter extends BaseContentListPresenter<Movie, HomeContract.View> implements HomeContract.Presenter {

    private final Repository<Movie, BaseKVH> movieRepository;
    private final PreferenceHelper preferenceHelper;
    private RealmResults<Movie> movies;

    @Inject
    HomePresenter(Repository<Movie, BaseKVH> movieRepository, PreferenceHelper preferenceHelper) {
        this.movieRepository = movieRepository;
        this.preferenceHelper = preferenceHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearMovies();
    }

    @Override
    public void delegatePopular() {
        preferenceHelper.setPopularSorting();
        clearMovies();
        internalLoad(true);
    }

    @Override
    public void delegateTopRating() {
        preferenceHelper.setTopRatingSorting();
        clearMovies();
        internalLoad(true);
    }

    @Override
    protected Observable<List<Movie>> provideLoadObservable(boolean sync) {
        final boolean isPopular = preferenceHelper.isPopularSorting();
        view.updateToolbarName(isPopular ? R.string.most_popular : R.string.highest_rated);
        return isPopular ? ((MovieRepository) movieRepository).getPopularAsObservable(sync)
                : ((MovieRepository) movieRepository).getTopRatedAsObservable(sync);
    }

    @Override
    protected void handleOnNewMovies(List<Movie> movies) {
        if (view != null && this.movies == null) {
            this.movies = (RealmResults<Movie>) movies;
            this.movies.addChangeListener((movies1, changeSet) -> Utils.notifyAdapterView(movies1, changeSet, view));
            view.onLoaded(this.movies);
        }
    }

    private void clearMovies() {
        if (movies != null) {
            movies.removeAllChangeListeners();
            movies = null;
        }
    }

}
