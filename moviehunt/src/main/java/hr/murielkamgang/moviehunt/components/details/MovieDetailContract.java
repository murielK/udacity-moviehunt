package hr.murielkamgang.moviehunt.components.details;

import hr.murielkamgang.moviehunt.components.details.base.BaseMovieDetailsContract;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;

/**
 * Created by muriel on 3/10/18.
 */
interface MovieDetailContract {

    interface View extends BaseMovieDetailsContract.View {

    }

    interface Presenter extends BaseMovieDetailsContract.Presenter<View> {

        void toggledFavorite(Movie movie);

        boolean isFavorite(Movie movie);
    }
}
