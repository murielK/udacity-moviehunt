package hr.murielkamgang.xmdb.components.home;

import java.util.List;

import hr.murielkamgang.xmdb.components.base.AdapterView;
import hr.murielkamgang.xmdb.components.base.BasePresenter;
import hr.murielkamgang.xmdb.data.model.movie.Movie;

/**
 * Created by muriel on 3/3/18.
 */

interface HomeContract {

    interface View extends AdapterView {

        void onItemLoaded(List<Movie> movies);

        void swipeToRefresh(boolean refreshing);

        void updateToolbarName(int nameResId);

    }

    interface Presenter extends BasePresenter<View> {

        void load();

        void delegatePopular();

        void delegateTopRating();

    }
}
