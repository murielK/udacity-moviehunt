package hr.murielkamgang.moviehunt.components.details;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.components.details.base.BaseMovieDetailPresenter;
import hr.murielkamgang.moviehunt.data.model.movie.Constant;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.data.model.movie.Tag;
import hr.murielkamgang.moviehunt.data.source.Repository;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import hr.murielkamgang.moviehunt.data.source.movie.MovieRepository;

/**
 * Created by muriel on 3/10/18.
 */
class MovieDetailPresenter extends BaseMovieDetailPresenter<MovieDetailContract.View> implements MovieDetailContract.Presenter {

    @Inject
    MovieDetailPresenter(Repository<Movie, BaseKVH> movieRepository, int movieId) {
        super(movieRepository, movieId);
    }

    @Override
    public void toggledFavorite(Movie movie) {
        ((MovieRepository) movieRepository).addMovieToFavoriteAsObservable(new BaseKVH("id", movie.getId()),
                !isFavorite(movie))
                .subscribe();
    }

    @Override
    public boolean isFavorite(Movie movie) {
        for (final Tag tag : movie.getTags()) {
            if (tag.getTag().equals(Constant.TAG_FAVORITE)) {
                return true;
            }
        }
        return false;
    }
}
