package hr.murielkamgang.xmdb.data.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by muriel on 25/2/18.
 */

/**
 * RealmHelper class, to help with basic realmObject queries
 * <p>Note that realm Object are not shared between thread.</p>
 */
public class RealmHelper {

    private static final boolean ENABLE_LOG = true;
    private static final Logger logger = LoggerFactory.getLogger(RealmHelper.class);

    private RealmHelper() {
    }

    /**
     * Method helper for queryEqual to equal to the input filters
     *
     * @param eClass  class of the object
     * @param field   the field to compare.
     * @param filters value to to compare with.
     * @param <E>     the Type of the object.
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> queryEqualTo(final Class<E> eClass, final String field, final String... filters) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return queryEqualTo(realm, eClass, field, filters);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }

    }

    /**
     * Method helper for queryEqual to equal to the input filters
     *
     * @param eClass  class of the object
     * @param field   the field to compare.
     * @param filters value to to compare with.
     * @param <E>     the Type of the object.
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> queryEqualTo(final Class<E> eClass, final String field, final boolean... filters) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return queryEqualTo(realm, eClass, field, filters);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }

    }

    /**
     * Method helper for queryEqual to equal to the input filters
     *
     * @param eClass  class of the object
     * @param field   the field to compare.
     * @param filters value to to compare with.
     * @param <E>     the Type of the object.
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> queryEqualTo(final Class<E> eClass, final String field, final int... filters) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return queryEqualTo(realm, eClass, field, filters);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }

    }

    /**
     * Method helper for queryEqual to equal to the input filters
     *
     * @param eClass  class of the object
     * @param field   the field to compare.
     * @param filters value to to compare with.
     * @param <E>     the Type of the object.
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> queryEqualTo(final Class<E> eClass, final String field, final long... filters) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return queryEqualTo(realm, eClass, field, filters);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }

    }

    /**
     * Method helper for queryEqual to equal to the input filters
     *
     * @param realm   the realm instance
     * @param eClass  class of the object
     * @param field   the field to compare.
     * @param filters value to to compare with.
     * @param <E>     the Type of the object.
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> queryEqualTo(final Realm realm, final Class<E> eClass, final String field, final long... filters) {
        try {
            final RealmQuery<E> realmQuery = realm.where(eClass);
            int i = 0;
            for (final long filter : filters) {
                if (i != 0)
                    realmQuery.or();
                realmQuery.equalTo(field, filter);
                i++;
            }
            return getRealmResults(realmQuery);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        }

    }

    /**
     * Method helper for queryEqual to equal to the input filters
     *
     * @param realm   the realm instance
     * @param eClass  class of the object
     * @param field   the field to compare.
     * @param filters value to to compare with.
     * @param <E>     the Type of the object.
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> queryEqualTo(final Realm realm, final Class<E> eClass, final String field, final boolean... filters) {
        try {
            final RealmQuery<E> realmQuery = realm.where(eClass);
            int i = 0;
            for (final boolean filter : filters) {
                if (i != 0)
                    realmQuery.or();
                realmQuery.equalTo(field, filter);
                i++;
            }
            return getRealmResults(realmQuery);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        }

    }

    /**
     * Method helper for queryEqual to equal to the input filters
     *
     * @param realm   the realm instance
     * @param eClass  class of the object
     * @param field   the field to compare.
     * @param filters value to to compare with.
     * @param <E>     the Type of the object.
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> queryEqualTo(final Realm realm, final Class<E> eClass, final String field, final String... filters) {
        try {
            final RealmQuery<E> realmQuery = realm.where(eClass);
            int i = 0;
            for (final String filter : filters) {
                if (i != 0)
                    realmQuery.or();
                realmQuery.equalTo(field, filter);
                i++;
            }
            return getRealmResults(realmQuery);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        }

    }

    /**
     * Method helper for queryEqual to equal to the input filters
     *
     * @param realm   the realm instance
     * @param eClass  class of the object
     * @param field   the field to compare.
     * @param filters value to to compare with.
     * @param <E>     the Type of the object.
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> queryEqualTo(final Realm realm, final Class<E> eClass, final String field, final int... filters) {
        try {
            final RealmQuery<E> realmQuery = realm.where(eClass);
            int i = 0;
            for (final int filter : filters) {
                if (i != 0)
                    realmQuery.or();
                realmQuery.equalTo(field, filter);
                i++;
            }
            return getRealmResults(realmQuery);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        }
    }

    /**
     * Method helper for queryEqual to equal to the input filters
     *
     * @param eClass  class of the object
     * @param field   the field to compare.
     * @param filters value to to compare with.
     * @param <E>     the Type of the object.
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> queryContains(final Class<E> eClass, final String field, final String... filters) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return queryContains(realm, eClass, field, filters);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }

    }

    /**
     * Method helper for queryEqual to equal to the input filters
     *
     * @param realm   the realm instance
     * @param eClass  class of the object
     * @param field   the field to compare.
     * @param filters value to to compare with.
     * @param <E>     the Type of the object.
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> queryContains(final Realm realm, final Class<E> eClass, final String field, final String... filters) {
        try {
            final RealmQuery<E> realmQuery = realm.where(eClass);
            int i = 0;
            for (final String filter : filters) {
                if (i != 0)
                    realmQuery.or();
                realmQuery.contains(field, filter, Case.INSENSITIVE);
                i++;
            }
            return getRealmResults(realmQuery);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        }

    }

    /**
     * Method helper to get results form RealmQuery
     *
     * @param realmQuery the realmQuery instance
     * @param <E>        the Type of the object
     * @return return null if nothing is found/if and error occur or a list of found object
     */
    public static <E extends RealmObject> RealmResults<E> getRealmResults(final RealmQuery<E> realmQuery) {
        try {
            return realmQuery.findAll();
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        }
    }

    /**
     * Method helper to perform find all object on a specific table
     *
     * @param realm  the realm instance
     * @param eClass class of the realmObject
     * @param <E>    the Type of the expected result object
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults<E> findAll(final Realm realm, final Class<E> eClass) {
        try {
            return realm.where(eClass).findAll();
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        }
    }

    /**
     * Method helper to get all entries from a specific table
     *
     * @param eClass class of the object
     * @param <E>    the Type of the object
     * @return return null if nothing is found or a list of found object.
     */
    public static <E extends RealmObject> RealmResults findAll(final Class<E> eClass) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return findAll(realm, eClass);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return null;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    /**
     * Method helper to perform write operation
     *
     * @param objects the objects to be insert
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean realmWrite(final List<E> objects) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return realmWrite(realm, objects);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return false;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    /**
     * Method helper to perform write operation
     *
     * @param realm   the realm instance
     * @param objects the objects to be insert
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean realmWrite(final Realm realm, final List<E> objects) {
        try {
            realm.beginTransaction();
            realm.insertOrUpdate(objects);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            realm.cancelTransaction();
            return false;
        }

    }

    /**
     * Method helper to perform write operation
     *
     * @param realm  the realm instance
     * @param object the object to be insert
     * @param <E>    the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean realmWrite(final Realm realm, final E object) {
        try {
            realm.beginTransaction();
            realm.insertOrUpdate(object);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            realm.cancelTransaction();
            return false;
        }

    }

    /**
     * Method helper to perform write operation
     *
     * @param object the object to be insert
     * @param <E>    the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean realmWrite(final E object) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return realmWrite(realm, object);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return false;
        } finally {
            if (realm != null)
                realm.close();
        }

    }

    /**
     * Method helper to delete entry from realm
     *
     * @param eClass  class of the object
     * @param field   the field to compare
     * @param filters value to compare with
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean delete(final Class<E> eClass, final String field, final String... filters) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return delete(realm, eClass, field, filters);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return false;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    /**
     * Method helper to delete entry from realm
     *
     * @param eClass  class of the object
     * @param field   the field to compare
     * @param filters value to compare with
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean delete(final Class<E> eClass, final String field, final long... filters) {
        final Realm realm = Realm.getDefaultInstance();
        try {
            return delete(realm, eClass, field, filters);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return false;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    /**
     * Method helper to delete entry from realm
     *
     * @param eClass  class of the object
     * @param field   the field to compare
     * @param filters value to compare with
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean delete(final Class<E> eClass, final String field, final boolean... filters) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return delete(realm, eClass, field, filters);
        } catch (Exception e) {
            logger.debug("", e);
            return false;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    /**
     * Method helper to delete entry from realm
     *
     * @param eClass  class of the object
     * @param field   the field to compare
     * @param filters value to compare with
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean delete(final Class<E> eClass, final String field, final int... filters) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return delete(realm, eClass, field, filters);
        } catch (Exception e) {
            logger.debug("", e);
            return false;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    /**
     * Method helper to delete entry from realm
     *
     * @param realm   the realm instance
     * @param eClass  class of the object
     * @param field   the field to compare
     * @param filters value to compare with
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean delete(final Realm realm, final Class<E> eClass, final String field, final int... filters) {
        try {
            final RealmResults<E> realmResults = queryEqualTo(realm, eClass, field, filters);
            realm.beginTransaction();
            boolean success = realmResults.deleteAllFromRealm();
            realm.commitTransaction();
            return success;
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            realm.cancelTransaction();
            return false;
        }
    }

    /**
     * Method helper to delete entry from realm
     *
     * @param realm   the realm instance
     * @param eClass  class of the object
     * @param field   the field to compare
     * @param filters value to compare with
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean delete(final Realm realm, final Class<E> eClass, final String field, final String... filters) {
        try {
            final RealmResults<E> realmResults = queryEqualTo(realm, eClass, field, filters);
            realm.beginTransaction();
            boolean success = realmResults.deleteAllFromRealm();
            realm.commitTransaction();
            return success;
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            realm.cancelTransaction();
            return false;
        }
    }

    /**
     * Method helper to delete entry from realm
     *
     * @param realm   the realm instance
     * @param eClass  class of the object
     * @param field   the field to compare
     * @param filters value to compare with
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean delete(final Realm realm, final Class<E> eClass, final String field, final boolean... filters) {
        try {
            final RealmResults<E> realmResults = queryEqualTo(realm, eClass, field, filters);
            realm.beginTransaction();
            boolean success = realmResults.deleteAllFromRealm();
            realm.commitTransaction();
            return success;
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            realm.cancelTransaction();
            return false;
        }
    }

    /**
     * Method helper to delete entry from realm
     *
     * @param realm   the realm instance
     * @param eClass  class of the object
     * @param field   the field to compare
     * @param filters value to compare with
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean delete(final Realm realm, final Class<E> eClass, final String field, final long... filters) {
        try {
            final RealmResults<E> realmResults = queryEqualTo(realm, eClass, field, filters);
            realm.beginTransaction();
            boolean success = realmResults.deleteAllFromRealm();
            realm.commitTransaction();
            return success;
        } catch (
                Exception e)

        {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            realm.cancelTransaction();
            return false;
        }

    }

    /**
     * Method helper to delete all table entries
     *
     * @param <E> the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean deleteAll(final Class<E> eClass) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return deleteAll(realm, eClass);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return false;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    /**
     * Method helper to delete all table entries
     *
     * @param realm the realm instance
     * @param <E>   the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean deleteAll(final Realm realm, final Class<E> eClass) {
        try {
            final RealmResults<E> realmResults = findAll(realm, eClass);
            realm.beginTransaction();
            boolean success = realmResults.deleteAllFromRealm();
            realm.commitTransaction();
            return success;
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            realm.cancelTransaction();
            return false;
        }
    }

    /**
     * Method helper to perform a clean insert operation, previous table entries will be removed first
     *
     * @param objects the objects to be insert
     * @param eClass  class of the object
     * @param <E>     the Type of the object
     * @return return false if transaction not successful
     */
    public static <E extends RealmObject> boolean realmCleanWrite(final List<E> objects, Class<E> eClass) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            return realmWCleanWrite(realm, objects, eClass);
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            return false;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    /**
     * Method helper to perform a clean insert operation, previous table entries will be removed first
     *
     * @param realm   the realm instance
     * @param objects the objects to be insert
     * @param eClass  class of the object
     * @param <E>     the Type of the object to be written
     * @return return null if nothing is found/if and error occur or a list of found object
     */
    public static <E extends RealmObject> boolean realmWCleanWrite(final Realm realm, final List<E> objects, Class<E> eClass) {
        try {
            realm.beginTransaction();
            realm.delete(eClass);
            realm.insertOrUpdate(objects);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            if (ENABLE_LOG) {
                logger.debug("", e);
            }
            realm.cancelTransaction();
            return false;
        }

    }

}
