package hr.murielkamgang.moviehunt.data.source.movie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by muriel on 4/10/18.
 */
public class FavoriteMovieDbHelper extends SQLiteOpenHelper {

    static final String TABLE_NAME = "favorite";
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_POSTER_PATH = "posterPath";
    static final String DB_NAME = "movie.hunt.db";
    static final int DB_VERSION = 1;

    public FavoriteMovieDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_FAVORITE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_TITLE + " TEXT NOT NULL, " +
                KEY_POSTER_PATH + " TEXT NOT NULL " +
                ")";

        db.execSQL(CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
