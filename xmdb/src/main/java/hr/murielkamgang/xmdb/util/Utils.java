package hr.murielkamgang.xmdb.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.AdapterView;
import hr.murielkamgang.xmdb.components.base.BaseView;
import io.realm.OrderedCollectionChangeSet;
import io.realm.RealmObject;

/**
 * Created by muriel on 3/4/18.
 */

public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static boolean isConnected(BaseView baseView) {
        final Context context = baseView == null ? null : baseView.getContext();
        if (context == null) {
            return false;
        }
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static boolean isConnected(Context context) {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static <T extends RealmObject> void notifyAdapterView(List<T> collection, OrderedCollectionChangeSet changeSet, AdapterView view) {
        if (view == null || collection == null || changeSet == null) {
            return;
        }
        final OrderedCollectionChangeSet.Range[] changeRanges = changeSet.getChangeRanges();
        final OrderedCollectionChangeSet.Range[] insertionRanges = changeSet.getInsertionRanges();
        final OrderedCollectionChangeSet.Range[] deletionRanges = changeSet.getDeletionRanges();

        logger.debug("changeRanges: length {}", changeRanges.length);
        logger.debug("insertionRanges: length {}", insertionRanges.length);
        logger.debug("deletionRanges: length {}", deletionRanges.length);

        if (insertionRanges.length > 0 && deletionRanges.length > 0) {
            int maxDeletion = 0;
            int maxInsertion = 0;

            for (final OrderedCollectionChangeSet.Range r : insertionRanges) {
                maxInsertion += r.length;
            }

            for (final OrderedCollectionChangeSet.Range r : deletionRanges) {
                maxDeletion += r.length;
            }

            if (maxDeletion > maxInsertion) {
                final int dRange = maxDeletion - maxInsertion;
                view.onUpdateRemoved(collection.size(), dRange);
                view.onUpdateChanged(0, collection.size());
            } else if (maxDeletion < maxInsertion) {
                final int iRange = maxInsertion - maxDeletion;
                view.onUpdateInserted(collection.size() - iRange, iRange);
                view.onUpdateChanged(0, collection.size());
            } else {
                view.notifyDataSetChanged();
            }


        } else {
            for (final OrderedCollectionChangeSet.Range r : changeRanges) {
                view.onUpdateChanged(r.startIndex, r.length);
            }

            for (final OrderedCollectionChangeSet.Range r : insertionRanges) {
                view.onUpdateInserted(r.startIndex, r.length);
            }

            for (final OrderedCollectionChangeSet.Range r : deletionRanges) {
                view.onUpdateRemoved(r.startIndex, r.length);
            }
        }
    }

    public static String makeMoviePosterUrlFor(Context context, String path) {
        return getImgBaseUrl(context) + context.getResources().getString(R.string.moive_poster_size) + path;
    }

    public static String makeMovieBackDropUrlFor(Context context, String path) {
        return getImgBaseUrl(context) + context.getResources().getString(R.string.moive_backdrop_size) + path;
    }

    public static String makePeoplePosterUrlFor(Context context, String path) {
        return makeMoviePosterUrlFor(context, path);//for now use the same setting as the moviePoster
    }

    private static String getImgBaseUrl(Context context) {
        return context.getResources().getString(R.string.base_poster_url);
    }
}
