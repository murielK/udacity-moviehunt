package hr.murielkamgang.moviehunt.data.model.image;

import com.google.gson.annotations.SerializedName;

import hr.murielkamgang.moviehunt.data.model.MovieId;
import io.realm.RealmObject;

/**
 * Created by muriel on 3/25/18.
 */
public class Image extends RealmObject implements MovieId {

    @SerializedName("aspect_ratio")
    private double aspectRatio;
    @SerializedName("file_path")
    private String filePath;
    private int height;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    private int voteCount;
    private int width;
    private int movieId;
    private String type;

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (Double.compare(image.aspectRatio, aspectRatio) != 0) return false;
        if (height != image.height) return false;
        if (Double.compare(image.voteAverage, voteAverage) != 0) return false;
        if (voteCount != image.voteCount) return false;
        if (width != image.width) return false;
        if (movieId != image.movieId) return false;
        if (filePath != null ? !filePath.equals(image.filePath) : image.filePath != null)
            return false;
        return type != null ? type.equals(image.type) : image.type == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(aspectRatio);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + height;
        temp = Double.doubleToLongBits(voteAverage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + voteCount;
        result = 31 * result + width;
        result = 31 * result + movieId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "aspectRatio=" + aspectRatio +
                ", filePath='" + filePath + '\'' +
                ", height=" + height +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", width=" + width +
                ", movieId=" + movieId +
                ", type='" + type + '\'' +
                '}';
    }
}
