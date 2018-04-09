package hr.murielkamgang.moviehunt.components.details.info;

import hr.murielkamgang.moviehunt.components.details.base.BaseMovieDetailsContract;
import hr.murielkamgang.moviehunt.data.model.credits.Credits;

/**
 * Created by muriel on 3/24/18.
 */
public interface MovieInfoContract {

    interface View extends BaseMovieDetailsContract.View {

        void onCreditLoaded(Credits credits);

    }

    interface Presenter extends BaseMovieDetailsContract.Presenter<View> {

    }

}
