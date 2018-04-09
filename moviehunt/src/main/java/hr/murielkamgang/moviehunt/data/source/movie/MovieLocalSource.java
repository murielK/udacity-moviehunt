package hr.murielkamgang.moviehunt.data.source.movie;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

import hr.murielkamgang.moviehunt.data.model.movie.Constant;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.data.model.movie.Tag;
import hr.murielkamgang.moviehunt.data.source.RealmHelper;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import hr.murielkamgang.moviehunt.data.source.base.BaseLocalDataSource;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by muriel on 3/4/18.
 */

public class MovieLocalSource extends BaseLocalDataSource<Movie, BaseKVH> implements MovieSourceExtension, MovieSourceExtensionAsObservable {

    private static final String TAG_QUERY_FIELD_NAME = "tags.tag";
    private final Logger logger = LoggerFactory.getLogger(MovieLocalSource.class);

    @Override
    public List<Movie> getPopular() {//ignore the page here for now.
        return getMovieForTag(Constant.TAG_POPULAR);
    }

    @Override
    public List<Movie> getTopRated() {
        return getMovieForTag(Constant.TAG_TOP_RATED);
    }

    @Override
    public List<Movie> getFavorite() {
        return getMovieForTag(Constant.TAG_FAVORITE);
    }

    @Override
    public Boolean addMovieToFavorite(BaseKVH baseKVH, boolean favorite) {
        Realm realm = null;

        try {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            final Movie localMovie = realm.where(Movie.class).equalTo("id", baseKVH.getFieldValue()).findFirst();
            if (favorite) {
                localMovie.getTags().add(new Tag(Constant.TAG_FAVORITE));
            } else {
                final Iterator<Tag> it = localMovie.getTags().iterator();
                while (it.hasNext()) {
                    final Tag tag = it.next();
                    if (tag.getTag().equals(Constant.TAG_FAVORITE)) {
                        it.remove();
                    }
                }
            }
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            if (realm != null) {
                if (realm.isInTransaction()) {
                    realm.cancelTransaction();
                }
                realm.close();
            }

            logger.debug("", e);
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
    public Observable<List<Movie>> getFavoriteAsObservable() {
        return Observable.fromCallable(this::getFavorite);
    }

    @Override
    public Observable<Boolean> addMovieToFavoriteAsObservable(BaseKVH baseKVH, boolean favorite) {
        return Observable.fromCallable(() -> addMovieToFavorite(baseKVH, favorite));
    }

    @Override
    public boolean saveData(@NonNull Movie movie) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            tryToRestoreTagFor(realm, movie);
            realm.insertOrUpdate(movie);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            if (realm != null) {
                if (realm.isInTransaction()) {
                    realm.cancelTransaction();
                }
                realm.close();
            }

            logger.debug("", e);
        }

        return false;
    }

    @Override
    public boolean saveData(@NonNull List<Movie> movies) {
        //this should always be called in a backgroundThread.
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            for (Movie m : movies) {
                tryToRestoreTagFor(realm, m);
            }
            realm.insertOrUpdate(movies);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            if (realm != null) {
                if (realm.isInTransaction()) {
                    realm.cancelTransaction();
                }
                realm.close();
            }

            logger.debug("", e);
        }

        return false;
    }

    private void tryToRestoreTagFor(Realm realm, Movie movie) {
        final Movie localMovie = realm.where(Movie.class).equalTo("id", movie.getId()).findFirst();
        if (localMovie != null) {
            final List<Tag> tags = localMovie.getTags();
            if (tags != null) {
                for (final Tag tag : tags) {
                    movie.getTags().add(tag);
                }
            }
        }
    }

    @Nullable
    private List<Movie> getMovieForTag(String tag, String... antiTags) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            if (antiTags == null || antiTags.length == 0) {
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

            logger.debug("", e);
        }

        return null;
    }
}


