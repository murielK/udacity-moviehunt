package hr.murielkamgang.moviehunt.components.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import hr.murielkamgang.moviehunt.components.details.MovieDetailActivity;
import hr.murielkamgang.moviehunt.components.details.MovieDetailModule;
import hr.murielkamgang.moviehunt.components.details.image.ImageViewModule;
import hr.murielkamgang.moviehunt.components.details.image.ImageViewerActivity;
import hr.murielkamgang.moviehunt.components.home.HomeActivity;
import hr.murielkamgang.moviehunt.components.home.HomeModule;

/**
 * Created by muriel on 9/20/17.
 */

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MovieDetailModule.class)
    abstract MovieDetailActivity movieDetailActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ImageViewModule.class)
    abstract ImageViewerActivity imageViewerActivity();

}