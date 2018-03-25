package hr.murielkamgang.xmdb.components.details.trailers;

import hr.murielkamgang.xmdb.components.base.BaseContentListContract;
import hr.murielkamgang.xmdb.data.model.video.Video;

/**
 * Created by muriel on 3/24/18.
 */
public class TrailerContract {

    public interface View extends BaseContentListContract.View<Video> {

    }

    public interface Presenter extends BaseContentListContract.Presenter<View> {

    }
}
