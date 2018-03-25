package hr.murielkamgang.xmdb.components.base;

import java.util.List;

/**
 * Created by muriel on 3/24/18.
 */
public class BaseContentListContract {

    public interface View<T> extends AdapterView {

        void onLoaded(List<T> ts);

        void swipeToRefresh(boolean show);
    }

    public interface Presenter<V extends BaseContentListContract.View> extends BasePresenter<V> {

        void load();
    }
}
