package hr.murielkamgang.xmdb.components.details;

import android.content.res.Resources;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.Lazy;
import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.BaseDialogFragment;
import hr.murielkamgang.xmdb.components.details.image.ImageFragment;
import hr.murielkamgang.xmdb.components.details.info.MovieInfoFragment;
import hr.murielkamgang.xmdb.components.details.reviews.ReviewFragment;
import hr.murielkamgang.xmdb.components.details.trailers.TrailerFragment;
import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.util.Utils;

/**
 * Created by muriel on 3/10/18.
 */
public class MovieDetailFragment extends BaseDialogFragment<MovieDetailContract.View, MovieDetailContract.Presenter> implements MovieDetailContract.View {

    private final Logger logger = LoggerFactory.getLogger(MovieDetailFragment.class);

    @Inject
    MovieDetailContract.Presenter presenter;

    @Inject
    Picasso picasso;

    @Inject
    Lazy<MovieInfoFragment> movieInfoFragmentLazy;

    @Inject
    Lazy<TrailerFragment> trailerFragmentLazy;

    @Inject
    Lazy<ReviewFragment> reviewFragmentLazy;

    @Inject
    Lazy<ImageFragment> imageFragmentLazy;

    @BindView(R.id.imageViewPoster)
    ImageView imageViewPoster;

    @BindView(R.id.ratingBar)
    AppCompatRatingBar ratingBar;

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;

    @BindView(R.id.textViewReleaseYear)
    TextView textViewReleaseYear;

    @BindView(R.id.textViewRating)
    TextView textViewRating;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsingToolBarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @BindView(R.id.imageViewBackdrop)
    ImageView imageViewBackdrop;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    Movie movie;


    @Inject
    public MovieDetailFragment() {
    }

    @Override
    protected void onPostViewCreate(View view) {
        final MovieDetailActivity movieDetailActivity = (MovieDetailActivity) getActivity();
        movieDetailActivity.setSupportActionBar(toolbar);
        movieDetailActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        movieDetailActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);

        final LayerDrawable layerDrawable = (LayerDrawable) ratingBar.getProgressDrawable();

        final Resources res = getResources();

        DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(0)), res.getColor(R.color.textColorSecondary));   // Empty star
        DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(1)), res.getColor(R.color.colorSecondaryInactive)); // Partial star
        DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(2)), res.getColor(R.color.colorPrimary));  // Full star

        collapsingToolbarLayout.setTitleEnabled(false);

        //i found a way to hide title of toolbar here:
        // https://stackoverflow.com/questions/31662416/show-collapsingtoolbarlayout-title-only-when-collapsed
        //it is a bit modified than the selected answer to meet my needs.
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                appBarLayout.post(() -> {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        if (movie != null && movie.isValid() && toolbar != null) {
                            toolbar.setTitle(movie.getTitle());
                            toolbar.setSubtitle(getMovieYear(movie));
                            isShow = true;
                        }
                    } else if (isShow) {
                        if (toolbar != null) {
                            toolbar.setTitle(" ");
                            toolbar.setSubtitle(" ");
                            isShow = false;
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) {
            presenter.setView(this);
            presenter.load();
        }
    }

    @Override
    public void onMovieLoaded(Movie movie) {
        bindViews(movie);
        setUpViewPager();
    }

    @Override
    protected MovieDetailContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.fragment_movie_detail;
    }

    private String getMovieYear(Movie movie) {
        return movie.getReleaseDate().substring(0, 4);
    }

    private void bindViews(Movie movie) {
        this.movie = movie;

        final DecimalFormat df = new DecimalFormat(".#");

        textViewRating.setText(df.format(movie.getVotesAverage()));

        final String year = getMovieYear(movie);
        textViewReleaseYear.setText(year);
        textViewTitle.setText(movie.getTitle());

        toolbar.setTitle(movie.getTitle());
        toolbar.setSubtitle(year);

        ratingBar.setRating((float) movie.getVotesAverage());

        Utils.loadMoviePoster(imageViewPoster, picasso, movie);

        Utils.loadMovieBackDrop(imageViewBackdrop, picasso, movie);
    }

    private void setUpViewPager() {
        if (viewPager.getAdapter() == null) {
            viewPager.setAdapter(new MovieDetailFragmentPageAdapter(getChildFragmentManager()));
            tabLayout.setupWithViewPager(viewPager);
            viewPager.setOffscreenPageLimit(4);
            viewPager.setCurrentItem(0);
        }
    }

    private class MovieDetailFragmentPageAdapter extends FragmentPagerAdapter {

        private MovieDetailFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return movieInfoFragmentLazy.get();
                case 1:
                    return imageFragmentLazy.get();
                case 2:
                    return trailerFragmentLazy.get();
                case 3:
                    return reviewFragmentLazy.get();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.info);
                case 1:
                    return getResources().getString(R.string.photos);
                case 2:
                    return getResources().getString(R.string.trailer);
                case 3:
                    return getResources().getString(R.string.review);
                default:
                    return null;
            }
        }
    }
}
