package hr.murielkamgang.moviehunt.components.details.reviews;

import hr.murielkamgang.moviehunt.components.base.BaseContentListContract;
import hr.murielkamgang.moviehunt.data.model.review.Review;

/**
 * Created by muriel on 3/25/18.
 */
public interface ReviewContract {

    interface View extends BaseContentListContract.View<Review> {

    }

    interface Presenter extends BaseContentListContract.Presenter<View> {

    }
}
