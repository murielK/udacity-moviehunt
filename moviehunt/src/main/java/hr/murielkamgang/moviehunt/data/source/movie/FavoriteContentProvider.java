package hr.murielkamgang.moviehunt.data.source.movie;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import hr.murielkamgang.moviehunt.BuildConfig;

/**
 * Created by muriel on 4/10/18.
 */
public class FavoriteContentProvider extends ContentProvider {

    private static final String PATH_FAVORITE = "favorite";
    public static final Uri FAVORITE_CONTENT_URI = Uri.parse("content://" + BuildConfig.CONTENT_AUTHORITY)
            .buildUpon().appendPath(PATH_FAVORITE).build();
    private static final int ROOT = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(BuildConfig.CONTENT_AUTHORITY, PATH_FAVORITE, ROOT);
    }

    private FavoriteMovieDbHelper favoriteMovieDbHelper;

    @Override
    public boolean onCreate() {
        favoriteMovieDbHelper = new FavoriteMovieDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = favoriteMovieDbHelper.getReadableDatabase();
        final int i = uriMatcher.match(uri);
        if (i == ROOT) {
            return db.query(FavoriteMovieDbHelper.TABLE_NAME,
                    projection,
                    selection, selectionArgs,
                    null,
                    null,
                    sortOrder);
        } else {
            throw new UnsupportedOperationException("unsupported uri: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new IllegalStateException("not implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int i = uriMatcher.match(uri);
        if (i == ROOT) {
            final long id = favoriteMovieDbHelper.getWritableDatabase().insert(FavoriteMovieDbHelper.TABLE_NAME, null, values);
            if (id > 0) {
                getContext().getContentResolver().notifyChange(ContentUris.withAppendedId(uri, id), null);
            } else {
                throw new android.database.SQLException("error while inserting: " + uri);
            }
        } else {
            throw new UnsupportedOperationException("unsupported uri: " + uri);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int i = uriMatcher.match(uri);
        if (i == ROOT) {
            int count = favoriteMovieDbHelper.getWritableDatabase().delete(FavoriteMovieDbHelper.TABLE_NAME
                    , selection, selectionArgs);
            if (count > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return count;
        } else {
            throw new UnsupportedOperationException("unsupported uri: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new IllegalStateException("not implemented");
    }
}
