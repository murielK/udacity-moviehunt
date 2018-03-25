package hr.murielkamgang.xmdb.components.home;

import hr.murielkamgang.xmdb.components.base.BaseContentListContract;
import hr.murielkamgang.xmdb.data.model.movie.Movie;

/**
 * Created by muriel on 3/3/18.
 */

interface HomeContract {

    interface View extends BaseContentListContract.View<Movie> {

        void updateToolbarName(int nameResId);

    }

    interface Presenter extends BaseContentListContract.Presenter<View> {

        void delegatePopular();

        void delegateTopRating();

    }
}
