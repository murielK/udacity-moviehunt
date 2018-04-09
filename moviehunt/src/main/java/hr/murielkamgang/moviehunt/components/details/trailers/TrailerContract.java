package hr.murielkamgang.moviehunt.components.details.trailers;

import hr.murielkamgang.moviehunt.components.base.BaseContentListContract;
import hr.murielkamgang.moviehunt.data.model.video.Video;

/**
 * Created by muriel on 3/24/18.
 */
public class TrailerContract {

    public interface View extends BaseContentListContract.View<Video> {

    }

    public interface Presenter extends BaseContentListContract.Presenter<View> {

    }
}
