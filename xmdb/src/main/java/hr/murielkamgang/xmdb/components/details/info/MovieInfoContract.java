package hr.murielkamgang.xmdb.components.details.info;

import hr.murielkamgang.xmdb.components.details.BaseMovieDetailsContract;
import hr.murielkamgang.xmdb.data.model.credits.Credits;

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
