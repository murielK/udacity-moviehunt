package hr.murielkamgang.xmdb.components.details;

import com.squareup.picasso.Picasso;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import hr.murielkamgang.xmdb.BuildConfig;
import hr.murielkamgang.xmdb.components.details.info.MovieInfoCastAdapter;
import hr.murielkamgang.xmdb.components.details.info.MovieInfoContract;
import hr.murielkamgang.xmdb.components.details.info.MovieInfoFragment;
import hr.murielkamgang.xmdb.components.details.info.MovieInfoPresenter;
import hr.murielkamgang.xmdb.components.details.trailers.TrailerAdapter;
import hr.murielkamgang.xmdb.components.details.trailers.TrailerContract;
import hr.murielkamgang.xmdb.components.details.trailers.TrailerFragment;
import hr.murielkamgang.xmdb.components.details.trailers.TrailerPresenter;
import hr.murielkamgang.xmdb.components.di.ActivityScoped;
import hr.murielkamgang.xmdb.components.di.FragmentScoped;
import hr.murielkamgang.xmdb.data.model.credits.Credits;
import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.model.video.Video;
import hr.murielkamgang.xmdb.data.source.Repository;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.credits.CreditsLocalSource;
import hr.murielkamgang.xmdb.data.source.credits.CreditsRemoteSource;
import hr.murielkamgang.xmdb.data.source.credits.CreditsRepository;
import hr.murielkamgang.xmdb.data.source.movie.MovieLocalSource;
import hr.murielkamgang.xmdb.data.source.movie.MovieRemoteSource;
import hr.murielkamgang.xmdb.data.source.movie.MovieRepository;
import hr.murielkamgang.xmdb.data.source.video.VideoLocalSource;
import hr.murielkamgang.xmdb.data.source.video.VideoRemoteSource;
import hr.murielkamgang.xmdb.data.source.video.VideoRepository;
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
    static CreditsLocalSource provideCreditLocalSource() {
        return new CreditsLocalSource();
    }

    @ActivityScoped
    @Provides
    static CreditsRemoteSource provideCreditsRemoteSource(Retrofit retrofit) {
        return new CreditsRemoteSource(retrofit, BuildConfig.API_KEY);
    }

    @ActivityScoped
    @Provides
    static MovieInfoCastAdapter provideMovieInfoCastAdapter(Picasso picasso) {
        return new MovieInfoCastAdapter(picasso);
    }

    //Trailer
    @ActivityScoped
    @Provides
    static VideoLocalSource provideVideoLocalSource() {
        return new VideoLocalSource();
    }

    @ActivityScoped
    @Provides
    static VideoRemoteSource provideVideRemoteSource(Retrofit retrofit) {
        return new VideoRemoteSource(retrofit, BuildConfig.API_KEY);
    }

    @ActivityScoped
    @Provides
    static TrailerAdapter provideTrailerAdapter(Picasso picasso) {
        return new TrailerAdapter(picasso);
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MovieDetailFragment movieDetailFragment();

    @ActivityScoped
    @Binds
    abstract MovieDetailContract.Presenter provideMovieDetailPresenter(MovieDetailPresenter movieDetailPresenter);

    @ActivityScoped
    @Binds
    abstract Repository<Movie, BaseKVH> provideMovieRepository(MovieRepository movieRepository);

    //Info
    @FragmentScoped
    @ContributesAndroidInjector()
    abstract MovieInfoFragment movieInfoFragment();

    @ActivityScoped
    @Binds
    abstract MovieInfoContract.Presenter provideMovieInfoPresenter(MovieInfoPresenter movieInfoPresenter);

    @ActivityScoped
    @Binds
    abstract Repository<Credits, BaseKVH> provideCreditsRepository(CreditsRepository creditsRepository);

    @ActivityScoped
    @Binds
    abstract TrailerContract.Presenter provideTrailerPresenter(TrailerPresenter trailerPresenter);

    @ActivityScoped
    @Binds
    abstract Repository<Video, BaseKVH> provideVideoRepository(VideoRepository videoRepository);

    @FragmentScoped
    @ContributesAndroidInjector()
    abstract TrailerFragment trailerFragment();

}
