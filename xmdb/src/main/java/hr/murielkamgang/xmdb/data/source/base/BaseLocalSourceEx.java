package hr.murielkamgang.xmdb.data.source.base;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by muriel on 24/2/18.
 */

interface BaseLocalSourceEx<T extends RealmObject> {

    boolean cleanSave(final List<T> ts);
}
