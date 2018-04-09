package hr.murielkamgang.moviehunt.data.source;

/**
 * Created by muriel on 24/2/18.
 */

import hr.murielkamgang.moviehunt.data.source.base.BaseLocalDataSource;

/**
 * Class holder of fieldName + filedValue used to find object in {@link BaseLocalDataSource}
 */
public abstract class KeyValueHolder {

    public abstract String getFieldName();

    public abstract Object getFieldValue();
}

