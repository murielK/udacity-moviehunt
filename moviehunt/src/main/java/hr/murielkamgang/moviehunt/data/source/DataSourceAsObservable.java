package hr.murielkamgang.moviehunt.data.source;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by muriel on 24/2/18.
 */

public interface DataSourceAsObservable<T, ID> {

    Observable<Boolean> saveDataAsObservable(final T t);

    Observable<Boolean> saveDataAsObservable(final List<T> t);

    Observable<T> createDataAsObservable(final T t);

    Observable<T> getDataAsObservable(final ID id);

    Observable<List<T>> getAllDataAsObservable();

    Observable<Boolean> deleteAsObservable(final ID id);

    Observable<Boolean> deleteAllAsObservable();

    Observable<List<T>> getAllDataAsObservable(final ID id);

}
