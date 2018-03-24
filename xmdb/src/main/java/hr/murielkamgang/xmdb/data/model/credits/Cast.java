package hr.murielkamgang.xmdb.data.model.credits;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by muriel on 3/24/18.
 */
public class Cast extends RealmObject implements People {

    @SerializedName("cast_id")
    private int castId;
    private String character;
    private int gender;
    private int id;
    private String name;
    private int order;
    @SerializedName("profile_path")
    private String profilePath;

    public int getCastId() {
        return castId;
    }

    public void setCastId(int castId) {
        this.castId = castId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cast crew = (Cast) o;

        if (castId != crew.castId) return false;
        if (gender != crew.gender) return false;
        if (id != crew.id) return false;
        if (order != crew.order) return false;
        if (character != null ? !character.equals(crew.character) : crew.character != null)
            return false;
        if (name != null ? !name.equals(crew.name) : crew.name != null) return false;
        return profilePath != null ? profilePath.equals(crew.profilePath) : crew.profilePath == null;
    }

    @Override
    public int hashCode() {
        int result = castId;
        result = 31 * result + (character != null ? character.hashCode() : 0);
        result = 31 * result + gender;
        result = 31 * result + id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + order;
        result = 31 * result + (profilePath != null ? profilePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "castId=" + castId +
                ", character='" + character + '\'' +
                ", gender=" + gender +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", profilePath='" + profilePath + '\'' +
                '}';
    }
}
