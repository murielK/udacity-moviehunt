package hr.murielkamgang.xmdb.data.model.movie;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by muriel on 3/4/18.
 */

public class Movie extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("belongs_to_collection")
    private Collection belongsToCollection;
    private int budget;
    private RealmList<Extension> genres;
    private String homepage;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    private double popularity;
    private String overview;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("production_companies")
    private RealmList<Extension> productionCompanmies;
    @SerializedName("production_countries")
    private RealmList<ExtensionCountries> productionCountries;
    @SerializedName("spoken_languages")
    private RealmList<ExtensionLanguage> spokenLanguage;
    @SerializedName("release_date")
    private String releaseDate;
    private int revenue;
    private int runtime;
    private String status;
    private String tagline;
    private boolean video;
    @SerializedName("vote_average")
    private double votesAverage;
    @SerializedName("vote_count")
    private int voteCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Collection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(Collection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public RealmList<Extension> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<Extension> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public RealmList<Extension> getProductionCompanmies() {
        return productionCompanmies;
    }

    public void setProductionCompanmies(RealmList<Extension> productionCompanmies) {
        this.productionCompanmies = productionCompanmies;
    }

    public RealmList<ExtensionCountries> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(RealmList<ExtensionCountries> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public RealmList<ExtensionLanguage> getSpokenLanguage() {
        return spokenLanguage;
    }

    public void setSpokenLanguage(RealmList<ExtensionLanguage> spokenLanguage) {
        this.spokenLanguage = spokenLanguage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVotesAverage() {
        return votesAverage;
    }

    public void setVotesAverage(double votesAverage) {
        this.votesAverage = votesAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (adult != movie.adult) return false;
        if (budget != movie.budget) return false;
        if (Double.compare(movie.popularity, popularity) != 0) return false;
        if (revenue != movie.revenue) return false;
        if (runtime != movie.runtime) return false;
        if (video != movie.video) return false;
        if (Double.compare(movie.votesAverage, votesAverage) != 0) return false;
        if (voteCount != movie.voteCount) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (backdropPath != null ? !backdropPath.equals(movie.backdropPath) : movie.backdropPath != null)
            return false;
        if (belongsToCollection != null ? !belongsToCollection.equals(movie.belongsToCollection) : movie.belongsToCollection != null)
            return false;
        if (genres != null ? !genres.equals(movie.genres) : movie.genres != null) return false;
        if (homepage != null ? !homepage.equals(movie.homepage) : movie.homepage != null)
            return false;
        if (imdbId != null ? !imdbId.equals(movie.imdbId) : movie.imdbId != null) return false;
        if (originalLanguage != null ? !originalLanguage.equals(movie.originalLanguage) : movie.originalLanguage != null)
            return false;
        if (originalTitle != null ? !originalTitle.equals(movie.originalTitle) : movie.originalTitle != null)
            return false;
        if (overview != null ? !overview.equals(movie.overview) : movie.overview != null)
            return false;
        if (posterPath != null ? !posterPath.equals(movie.posterPath) : movie.posterPath != null)
            return false;
        if (productionCompanmies != null ? !productionCompanmies.equals(movie.productionCompanmies) : movie.productionCompanmies != null)
            return false;
        if (productionCountries != null ? !productionCountries.equals(movie.productionCountries) : movie.productionCountries != null)
            return false;
        if (spokenLanguage != null ? !spokenLanguage.equals(movie.spokenLanguage) : movie.spokenLanguage != null)
            return false;
        if (releaseDate != null ? !releaseDate.equals(movie.releaseDate) : movie.releaseDate != null)
            return false;
        if (status != null ? !status.equals(movie.status) : movie.status != null) return false;
        return tagline != null ? tagline.equals(movie.tagline) : movie.tagline == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (adult ? 1 : 0);
        result = 31 * result + (backdropPath != null ? backdropPath.hashCode() : 0);
        result = 31 * result + (belongsToCollection != null ? belongsToCollection.hashCode() : 0);
        result = 31 * result + budget;
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
        result = 31 * result + (imdbId != null ? imdbId.hashCode() : 0);
        result = 31 * result + (originalLanguage != null ? originalLanguage.hashCode() : 0);
        result = 31 * result + (originalTitle != null ? originalTitle.hashCode() : 0);
        temp = Double.doubleToLongBits(popularity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
        result = 31 * result + (productionCompanmies != null ? productionCompanmies.hashCode() : 0);
        result = 31 * result + (productionCountries != null ? productionCountries.hashCode() : 0);
        result = 31 * result + (spokenLanguage != null ? spokenLanguage.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + revenue;
        result = 31 * result + runtime;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (tagline != null ? tagline.hashCode() : 0);
        result = 31 * result + (video ? 1 : 0);
        temp = Double.doubleToLongBits(votesAverage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + voteCount;
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", adult=" + adult +
                ", backdropPath='" + backdropPath + '\'' +
                ", belongsToCollection=" + belongsToCollection +
                ", budget=" + budget +
                ", genres=" + genres +
                ", homepage='" + homepage + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", popularity=" + popularity +
                ", overview='" + overview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", productionCompanmies=" + productionCompanmies +
                ", productionCountries=" + productionCountries +
                ", spokenLanguage=" + spokenLanguage +
                ", releaseDate='" + releaseDate + '\'' +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", video=" + video +
                ", votesAverage=" + votesAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}
