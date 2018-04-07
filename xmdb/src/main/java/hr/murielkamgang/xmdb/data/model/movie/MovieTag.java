package hr.murielkamgang.xmdb.data.model.movie;

import io.realm.RealmObject;

/**
 * Created by muriel on 4/7/18.
 */
public class MovieTag extends RealmObject {

    public static final String TAG_FAVORITE = "TAG_FAVORITE";
    public static final String TAG_POPULAR = "TAG_POPULAR";
    public static final String TAG_TOP_RATED = "TAG_TOP_RATED";

    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieTag movieTag = (MovieTag) o;

        return tag != null ? tag.equals(movieTag.tag) : movieTag.tag == null;
    }

    @Override
    public int hashCode() {
        return tag != null ? tag.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MovieTag{" +
                "tag='" + tag + '\'' +
                '}';
    }
}
