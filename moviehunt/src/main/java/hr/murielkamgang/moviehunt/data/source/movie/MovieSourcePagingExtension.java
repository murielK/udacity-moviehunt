package hr.murielkamgang.moviehunt.data.source.movie;

import java.util.List;

import hr.murielkamgang.moviehunt.data.model.movie.Movie;

/**
 * Created by muriel on 3/10/18.
 */
public interface MovieSourcePagingExtension {

    List<Movie> getPopular(int page);

    List<Movie> getTopRated(int page);
}
