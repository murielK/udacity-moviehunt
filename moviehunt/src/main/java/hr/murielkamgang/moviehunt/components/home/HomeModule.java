package hr.murielkamgang.moviehunt.components.home;

import com.squareup.picasso.Picasso;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import hr.murielkamgang.moviehunt.BuildConfig;
import hr.murielkamgang.moviehunt.components.di.ActivityScoped;
import hr.murielkamgang.moviehunt.components.di.FragmentScoped;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.data.source.Repository;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import hr.murielkamgang.moviehunt.data.source.movie.MovieLocalSource;
import hr.murielkamgang.moviehunt.data.source.movie.MovieRemoteSource;
import hr.murielkamgang.moviehunt.data.source.movie.MovieRepository;
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

    @ActivityScoped
    @Provides
    static HomeAdapter provideHomeAdapter(Picasso picasso) {
        return new HomeAdapter(picasso);
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
