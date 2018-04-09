package hr.murielkamgang.moviehunt.components.details;

import android.content.res.Resources;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
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
import butterknife.OnClick;
import dagger.Lazy;
import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.BaseDialogFragment;
import hr.murielkamgang.moviehunt.components.base.BaseFragment;
import hr.murielkamgang.moviehunt.components.details.image.ImageFragment;
import hr.murielkamgang.moviehunt.components.details.info.MovieInfoFragment;
import hr.murielkamgang.moviehunt.components.details.reviews.ReviewFragment;
import hr.murielkamgang.moviehunt.components.details.trailers.TrailerFragment;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.util.Utils;

/**
 * Created by muriel on 3/10/18.
 */
public class MovieDetailFragment extends BaseDialogFragment<MovieDetailContract.View, MovieDetailContract.Presenter> implements MovieDetailContract.View {

    private static final String BUNDLE_CURRENT_TAB_POSITION = "BUNDLE_CURRENT_TAB_POSITION";
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

    @BindView(R.id.imageViewFavorite)
    ImageView imageViewFavorite;

    private Movie movie;

    private BaseFragment currentFragment;

    private int currentTabPosition;

    private ArrayMap<String, Lazy<? extends BaseFragment>> arrayMapTagFragment;

    @Inject
    public MovieDetailFragment() {
    }

    @Override
    protected void onPostViewCreate(View view) {
        imageViewFavorite.setEnabled(false);

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

        arrayMapTagFragment = new ArrayMap<>();
        arrayMapTagFragment.put(getResources().getString(R.string.info), movieInfoFragmentLazy);
        arrayMapTagFragment.put(getResources().getString(R.string.trailer), trailerFragmentLazy);
        arrayMapTagFragment.put(getResources().getString(R.string.review), reviewFragmentLazy);
        arrayMapTagFragment.put(getResources().getString(R.string.photos), imageFragmentLazy);

        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
        }

        if (presenter != null) {
            presenter.setView(this);
            presenter.load();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_CURRENT_TAB_POSITION, currentTabPosition);

    }

    @Override
    public void onMovieLoaded(Movie movie) {
        bindViews(movie);
        if (tabLayout.getTabCount() == 0) {
            setUpTabs();
            setTabAt(0);
        }
    }

    @Override
    protected MovieDetailContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeListenerOnMovie();
    }

    @OnClick(R.id.imageViewFavorite)
    void onClick(View view) {
        if (presenter != null) {
            presenter.toggledFavorite(movie);
        }
    }

    private String getMovieYear(Movie movie) {
        return movie.getReleaseDate().substring(0, 4);
    }

    private void bindViews(Movie movie) {
        removeListenerOnMovie();
        this.movie = movie;
        this.movie.addChangeListener(realmModel -> {
            bindViews(movie);
        });

        if (!imageViewFavorite.isEnabled()) {
            imageViewFavorite.setEnabled(true);
        }

        imageViewFavorite.setImageResource(presenter.isFavorite(this.movie) ? R.drawable.ic_favorited : R.drawable.ic_not_favorited);

        final DecimalFormat df = new DecimalFormat(".#");

        textViewRating.setText(df.format(movie.getVotesAverage()));

        final String year = getMovieYear(movie);
        textViewReleaseYear.setText(year);
        textViewTitle.setText(movie.getTitle());

        ratingBar.setRating((float) movie.getVotesAverage());

        Utils.loadMoviePoster(imageViewPoster, picasso, movie);

        Utils.loadMovieBackDrop(imageViewBackdrop, picasso, movie);
    }

    private void setTabAt(int position) {
        final TabLayout.Tab tab = tabLayout.getTabAt(position);
        tab.select();
        setCurrentTab(tab.getText().toString());
    }

    private void setUpTabs() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.info));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.trailer));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.photos));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.review));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTabPosition = tab.getPosition();
                setCurrentTab(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        currentTabPosition = savedInstanceState.getInt(BUNDLE_CURRENT_TAB_POSITION);
        setUpTabs();
        setTabAt(currentTabPosition);
    }

    private void setCurrentTab(String tag) {
        final FragmentManager fm = getChildFragmentManager();

        final BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(tag);

        if (currentFragment != null && currentFragment == fragment) {
            return;
        } else if (currentFragment != null) {
            fm
                    .beginTransaction()
                    .hide(currentFragment)
                    .commit();
        } else {//just in case there are some rogue fragment when the activity is recreate
            for (final Fragment f : fm.getFragments()) {
                if (fragment != f && f.isVisible()) {
                    fm
                            .beginTransaction()
                            .hide(f)
                            .commit();
                }

            }
        }

        currentFragment = fragment;
        if (currentFragment == null) {
            currentFragment = arrayMapTagFragment.get(tag).get();
            fm.beginTransaction().add(R.id.container, currentFragment, tag).commit();
            fm.executePendingTransactions();
        } else if (!currentFragment.isVisible()) {
            fm.beginTransaction().show(currentFragment).commit();
        }
    }

    private void removeListenerOnMovie() {
        if (movie != null) {
            movie.removeAllChangeListeners();
        }
    }
}
