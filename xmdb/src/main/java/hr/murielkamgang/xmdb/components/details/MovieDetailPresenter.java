package hr.murielkamgang.xmdb.components.details;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.components.details.base.BaseMovieDetailPresenter;
import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.Repository;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;

/**
 * Created by muriel on 3/10/18.
 */
class MovieDetailPresenter extends BaseMovieDetailPresenter<MovieDetailContract.View> implements MovieDetailContract.Presenter {

    @Inject
    MovieDetailPresenter(Repository<Movie, BaseKVH> movieRepository, int movieId) {
        super(movieRepository, movieId);
    }
}
