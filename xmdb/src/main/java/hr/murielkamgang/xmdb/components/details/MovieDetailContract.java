package hr.murielkamgang.xmdb.components.details;

import hr.murielkamgang.xmdb.components.details.base.BaseMovieDetailsContract;

/**
 * Created by muriel on 3/10/18.
 */
interface MovieDetailContract {

    interface View extends BaseMovieDetailsContract.View {

    }

    interface Presenter extends BaseMovieDetailsContract.Presenter<View> {

    }
}
