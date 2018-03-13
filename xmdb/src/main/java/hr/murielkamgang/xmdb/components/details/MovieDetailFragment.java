package hr.murielkamgang.xmdb.components.details;

import android.content.res.Resources;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.BindView;
import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.BaseDialogFragment;
import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.util.Utils;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by muriel on 3/10/18.
 */
public class MovieDetailFragment extends BaseDialogFragment<MovieDetailContract.View, MovieDetailContract.Presenter> implements MovieDetailContract.View {

    private final Logger logger = LoggerFactory.getLogger(MovieDetailFragment.class);

    @Inject
    MovieDetailContract.Presenter presenter;

    @Inject
    Picasso picasso;

    @BindView(R.id.imageViewPoster)
    ImageView imageViewPoster;

    @BindView(R.id.ratingBar)
    AppCompatRatingBar ratingBar;

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;

    @BindView(R.id.textViewReleaseYear)
    TextView textViewReleaseYear;

    @BindView(R.id.textViewReleaseDate)
    TextView textViewReleaseDate;

    @BindView(R.id.textViewStoryLineInput)
    TextView textViewStoryLineInput;

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
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    if (movie != null && movie.isValid()) {
                        toolbar.setTitle(movie.getTitle());
                        toolbar.setSubtitle(getMovieYear(movie));
                    }
                    isShow = true;
                } else if (isShow) {
                    toolbar.setTitle(" ");
                    toolbar.setSubtitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) {
            presenter.setView(this);
            presenter.loadMovie();
        }
    }

    @Override
    public void onMovieLoaded(Movie movie) {
        this.movie = movie;

        final DecimalFormat df = new DecimalFormat(".#");

        textViewRating.setText(df.format(movie.getVotesAverage()));
        textViewStoryLineInput.setText(movie.getOverview());

        final DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", getResources().getConfiguration().locale);
        final DateFormat dateFormatIn = new SimpleDateFormat("yyyy-MM-dd", getResources().getConfiguration().locale);
        try {
            textViewReleaseDate.setText(dateFormat.format(dateFormatIn.parse(movie.getReleaseDate())));
        } catch (ParseException e) {
            logger.debug("", e);
        }

        final String year = getMovieYear(movie);
        textViewReleaseYear.setText(year);
        textViewTitle.setText(movie.getTitle());

        toolbar.setTitle(movie.getTitle());
        toolbar.setSubtitle(year);

        ratingBar.setRating((float) movie.getVotesAverage());

        picasso
                .load(Utils.makePosterUrlFor(getContext(), movie.getPosterPath()))
                .fit()
                .transform(new RoundedCornersTransformation(10, 0))
                .into(imageViewPoster);

        picasso
                .load(Utils.makeBackDropUrlFor(getContext(), movie.getBackdropPath()))
                .fit()
                .centerCrop()
                .into(imageViewBackdrop);
    }

    private String getMovieYear(Movie movie) {
        return movie.getReleaseDate().substring(0, 4);
    }

    @Override
    protected MovieDetailContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.fragment_movie_detail;
    }
}
