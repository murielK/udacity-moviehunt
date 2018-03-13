package hr.murielkamgang.xmdb.data.source.movie;

import java.util.List;

import hr.murielkamgang.xmdb.data.model.movie.Movie;
import io.reactivex.Observable;

/**
 * Created by muriel on 3/10/18.
 */
public interface MovieSourcePagingExtensionAsObservable {

    Observable<List<Movie>> getPopularAsObservable(int page);

    Observable<List<Movie>> getTopRatedAsObservable(int page);
}
