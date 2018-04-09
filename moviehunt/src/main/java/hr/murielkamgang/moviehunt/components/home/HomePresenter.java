package hr.murielkamgang.moviehunt.components.home;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.BaseContentListPresenter;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.data.source.Repository;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import hr.murielkamgang.moviehunt.data.source.movie.MovieRepository;
import hr.murielkamgang.moviehunt.helper.PreferenceHelper;
import hr.murielkamgang.moviehunt.util.Utils;
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
        internalLoad(false);
    }

    @Override
    public void delegateTopRating() {
        preferenceHelper.setTopRatingSorting();
        clearMovies();
        internalLoad(false);
    }

    @Override
    public void delegateFavorite() {
        clearMovies();
        view.updateToolbarName(R.string.favorite);
        internalLoad(((MovieRepository) movieRepository).getFavoriteAsObservable(), false);
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
