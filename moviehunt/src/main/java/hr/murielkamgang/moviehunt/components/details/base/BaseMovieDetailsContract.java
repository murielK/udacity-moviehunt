package hr.murielkamgang.moviehunt.components.details.base;

import hr.murielkamgang.moviehunt.components.base.BaseDialogView;
import hr.murielkamgang.moviehunt.components.base.BasePresenter;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;

/**
 * Created by muriel on 3/24/18.
 */
public interface BaseMovieDetailsContract {

    interface View extends BaseDialogView {

        void onMovieLoaded(Movie movie);
    }

    interface Presenter<V extends BaseMovieDetailsContract.View> extends BasePresenter<V> {

        void load();
    }
}
