package hr.murielkamgang.moviehunt.components.details.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.AdapterItemDivider;
import hr.murielkamgang.moviehunt.components.base.BaseDialogFragment;
import hr.murielkamgang.moviehunt.data.model.credits.Credits;
import hr.murielkamgang.moviehunt.data.model.credits.Crew;
import hr.murielkamgang.moviehunt.data.model.credits.People;
import hr.murielkamgang.moviehunt.data.model.movie.Extension;
import hr.murielkamgang.moviehunt.data.model.movie.ExtensionLanguage;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;

/**
 * Created by muriel on 3/24/18.
 */
public class MovieInfoFragment extends BaseDialogFragment<MovieInfoContract.View, MovieInfoContract.Presenter> implements MovieInfoContract.View {

    private static final Logger logger = LoggerFactory.getLogger(MovieInfoFragment.class);
    private static final String JOB_TITLE_DIRECTOR_NAME = "Director";

    @Inject
    MovieInfoContract.Presenter presenter;

    @Inject
    MovieInfoCastAdapter movieInfoCastAdapter;

    @Inject
    PeopleBinder peopleBinder;

    @BindView(R.id.textViewReleaseDate)
    TextView textViewReleaseDate;

    @BindView(R.id.textViewRuntime)
    TextView textViewRuntime;

    @BindView(R.id.textViewLanguage)
    TextView textViewLanguage;

    @BindView(R.id.textViewGenre)
    TextView textViewGenre;

    @BindView(R.id.textViewStoryLineInput)
    TextView textViewStoryLineInput;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    public MovieInfoFragment() {

    }

    @Override
    protected void onPostViewCreate(View view) {
        //todo init recyclerView
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
        textViewStoryLineInput.setText(movie.getOverview());
        textViewRuntime.setText(getRuntimePretty(movie));

        if (movie.getGenres() != null || !movie.getGenres().isEmpty()) {
            final List<String> genres = new ArrayList<>(movie.getGenres().size());
            for (final Extension genre : movie.getGenres()) {
                genres.add(genre.getName());
            }

            textViewGenre.setText(TextUtils.join(", ", genres));
        } else {
            textViewGenre.setText(" - ");
        }

        if (movie.getSpokenLanguage() != null || !movie.getSpokenLanguage().isEmpty()) {
            final List<String> spokenLanguages = new ArrayList<>(movie.getSpokenLanguage().size());
            for (final ExtensionLanguage language : movie.getSpokenLanguage()) {
                spokenLanguages.add(language.getName());
            }

            textViewLanguage.setText(TextUtils.join(", ", spokenLanguages));

        } else {
            textViewLanguage.setText(" - ");
        }

        final DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", getResources().getConfiguration().locale);
        final DateFormat dateFormatIn = new SimpleDateFormat("yyyy-MM-dd", getResources().getConfiguration().locale);
        try {
            textViewReleaseDate.setText(dateFormat.format(dateFormatIn.parse(movie.getReleaseDate())));
        } catch (ParseException e) {
            logger.debug("", e);
        }
    }

    @Override
    protected MovieInfoContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.fragment_movie_info;
    }

    @Override
    public void onCreditLoaded(Credits credits) {
        intiPeopleBinder();
        peopleBinder.bind(getDirector(credits.getCrews()));
        initCastRecyclerView();
        movieInfoCastAdapter.setItems(credits.getCasts());
    }

    private String getRuntimePretty(Movie movie) {
        return movie.getRuntime() / 60 + "h:" + movie.getRuntime() % 60 + "m";
    }

    private People getDirector(List<Crew> crews) {
        for (final Crew crew : crews) {
            if (JOB_TITLE_DIRECTOR_NAME.equalsIgnoreCase(crew.getJob())) {
                return crew;
            }
        }

        return null;
    }

    private void intiPeopleBinder() {
        if (!peopleBinder.isInit()) {
            peopleBinder.initTarget(getView());
        }
    }

    private void initCastRecyclerView() {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.addItemDecoration(new AdapterItemDivider(getContext(), getResources().getDimensionPixelOffset(R.dimen.recycler_view_spacing), AdapterItemDivider.ORIENTATION_HORIZONTAL));
            recyclerView.setAdapter(movieInfoCastAdapter);
        }
    }
}
