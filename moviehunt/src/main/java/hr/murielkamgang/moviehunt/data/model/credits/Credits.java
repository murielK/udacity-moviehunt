package hr.murielkamgang.moviehunt.data.model.credits;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by muriel on 3/24/18.
 */
public class Credits extends RealmObject {

    private int id;
    @SerializedName("cast")
    private RealmList<Cast> casts;
    @SerializedName("crew")
    private RealmList<Crew> crews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Cast> getCasts() {
        return casts;
    }

    public void setCasts(RealmList<Cast> casts) {
        this.casts = casts;
    }

    public RealmList<Crew> getCrews() {
        return crews;
    }

    public void setCrews(RealmList<Crew> crews) {
        this.crews = crews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credits credits = (Credits) o;

        if (id != credits.id) return false;
        if (casts != null ? !casts.equals(credits.casts) : credits.casts != null) return false;
        return crews != null ? crews.equals(credits.crews) : credits.crews == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (casts != null ? casts.hashCode() : 0);
        result = 31 * result + (crews != null ? crews.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Credits{" +
                "id=" + id +
                ", casts=" + casts +
                ", crews=" + crews +
                '}';
    }
}
