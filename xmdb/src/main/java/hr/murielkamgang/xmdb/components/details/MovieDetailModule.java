package hr.murielkamgang.xmdb.components.details;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import hr.murielkamgang.xmdb.BuildConfig;
import hr.murielkamgang.xmdb.components.details.info.MovieInfoContract;
import hr.murielkamgang.xmdb.components.details.info.MovieInfoFragment;
import hr.murielkamgang.xmdb.components.details.info.MovieInfoPresenter;
import hr.murielkamgang.xmdb.components.di.ActivityScoped;
import hr.murielkamgang.xmdb.components.di.FragmentScoped;
import hr.murielkamgang.xmdb.data.model.credits.Credits;
import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.Repository;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.credits.CreditsLocalSource;
import hr.murielkamgang.xmdb.data.source.credits.CreditsRemoteSource;
import hr.murielkamgang.xmdb.data.source.credits.CreditsRepository;
import hr.murielkamgang.xmdb.data.source.movie.MovieLocalSource;
import hr.murielkamgang.xmdb.data.source.movie.MovieRemoteSource;
import hr.murielkamgang.xmdb.data.source.movie.MovieRepository;
import retrofit2.Retrofit;

/**
 * Created by muriel on 3/10/18.
 */
@Module
public abstract class MovieDetailModule {

    @ActivityScoped
    @Provides
    static MovieLocalSource provideMovieLocalSource() {
        return new MovieLocalSource();
    }

    @ActivityScoped
    @Provides
    static MovieRemoteSource provideMovieRemoteSource(Retrofit retrofit) {
        return new MovieRemoteSource(retrofit, BuildConfig.API_KEY);
    }

    @ActivityScoped
    @Provides
    static int provideMovieId(MovieDetailActivity movieDetailActivity) {
        return movieDetailActivity.getIntent().getIntExtra(MovieDetailActivity.EXTRA_MOVIE_ID_KEY, -1);
    }

    @ActivityScoped
    @Provides
    static CreditsLocalSource provideCreditsocalSource() {
        return new CreditsLocalSource();
    }

    @ActivityScoped
    @Provides
    static CreditsRemoteSource provideCreditsRemoteSource(Retrofit retrofit) {
        return new CreditsRemoteSource(retrofit, BuildConfig.API_KEY);
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MovieDetailFragment movieDetailFragment();

    @FragmentScoped
    @ContributesAndroidInjector()
    abstract MovieInfoFragment movieInfoFragment();

    @ActivityScoped
    @Binds
    abstract MovieDetailContract.Presenter provideMovieDetailPresenter(MovieDetailPresenter movieDetailPresenter);

    @ActivityScoped
    @Binds
    abstract Repository<Movie, BaseKVH> provideMovieRepository(MovieRepository movieRepository);

    //Info
    @ActivityScoped
    @Binds
    abstract MovieInfoContract.Presenter provideMovieInfoPresenter(MovieInfoPresenter movieInfoPresenter);

    @ActivityScoped
    @Binds
    abstract Repository<Credits, BaseKVH> provideCreditsRepository(CreditsRepository creditsRepository);


}
