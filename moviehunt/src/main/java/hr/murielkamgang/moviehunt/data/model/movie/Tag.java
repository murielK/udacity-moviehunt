package hr.murielkamgang.moviehunt.data.model.movie;

import io.realm.RealmObject;

/**
 * Created by muriel on 4/7/18.
 */
public class Tag extends RealmObject {

    private String tag;

    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

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

        Tag tag = (Tag) o;

        return this.tag != null ? this.tag.equals(tag.tag) : tag.tag == null;
    }

    @Override
    public int hashCode() {
        return tag != null ? tag.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag='" + tag + '\'' +
                '}';
    }
}
