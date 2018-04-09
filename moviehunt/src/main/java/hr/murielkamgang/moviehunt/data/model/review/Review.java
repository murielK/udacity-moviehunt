package hr.murielkamgang.moviehunt.data.model.review;

import hr.murielkamgang.moviehunt.data.model.MovieId;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by muriel on 3/25/18.
 */
public class Review extends RealmObject implements MovieId {

    private String author;
    private String content;
    @PrimaryKey
    private String id;
    private String url;
    private int movieId;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

        Review review = (Review) o;

        if (movieId != review.movieId) return false;
        if (author != null ? !author.equals(review.author) : review.author != null) return false;
        if (content != null ? !content.equals(review.content) : review.content != null)
            return false;
        if (id != null ? !id.equals(review.id) : review.id != null) return false;
        return url != null ? url.equals(review.url) : review.url == null;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + movieId;
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", movieId=" + movieId +
                '}';
    }

}
