package hr.murielkamgang.moviehunt.data.source.base;

import java.util.List;

import hr.murielkamgang.moviehunt.data.source.DataSource;
import io.reactivex.Observable;

/**
 * Created by muriel on 24/2/18.
 */

public abstract class BaseRemoteDataSource<T, ID> implements DataSource<T, ID> {

    @Override
    public T getData(ID id) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<Boolean> saveDataAsObservable(T t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public List<T> getAllData() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<Boolean> saveDataAsObservable(List<T> t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public boolean saveData(T t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public boolean saveData(List<T> t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<T> createDataAsObservable(T t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public T createData(T t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<T> getDataAsObservable(ID id) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public boolean delete(ID id) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public boolean deleteAll() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<List<T>> getAllDataAsObservable() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<Boolean> deleteAsObservable(ID id) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<Boolean> deleteAllAsObservable() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public List<T> getAllData(ID id) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<List<T>> getAllDataAsObservable(ID id) {
        throw new IllegalStateException("not implemented");
    }
}