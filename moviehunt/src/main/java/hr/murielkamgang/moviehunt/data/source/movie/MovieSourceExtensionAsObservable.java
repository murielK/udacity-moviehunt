package hr.murielkamgang.moviehunt.data.source.movie;

import java.util.List;

import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import io.reactivex.Observable;

/**
 * Created by muriel on 3/10/18.
 */
public interface MovieSourceExtensionAsObservable {

    Observable<List<Movie>> getPopularAsObservable();

    Observable<List<Movie>> getTopRatedAsObservable();

    Observable<List<Movie>> getFavoriteAsObservable();

    Observable<Boolean> addMovieToFavoriteAsObservable(BaseKVH baseKVH, boolean favorite);
}
