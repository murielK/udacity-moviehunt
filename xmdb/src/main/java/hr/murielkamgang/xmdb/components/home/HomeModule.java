package hr.murielkamgang.xmdb.components.home;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import hr.murielkamgang.xmdb.BuildConfig;
import hr.murielkamgang.xmdb.components.di.ActivityScoped;
import hr.murielkamgang.xmdb.components.di.FragmentScoped;
import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.Repository;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.movie.MovieLocalSource;
import hr.murielkamgang.xmdb.data.source.movie.MovieRemoteSource;
import hr.murielkamgang.xmdb.data.source.movie.MovieRepository;
import retrofit2.Retrofit;

/**
 * Created by muriel on 3/3/18.
 */

@Module
public abstract class HomeModule {

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

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @ActivityScoped
    @Binds
    abstract HomeContract.Presenter providePresenter(HomePresenter homePresenter);

    @ActivityScoped
    @Binds
    abstract Repository<Movie, BaseKVH> provideMovieRepository(MovieRepository movieRepository);
}
