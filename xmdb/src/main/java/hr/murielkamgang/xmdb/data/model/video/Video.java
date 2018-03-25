package hr.murielkamgang.xmdb.data.model.video;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by muriel on 3/24/18.
 */
public class Video extends RealmObject {

    @PrimaryKey
    private String id;
    private String key;
    private String name;
    private String site;
    private int size;
    private String type;
    private int movieId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        if (size != video.size) return false;
        if (movieId != video.movieId) return false;
        if (id != null ? !id.equals(video.id) : video.id != null) return false;
        if (key != null ? !key.equals(video.key) : video.key != null) return false;
        if (name != null ? !name.equals(video.name) : video.name != null) return false;
        if (site != null ? !site.equals(video.site) : video.site != null) return false;
        return type != null ? type.equals(video.type) : video.type == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (site != null ? site.hashCode() : 0);
        result = 31 * result + size;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + movieId;
        return result;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", movieId=" + movieId +
                '}';
    }
}
