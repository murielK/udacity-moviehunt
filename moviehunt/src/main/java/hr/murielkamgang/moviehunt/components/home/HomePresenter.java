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
        preferenceHelper.setFavoriteSorting();
        clearMovies();
        internalLoad(false);
    }

    @Override
    protected Observable<List<Movie>> provideLoadObservable(boolean sync) {
        switch (preferenceHelper.getSortingType()) {
            case PreferenceHelper.PREF_FAVORITE:
                view.updateToolbarName(R.string.favorite);
                return ((MovieRepository) movieRepository).getFavoriteAsObservable();
            case PreferenceHelper.PREF_TOP_RATING:
                view.updateToolbarName(R.string.highest_rated);
                return ((MovieRepository) movieRepository).getTopRatedAsObservable(sync);
            case PreferenceHelper.PREF_MOST_POPULAR:
                view.updateToolbarName(R.string.most_popular);
                return ((MovieRepository) movieRepository).getPopularAsObservable(sync);
            default:
                return Observable.empty();
        }
    }

    @Override
    protected void handleOnNewMovies(List<Movie> movies) {
        if (view != null) {
            if (movies instanceof RealmResults && ((RealmResults) movies).isManaged() && this.movies == null) {
                this.movies = (RealmResults<Movie>) movies;
                this.movies.addChangeListener((movies1, changeSet) -> Utils.notifyAdapterView(movies1, changeSet, view));
                view.onLoaded(this.movies);
            } else {
                clearMovies();
                super.handleOnNewMovies(movies);
            }
        }
    }

    private void clearMovies() {
        if (movies != null) {
            movies.removeAllChangeListeners();
            movies = null;
        }
    }

}
