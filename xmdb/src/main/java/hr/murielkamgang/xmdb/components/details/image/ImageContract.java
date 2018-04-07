package hr.murielkamgang.xmdb.components.details.image;

import hr.murielkamgang.xmdb.components.base.BaseContentListContract;
import hr.murielkamgang.xmdb.data.model.image.Image;

/**
 * Created by muriel on 3/25/18.
 */
public interface ImageContract {

    interface View extends BaseContentListContract.View<Image> {

    }

    interface Presenter extends BaseContentListContract.Presenter<View> {

    }
}
