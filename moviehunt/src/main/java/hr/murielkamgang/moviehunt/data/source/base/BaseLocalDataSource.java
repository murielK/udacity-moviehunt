package hr.murielkamgang.moviehunt.data.source.base;

import android.os.Looper;
import android.support.annotation.NonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import hr.murielkamgang.moviehunt.data.source.DataSource;
import hr.murielkamgang.moviehunt.data.source.DataSourceException;
import hr.murielkamgang.moviehunt.data.source.KeyValueHolder;
import hr.murielkamgang.moviehunt.data.source.RealmHelper;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by muriel on 24/2/18.
 */

public abstract class BaseLocalDataSource<T extends RealmObject, K extends KeyValueHolder> implements
        DataSource<T, K>, BaseLocalSourceEx<T>, BaseLocalSourceExAsObservable<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseLocalDataSource.class);

    /**
     * Class of the subclass realmObject
     */
    private Class<T> tClass;

    public BaseLocalDataSource() {
        final Type superClass = getClass().getGenericSuperclass();
        final Type[] arguments = ((ParameterizedType) superClass).getActualTypeArguments();

        final Type firstType = arguments[0];

        if (!(firstType instanceof Class)) {
            logger.debug("could not resolve the type of the first argument");
            throw new IllegalArgumentException();
        } else {
            this.tClass = (Class<T>) firstType;
        }
    }

    @Override
    public T getData(@NonNull K k) {
        T t = null;
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            t = getData(realm, k);
        } catch (Exception e) {
            if (realm != null && Looper.myLooper() != Looper.getMainLooper()) {
                realm.close();
            }

            logger.debug("", e);
        }

        return t;
    }

    private T getData(@NonNull Realm realm, @NonNull K k) {
        RealmResults<T> results;
        final Object value = k.getFieldValue();

        if (value == null) {
            throw new NullPointerException("value cannot bet null");
        }

        if (value instanceof String) {
            results = RealmHelper.queryEqualTo(realm, tClass, k.getFieldName(), (String) value);
        } else if (value instanceof Integer) {
            results = RealmHelper.queryEqualTo(realm, tClass, k.getFieldName(), (int) value);
        } else if (value instanceof Long) {
            results = RealmHelper.queryEqualTo(realm, tClass, k.getFieldName(), (long) value);
        } else
            throw new IllegalArgumentException("value can be only a long,int,or string"); // because i said so!

        if (results == null) {
            throw new NullPointerException("nothing found");
        }

        return results.first();
    }

    @Override
    public List<T> getAllData() {
        List<T> ts = null;
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            ts = RealmHelper.findAll(realm, tClass);
        } catch (Exception e) {
            if (realm != null && Looper.myLooper() != Looper.getMainLooper()) {
                realm.close();
            }

            logger.debug("", e);
        }

        return ts;
    }

    private List<T> getAllData(@NonNull Realm realm, @NonNull K k) {
        RealmResults<T> results;
        final Object value = k.getFieldValue();

        if (value == null) {
            throw new NullPointerException("key cannot bet null");
        }

        if (value instanceof String) {
            results = RealmHelper.queryEqualTo(realm, tClass, k.getFieldName(), (String) value);
        } else if (value instanceof Integer) {
            results = RealmHelper.queryEqualTo(realm, tClass, k.getFieldName(), (int) value);
        } else if (value instanceof Long) {
            results = RealmHelper.queryEqualTo(realm, tClass, k.getFieldName(), (long) value);
        } else
            throw new IllegalArgumentException("key can be only a long,int,or string"); // because i said so!

        if (results == null) {
            throw new NullPointerException("nothing found");
        }

        return results;
    }

    private Observable<List<T>> getAllDataAsObservable(@NonNull Realm realm, @NonNull K k) {
        return Observable.fromCallable(() -> getAllData(realm, k));
    }

    @Override
    public List<T> getAllData(K k) {
        List<T> ts = null;
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            ts = getAllData(realm, k);
        } catch (Exception e) {
            if (realm != null && Looper.myLooper() != Looper.getMainLooper()) {
                realm.close();
            }

            logger.debug("", e);
        }

        return ts;
    }

    @Override
    public Observable<List<T>> getAllDataAsObservable(K k) {
        return Observable.fromCallable(() -> getAllData(k));
    }

    @Override
    public boolean saveData(@NonNull T t) {
        return RealmHelper.realmWrite(t);
    }

    @Override
    public boolean saveData(@NonNull List<T> t) {
        return RealmHelper.realmWrite(t);
    }

    @Override
    public T createData(@NonNull T t) {
        final boolean success = RealmHelper.realmWrite(t);
        if (!success)
            throw new DataSourceException("unable to createProfile {}" + toString(), null);
        return t;
    }

    @Override
    public boolean delete(@NonNull K k) {
        final Object key = k.getFieldValue();

        if (key == null) {
            throw new NullPointerException("key cannot bet null");
        }

        if (key instanceof String) {
            return RealmHelper.delete(tClass, k.getFieldName(), (String) k.getFieldValue());
        } else if (key instanceof Integer) {
            return RealmHelper.delete(tClass, k.getFieldName(), (int) k.getFieldValue());
        } else if (key instanceof Long) {
            return RealmHelper.delete(tClass, k.getFieldName(), (long) k.getFieldValue());
        } else
            throw new IllegalArgumentException("key can be only a long,int,or string"); // because i said so!
    }

    @Override
    public boolean deleteAll() {
        return RealmHelper.deleteAll(tClass);
    }

    @Override
    public Observable<Boolean> saveDataAsObservable(@NonNull final T t) {
        return Observable.fromCallable(() -> saveData(t));
    }

    @Override
    public Observable<Boolean> saveDataAsObservable(@NonNull final List<T> t) {
        return Observable.fromCallable(() -> saveData(t));
    }

    @Override
    public Observable<T> createDataAsObservable(@NonNull final T t) {
        return Observable.fromCallable(() -> createData(t));
    }

    @Override
    public Observable<T> getDataAsObservable(@NonNull final K k) {
        return Observable.fromCallable(() -> getData(k));
    }

    @Override
    public Observable<List<T>> getAllDataAsObservable() {
        return Observable.fromCallable(this::getAllData);
    }

    @Override
    public Observable<Boolean> deleteAsObservable(@NonNull final K k) {
        return Observable.fromCallable(() -> delete(k));
    }

    @Override
    public Observable<Boolean> deleteAllAsObservable() {
        return Observable.fromCallable(this::deleteAll);
    }

    @Override
    public boolean cleanSave(List<T> ts) {
        return RealmHelper.realmCleanWrite(ts, tClass);
    }

    @Override
    public Observable<Boolean> cleanSaveAsObservable(final List<T> ts) {
        return Observable.fromCallable(() -> cleanSave(ts));
    }
}
