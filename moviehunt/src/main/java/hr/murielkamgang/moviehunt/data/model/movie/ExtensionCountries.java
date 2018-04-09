package hr.murielkamgang.moviehunt.data.model.movie;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by muriel on 3/4/18.
 */

public class ExtensionCountries extends RealmObject {

    @SerializedName("iso_3166_1")
    private String code;
    private int id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtensionCountries that = (ExtensionCountries) o;

        if (id != that.id) return false;
        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "ExtensionCountries{" +
                "code='" + code + '\'' +
                ", id=" + id +
                '}';
    }


}
