package hr.murielkamgang.xmdb.components.details;

import hr.murielkamgang.xmdb.components.base.BaseDialogView;
import hr.murielkamgang.xmdb.components.base.BasePresenter;
import hr.murielkamgang.xmdb.data.model.movie.Movie;

/**
 * Created by muriel on 3/10/18.
 */
interface MovieDetailContract {

    interface View extends BaseDialogView {

        void onMovieLoaded(Movie movie);
    }

    interface Presenter extends BasePresenter<View> {

        void loadMovie();
    }
}
