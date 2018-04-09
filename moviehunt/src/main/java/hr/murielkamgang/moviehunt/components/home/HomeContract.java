package hr.murielkamgang.moviehunt.components.home;

import hr.murielkamgang.moviehunt.components.base.BaseContentListContract;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;

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

        void delegateFavorite();

    }
}
