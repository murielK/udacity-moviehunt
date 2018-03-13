package hr.murielkamgang.xmdb.data.source;

import java.util.List;

/**
 * Created by muriel on 24/2/18.
 */

public interface DataSource<T, ID> extends DataSourceAsObservable<T, ID> {

    T getData(final ID id);

    List<T> getAllData(final ID id);

    List<T> getAllData();

    boolean saveData(final T t);

    boolean saveData(final List<T> t);

    T createData(final T t);

    boolean delete(final ID id);

    boolean deleteAll();
}
