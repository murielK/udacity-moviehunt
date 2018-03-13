package hr.murielkamgang.xmdb.data.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by muriel on 25/2/18.
 */

/**
 * Application Migration Helper, this class will be used to perform
 * realm migration when data schema will be changed from previous schema version
 * <p>
 * <p>Note that failing to provide migration updateProfile on schema change will render the current data base useless</p>
 * <p>
 * {@inheritDoc}
 */
public class MigrationHelper implements RealmMigration {

    private static final Logger logger = LoggerFactory.getLogger(MigrationHelper.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        logger.debug("migrate oldVersion: {} newVersion {}", oldVersion, newVersion);
    }
}
