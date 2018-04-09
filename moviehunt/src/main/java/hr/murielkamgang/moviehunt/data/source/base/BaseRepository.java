package hr.murielkamgang.moviehunt.data.source.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import hr.murielkamgang.moviehunt.data.source.KeyValueHolder;
import hr.murielkamgang.moviehunt.data.source.Repository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmObject;

/**
 * Created by muriel on 24/2/18.
 */

public abstract class BaseRepository<T extends RealmObject, K extends KeyValueHolder> implements Repository<T, K> {

    private final Logger logger = LoggerFactory.getLogger(BaseRepository.class);

    @Override
    public Observable<Boolean> saveDataAsObservable(T t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<Boolean> saveDataAsObservable(List<T> t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<T> createDataAsObservable(T t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<T> getDataAsObservable(K k) {
        return getDataAsObservable(k, false);
    }

    @Override
    public Observable<List<T>> getAllDataAsObservable() {
        return getAllDataAsObservable(false);
    }

    @Override
    public Observable<Boolean> deleteAsObservable(K k) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<Boolean> deleteAllAsObservable() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<T> getDataAsObservable(K k, boolean sync) {
        return sync ? getAndSaveAsObservable(k) : getLocalDataSource().getDataAsObservable(k)
                .onErrorResumeNext(throwable -> {
                    logger.debug("", throwable);
                    return Observable.empty();
                })
                .concatWith(getAndSaveAsObservable(k));
    }

    @Override
    public Observable<List<T>> getAllDataAsObservable(boolean sync) {
        return sync ? getAllAndSaveAsObservable(true) : getLocalDataSource().getAllDataAsObservable()
                .onErrorResumeNext(throwable -> {
                    logger.debug("", throwable);
                    return Observable.empty();
                })
                .filter(ts -> !ts.isEmpty())
                .concatWith(getAllAndSaveAsObservable(false));
    }

    @Override
    public abstract BaseLocalDataSource<T, K> getLocalDataSource();

    @Override
    public abstract BaseRemoteDataSource<T, K> getRemoteDataSource();

    private Observable<T> getAndSaveAsObservable(final K k) {
        return getRemoteDataSource().getDataAsObservable(k)
                .subscribeOn(Schedulers.io())
                .doOnNext(t -> getLocalDataSource().saveData(t))
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(t -> getLocalDataSource().getDataAsObservable(k));
    }

    protected Observable<List<T>> getAllAndSaveAsObservable(final boolean sync) {
        return getRemoteDataSource().getAllDataAsObservable()
                .subscribeOn(Schedulers.io())
                .doOnNext(ts -> handleOnNext(ts, sync))
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(newsList -> getLocalDataSource().getAllDataAsObservable());
    }

    protected Observable<List<T>> getAllAndSaveAsObservable(K k, final boolean sync) {
        return getRemoteDataSource().getAllDataAsObservable(k)
                .subscribeOn(Schedulers.io())
                .doOnNext(ts -> handleOnNext(ts, sync))
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(newsList -> getLocalDataSource().getAllDataAsObservable(k));
    }

    protected void handleOnNext(List<T> ts, boolean sync) {
        final BaseLocalDataSource<T, K> localDataSource = getLocalDataSource();
        if (sync) {
            localDataSource.cleanSave(ts);
        } else {
            localDataSource.saveData(ts);
        }
    }

    @Override
    public Observable<List<T>> getAllDataAsObservable(K k) {
        return getAllAndSaveAsObservable(k, false);
    }

    @Override
    public Observable<List<T>> getAllDataAsObservable(K k, boolean sync) {
        return sync ? getAllAndSaveAsObservable(k, true) : getLocalDataSource().getAllDataAsObservable(k)
                .onErrorResumeNext(throwable -> {
                    logger.debug("", throwable);
                    return Observable.empty();
                })
                .filter(ts -> !ts.isEmpty())
                .concatWith(getAllAndSaveAsObservable(k, false));
    }
}
