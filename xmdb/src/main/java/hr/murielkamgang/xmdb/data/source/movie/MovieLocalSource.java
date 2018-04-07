package hr.murielkamgang.xmdb.data.source.movie;

import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.List;

import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.model.movie.MovieTag;
import hr.murielkamgang.xmdb.data.source.RealmHelper;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.base.BaseLocalDataSource;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by muriel on 3/4/18.
 */

public class MovieLocalSource extends BaseLocalDataSource<Movie, BaseKVH> implements MovieSourceExtension, MovieSourceExtensionAsObservable {

    private static final String TAG_QUERY_FIELD_NAME = "tags.tag";

    @Override
    public List<Movie> getPopular() {//ignore the page here for now.
        final List<Movie> movies = getMovieForTag(MovieTag.TAG_POPULAR);
        if (movies instanceof RealmResults) {
            return ((RealmResults<Movie>) movies).sort("votesAverage", Sort.DESCENDING);
        }

        return movies;
    }

    @Override
    public List<Movie> getTopRated() {
        final List<Movie> movies = getMovieForTag(MovieTag.TAG_TOP_RATED);
        if (movies instanceof RealmResults) {
            return ((RealmResults<Movie>) movies).sort("popularity", Sort.DESCENDING);
        }

        return movies;
    }

    @Override
    public Boolean addMovieToFavorite(BaseKVH baseKVH, boolean favorite) {
        Realm realm = null;

        try {
            realm = Realm.getDefaultInstance();
            final RealmResults<Movie> localMovie = RealmHelper.queryEqualTo(realm, Movie.class, "id", baseKVH.getFieldValue());
            if (localMovie != null && !localMovie.isEmpty()) {
                realm.beginTransaction();
                if (favorite) {
                    localMovie.first().addTag(MovieTag.TAG_FAVORITE);
                } else {
                    localMovie.first().remoteTag(MovieTag.TAG_FAVORITE);
                }
                realm.commitTransaction();
            }
            return true;
        } catch (Exception e) {
            if (realm != null) {
                if (realm.isInTransaction()) {
                    realm.cancelTransaction();
                }
                realm.close();
            }
        }
        return false;
    }

    @Override
    public Observable<List<Movie>> getPopularAsObservable() {
        return Observable.fromCallable(this::getPopular);
    }

    @Override
    public Observable<List<Movie>> getTopRatedAsObservable() {
        return Observable.fromCallable(this::getTopRated);
    }

    @Override
    public Boolean addMovieToFavoriteAsObservable(BaseKVH baseKVH, boolean favorite) {
        return null;
    }

    @Override
    public boolean saveData(@NonNull List<Movie> movies) {
        //this should always be called in a backgroundThread.
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            for (Movie m : movies) {
                final RealmResults<Movie> localMovie = RealmHelper.queryEqualTo(realm, Movie.class, "id", m.getId());
                if (localMovie != null && !localMovie.isEmpty()) {
                    final List<MovieTag> movieTags = localMovie.first().getTags();
                    if (movieTags != null) {
                        for (final MovieTag movieTag : movieTags) {
                            m.addTag(movieTag);
                        }
                    }
                }
            }

            RealmHelper.realmWrite(realm, movies);
            return true;
        } catch (Exception e) {
            if (realm != null) {
                realm.close();
            }
        }

        return false;
    }

    private List<Movie> getMovieForTag(String tag, String... antiTags) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            if (antiTags == null || antiTags.length != 0) {
                return RealmHelper.queryEqualTo(realm, Movie.class, TAG_QUERY_FIELD_NAME, tag);
            } else {
                RealmQuery<Movie> query = realm.where(Movie.class)
                        .equalTo(TAG_QUERY_FIELD_NAME, tag);
                for (final String antiTag : antiTags) {
                    query.and().notEqualTo(TAG_QUERY_FIELD_NAME, antiTag);
                }

                return query.findAll();
            }
        } catch (Exception e) {
            if (realm != null) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    realm.close();
                }
            }
        }

        return null;
    }
}


