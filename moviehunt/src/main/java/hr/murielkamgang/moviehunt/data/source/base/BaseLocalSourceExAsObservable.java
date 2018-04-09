package hr.murielkamgang.moviehunt.data.source.base;

import java.util.List;

import io.reactivex.Observable;
import io.realm.RealmObject;

/**
 * Created by muriel on 24/2/18.
 */

interface BaseLocalSourceExAsObservable<T extends RealmObject> {

    Observable<Boolean> cleanSaveAsObservable(final List<T> ts);
}
