package hr.murielkamgang.xmdb.data.source.movie;

import java.util.List;

import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.base.BaseLocalDataSource;
import io.reactivex.Observable;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by muriel on 3/4/18.
 */

public class MovieLocalSource extends BaseLocalDataSource<Movie, BaseKVH> implements MovieSourceExtension, MovieSourceExtensionAsObservable {

    @Override
    public List<Movie> getPopular() {//ignore the page here for now.
        final List<Movie> movies = getAllData();
        if (movies instanceof RealmResults) {
            return ((RealmResults<Movie>) movies).sort("votesAverage", Sort.DESCENDING);
        }

        return movies;
    }

    @Override
    public List<Movie> getTopRated() {
        final List<Movie> movies = getAllData();
        if (movies instanceof RealmResults) {
            return ((RealmResults<Movie>) movies).sort("popularity", Sort.DESCENDING);
        }

        return movies;
    }

    @Override
    public Observable<List<Movie>> getPopularAsObservable() {
        return Observable.fromCallable(this::getPopular);
    }

    @Override
    public Observable<List<Movie>> getTopRatedAsObservable() {
        return Observable.fromCallable(this::getTopRated);
    }
}
