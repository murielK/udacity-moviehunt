package hr.murielkamgang.xmdb.components.details.reviews;

import hr.murielkamgang.xmdb.components.base.BaseContentListContract;
import hr.murielkamgang.xmdb.data.model.review.Review;

/**
 * Created by muriel on 3/25/18.
 */
public interface ReviewContract {

    interface View extends BaseContentListContract.View<Review> {

    }

    interface Presenter extends BaseContentListContract.Presenter<View> {

    }
}
