package hr.murielkamgang.moviehunt.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.AdapterView;
import hr.murielkamgang.moviehunt.components.base.BaseView;
import hr.murielkamgang.moviehunt.data.model.MovieId;
import hr.murielkamgang.moviehunt.data.model.credits.People;
import hr.murielkamgang.moviehunt.data.model.image.Image;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.data.model.video.Video;
import io.realm.OrderedCollectionChangeSet;
import io.realm.RealmObject;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by muriel on 3/4/18.
 */

public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    private Utils() {
    }

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

    public static String makeYoutubeThumbnailFor(Context context, String key) {
        return context.getResources().getString(R.string.youtube_thumbnail_url, key);
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

    public static void watchYoutubeVideo(Context context, String key) {
        final Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
        if (youtubeIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(youtubeIntent);
        } else {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + key)));
        }
    }

    public static void loadMoviePoster(ImageView target, Picasso picasso, Movie movie) {
        loadImageFit(target, picasso, makeMoviePosterUrlFor(target.getContext(), movie.getPosterPath()));
    }

    public static void loadMovieBackDrop(ImageView target, Picasso picasso, Movie movie) {
        loadImageFitCenterCrop(target, picasso, makeMovieBackDropUrlFor(target.getContext(), movie.getPosterPath()));
    }

    public static void loadMovieBackDrop(ImageView target, Picasso picasso, Image image) {
        loadImageFitCenterCrop(target, picasso, makeMovieBackDropUrlFor(target.getContext(), image.getFilePath()));
    }

    public static void loadVideo(ImageView target, Picasso picasso, Video video) {
        loadImageFitCenterCrop(target, picasso, makeYoutubeThumbnailFor(target.getContext(), video.getKey()));
    }

    public static void loadPeople(ImageView target, Picasso picasso, People people) {
        loadImageFitCenterCrop(target, picasso, makePeoplePosterUrlFor(target.getContext(), people.getProfilePath()));
    }

    public static void loadImageFit(ImageView target, Picasso picasso, String path) {
        picasso
                .load(path)
                .fit()
                .transform(new RoundedCornersTransformation(target.getContext().getResources().getDimensionPixelSize(R.dimen.default_rounded_radius), 0))
                .into(target);
    }

    public static void loadImageFitCenterCrop(ImageView target, Picasso picasso, String path) {
        picasso
                .load(path)
                .fit()
                .centerCrop()
                .transform(new RoundedCornersTransformation(target.getContext().getResources().getDimensionPixelSize(R.dimen.default_rounded_radius), 0))
                .into(target);
    }

    public static void updateIdFor(List<? extends MovieId> list, int movieId) {
        if (list != null) {
            for (final MovieId m : list) {
                m.setMovieId(movieId);
            }
        }
    }
}
