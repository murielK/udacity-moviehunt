package hr.murielkamgang.moviehunt.components.details;

import android.content.Context;

import com.squareup.picasso.Picasso;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import hr.murielkamgang.moviehunt.BuildConfig;
import hr.murielkamgang.moviehunt.components.details.image.ImageAdapter;
import hr.murielkamgang.moviehunt.components.details.image.ImageContract;
import hr.murielkamgang.moviehunt.components.details.image.ImageFragment;
import hr.murielkamgang.moviehunt.components.details.image.ImagePresenter;
import hr.murielkamgang.moviehunt.components.details.info.MovieInfoCastAdapter;
import hr.murielkamgang.moviehunt.components.details.info.MovieInfoContract;
import hr.murielkamgang.moviehunt.components.details.info.MovieInfoFragment;
import hr.murielkamgang.moviehunt.components.details.info.MovieInfoPresenter;
import hr.murielkamgang.moviehunt.components.details.reviews.ReviewAdapter;
import hr.murielkamgang.moviehunt.components.details.reviews.ReviewContract;
import hr.murielkamgang.moviehunt.components.details.reviews.ReviewFragment;
import hr.murielkamgang.moviehunt.components.details.reviews.ReviewPresenter;
import hr.murielkamgang.moviehunt.components.details.trailers.TrailerAdapter;
import hr.murielkamgang.moviehunt.components.details.trailers.TrailerContract;
import hr.murielkamgang.moviehunt.components.details.trailers.TrailerFragment;
import hr.murielkamgang.moviehunt.components.details.trailers.TrailerPresenter;
import hr.murielkamgang.moviehunt.components.di.ActivityScoped;
import hr.murielkamgang.moviehunt.components.di.FragmentScoped;
import hr.murielkamgang.moviehunt.data.model.credits.Credits;
import hr.murielkamgang.moviehunt.data.model.image.Image;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.data.model.review.Review;
import hr.murielkamgang.moviehunt.data.model.video.Video;
import hr.murielkamgang.moviehunt.data.source.Repository;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import hr.murielkamgang.moviehunt.data.source.credits.CreditsLocalSource;
import hr.murielkamgang.moviehunt.data.source.credits.CreditsRemoteSource;
import hr.murielkamgang.moviehunt.data.source.credits.CreditsRepository;
import hr.murielkamgang.moviehunt.data.source.image.ImageLocalSource;
import hr.murielkamgang.moviehunt.data.source.image.ImageRemoteSource;
import hr.murielkamgang.moviehunt.data.source.image.ImagesRepository;
import hr.murielkamgang.moviehunt.data.source.movie.FavoriteLocalSource;
import hr.murielkamgang.moviehunt.data.source.movie.MovieLocalSource;
import hr.murielkamgang.moviehunt.data.source.movie.MovieRemoteSource;
import hr.murielkamgang.moviehunt.data.source.movie.MovieRepository;
import hr.murielkamgang.moviehunt.data.source.review.ReviewLocalSource;
import hr.murielkamgang.moviehunt.data.source.review.ReviewRemoteSource;
import hr.murielkamgang.moviehunt.data.source.review.ReviewRepository;
import hr.murielkamgang.moviehunt.data.source.video.VideoLocalSource;
import hr.murielkamgang.moviehunt.data.source.video.VideoRemoteSource;
import hr.murielkamgang.moviehunt.data.source.video.VideoRepository;
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
    static FavoriteLocalSource provideFavoriteLocalSource(Context context) {
        return new FavoriteLocalSource(context);
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
    static VideoRemoteSource provideVideoRemoteSource(Retrofit retrofit) {
        return new VideoRemoteSource(retrofit, BuildConfig.API_KEY);
    }

    @ActivityScoped
    @Provides
    static TrailerAdapter provideTrailerAdapter(Picasso picasso) {
        return new TrailerAdapter(picasso);
    }

    @ActivityScoped
    @Provides
    static ReviewLocalSource provideReviewLocalSource() {
        return new ReviewLocalSource();
    }

    @ActivityScoped
    @Provides
    static ReviewRemoteSource provideReviewRemoteSource(Retrofit retrofit) {
        return new ReviewRemoteSource(retrofit, BuildConfig.API_KEY);
    }

    @ActivityScoped
    @Provides
    static ReviewAdapter provideReviewAdapter() {
        return new ReviewAdapter();
    }

    @ActivityScoped
    @Provides
    static ImageLocalSource provideImageLocalSource() {
        return new ImageLocalSource();
    }

    @ActivityScoped
    @Provides
    static ImageRemoteSource provideImageRemoteSource(Retrofit retrofit) {
        return new ImageRemoteSource(retrofit, BuildConfig.API_KEY);
    }

    @ActivityScoped
    @Provides
    static ImageAdapter provideImageAdapter(Picasso picasso) {
        return new ImageAdapter(picasso);
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
    @ContributesAndroidInjector
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
    @ContributesAndroidInjector
    abstract TrailerFragment trailerFragment();

    //Review
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ReviewFragment reviewFragment();

    @ActivityScoped
    @Binds
    abstract ReviewContract.Presenter provideReviewPresenter(ReviewPresenter reviewPresenter);

    @ActivityScoped
    @Binds
    abstract Repository<Review, BaseKVH> provideReviewRepository(ReviewRepository reviewRepository);

    //Image
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ImageFragment imageFragment();

    @ActivityScoped
    @Binds
    abstract ImageContract.Presenter provideImagePresenter(ImagePresenter imagePresenter);

    @ActivityScoped
    @Binds
    abstract Repository<Image, BaseKVH> provideImageRepository(ImagesRepository imagesRepository);
}
