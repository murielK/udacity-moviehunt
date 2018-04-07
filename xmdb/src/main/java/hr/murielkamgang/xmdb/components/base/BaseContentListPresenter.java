package hr.murielkamgang.xmdb.components.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.util.Utils;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by muriel on 3/24/18.
 */
public abstract class BaseContentListPresenter<T, V extends BaseContentListContract.View<T>> implements BaseContentListContract.Presenter<V> {

    private final Logger logger = LoggerFactory.getLogger(BaseContentListPresenter.class);
    protected V view;
    private Disposable disposable;

    protected abstract Observable<List<T>> provideLoadObservable(boolean sync);

    @Override
    public void onDestroy() {
        view = null;
        dispose();
    }

    @Override
    public void setView(V v) {
        this.view = v;
        internalLoad(false);
    }

    @Override
    public void load() {
        internalLoad(false);

    }

    protected void internalLoad(boolean sync) {
        internalLoad(provideLoadObservable(sync), sync);
    }

    protected void internalLoad(Observable<List<T>> observable, boolean sync) {
        observable
                .doOnSubscribe(disposable -> handleOnSubscribed(sync, disposable))
                .subscribe(this::handleOnNewMovies, this::handleOnError, this::onComplete);
    }

    protected void handleOnNewMovies(List<T> ts) {
        if (view != null) {
            view.onLoaded(ts);
        }
    }

    protected void handleOnError(Throwable throwable) {
        if (view != null) {
            view.toast(R.string.error_msg_something_went_wrong);
            view.swipeToRefresh(false);
        }
        logger.debug("", throwable);
    }

    protected void onComplete() {
        if (view != null) {
            view.swipeToRefresh(false);
        }
    }

    protected void handleOnSubscribed(boolean sync, Disposable disposable) {
        dispose();
        if (view != null) {
            if (!sync || Utils.isConnected(view.getContext())) {
                BaseContentListPresenter.this.disposable = disposable;
                view.swipeToRefresh(true);
                return;
            } else {
                view.toast(R.string.error_msg_not_connected);
            }
        }
        disposable.dispose();
    }

    private void dispose() {
        if (disposable != null) {
            disposable.dispose();
        }
        disposable = null;
    }

}
