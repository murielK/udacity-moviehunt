package hr.murielkamgang.xmdb.data.source.movie;

import java.util.List;

import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;

/**
 * Created by muriel on 3/10/18.
 */
public interface MovieSourceExtension {

    List<Movie> getPopular();

    List<Movie> getTopRated();

    Boolean addMovieToFavorite(BaseKVH baseKVH, boolean favorite);
}
