package hr.murielkamgang.xmdb.data.source;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by muriel on 24/2/18.
 */

public interface Repository<T, ID> extends DataSourceAsObservable<T, ID> {

    Observable<T> getDataAsObservable(final ID id, boolean sync);

    Observable<List<T>> getAllDataAsObservable(boolean sync);

    Observable<List<T>> getAllDataAsObservable(ID id, boolean sync);

    DataSource<T, ID> getLocalDataSource();

    DataSource<T, ID> getRemoteDataSource();

}
