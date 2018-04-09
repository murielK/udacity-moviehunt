package hr.murielkamgang.moviehunt.data.model.movie;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by muriel on 3/4/18.
 */

public class ExtensionLanguage extends RealmObject {

    @SerializedName("iso_639_1")
    private String code;
    private String name;
    private int id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        ExtensionLanguage that = (ExtensionLanguage) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "ExtensionLanguage{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
