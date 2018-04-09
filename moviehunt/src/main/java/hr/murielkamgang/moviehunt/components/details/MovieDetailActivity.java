package hr.murielkamgang.moviehunt.components.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.Lazy;
import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.BaseFragment;
import hr.murielkamgang.moviehunt.components.base.BaseToolBarActivity;
import hr.murielkamgang.moviehunt.components.home.HomeActivity;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;

/**
 * Created by muriel on 3/10/18.
 */
public class MovieDetailActivity extends BaseToolBarActivity {

    public static final String EXTRA_MOVIE_ID_KEY = "EXTRA_MOVIE_ID_KEY";

    @Inject
    Lazy<MovieDetailFragment> movieDetailFragment;

    public static void view(@NonNull Context context, @NonNull Movie movie) {
        final Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID_KEY, movie.getId());
        context.startActivity(intent);
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.activity_base;
    }

    @Override
    protected BaseFragment provideFragment() {
        return movieDetailFragment.get();
    }

    @Override
    protected int provideToolbarTitleResId() {
        return 0;
    }

    @Override
    protected Class<? extends AppCompatActivity> provideParentActivityClass() {
        return HomeActivity.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
