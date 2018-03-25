package hr.murielkamgang.xmdb.components.details.base;

import hr.murielkamgang.xmdb.components.base.BaseDialogView;
import hr.murielkamgang.xmdb.components.base.BasePresenter;
import hr.murielkamgang.xmdb.data.model.movie.Movie;

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
