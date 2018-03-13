package hr.murielkamgang.xmdb.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import hr.murielkamgang.xmdb.components.di.AppComponent;
import hr.murielkamgang.xmdb.components.di.DaggerAppComponent;
import hr.murielkamgang.xmdb.data.source.MigrationHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by muriel on 3/3/18.
 */

public class XmdbApplication extends DaggerApplication {

    private final Logger logger = LoggerFactory.getLogger(XmdbApplication.class);

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        final AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        RxJavaPlugins.setErrorHandler(throwable -> logger.error("Probably an undeliverableException: {}", throwable));

        Realm.init(this);

        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .name("xmdb")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .migration(new MigrationHelper())
                .build());
    }
}
