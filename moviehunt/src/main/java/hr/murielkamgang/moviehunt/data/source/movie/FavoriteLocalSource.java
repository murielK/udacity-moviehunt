package hr.murielkamgang.moviehunt.data.source.movie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.murielkamgang.moviehunt.data.model.movie.Constant;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.data.model.movie.Tag;
import hr.murielkamgang.moviehunt.data.source.DataSource;
import hr.murielkamgang.moviehunt.data.source.DataSourceException;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import io.reactivex.Observable;

/**
 * Created by muriel on 4/10/18.
 */
public class FavoriteLocalSource implements DataSource<Movie, BaseKVH> {

    private final Context context;
    private final Logger logger = LoggerFactory.getLogger(FavoriteLocalSource.class);

    public FavoriteLocalSource(Context context) {
        this.context = context;
    }

    @Override
    public Movie getData(BaseKVH baseKVH) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public List<Movie> getAllData(BaseKVH baseKVH) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public List<Movie> getAllData() {
        Cursor cursor = null;

        try {
            cursor = context.getContentResolver().query(FavoriteContentProvider.FAVORITE_CONTENT_URI,
                    null,
                    null,
                    null,
                    FavoriteMovieDbHelper.KEY_ID);

            if (cursor != null && cursor.moveToFirst()) {
                final List<Movie> movies = new ArrayList<>();
                do {
                    final Movie movie = new Movie();
                    movie.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    movie.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    movie.setPosterPath(cursor.getString(cursor.getColumnIndex("posterPath")));
                    movie.getTags().add(new Tag(Constant.TAG_FAVORITE));
                    movies.add(movie);
                } while (cursor.moveToNext());

                return movies;
            }

        } catch (Exception e) {
            logger.debug("", e);

            throw new DataSourceException("getAllData {}", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public boolean saveData(Movie movie) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public boolean saveData(List<Movie> t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Movie createData(Movie movie) {
        try {
            final ContentValues contentValues = new ContentValues();
            contentValues.put(FavoriteMovieDbHelper.KEY_ID, movie.getId());
            contentValues.put(FavoriteMovieDbHelper.KEY_TITLE, movie.getTitle());
            contentValues.put(FavoriteMovieDbHelper.KEY_POSTER_PATH, movie.getPosterPath());
            context.getContentResolver().insert(FavoriteContentProvider.FAVORITE_CONTENT_URI, contentValues);
            return movie;
        } catch (Exception e) {
            logger.debug("", e);

            throw new DataSourceException("createData {}", e);
        }
    }

    @Override
    public boolean delete(BaseKVH baseKVH) {
        try {
            final int count = context.getContentResolver().delete(FavoriteContentProvider.FAVORITE_CONTENT_URI, "id=?", new String[]{baseKVH.getFieldValue().toString()});
            return count > 0;
        } catch (Exception e) {
            logger.debug("", e);

            throw new DataSourceException("createData {}", e);
        }
    }

    @Override
    public boolean deleteAll() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<Boolean> saveDataAsObservable(Movie movie) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<Boolean> saveDataAsObservable(List<Movie> t) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<Movie> createDataAsObservable(Movie movie) {
        return Observable.fromCallable(() -> createData(movie));
    }

    @Override
    public Observable<Movie> getDataAsObservable(BaseKVH baseKVH) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<List<Movie>> getAllDataAsObservable() {
        return Observable.fromCallable(() -> getAllData());
    }

    @Override
    public Observable<Boolean> deleteAsObservable(BaseKVH baseKVH) {
        return Observable.fromCallable(() -> delete(baseKVH));
    }

    @Override
    public Observable<Boolean> deleteAllAsObservable() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Observable<List<Movie>> getAllDataAsObservable(BaseKVH baseKVH) {
        throw new IllegalStateException("not implemented");
    }
}
