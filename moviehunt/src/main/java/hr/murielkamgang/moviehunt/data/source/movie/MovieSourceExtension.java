package hr.murielkamgang.moviehunt.data.source.movie;

import java.util.List;

import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;

/**
 * Created by muriel on 3/10/18.
 */
public interface MovieSourceExtension {

    List<Movie> getPopular();

    List<Movie> getTopRated();

    List<Movie> getFavorite();

    Boolean addMovieToFavorite(BaseKVH baseKVH, boolean favorite);
}
