package hr.murielkamgang.xmdb.data.model.credits;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by muriel on 3/24/18.
 */
public class Crew extends RealmObject implements People {

    @SerializedName("credit_id")
    private String creditId;
    private String department;
    private int gender;
    private int id;
    private String job;
    private String name;
    @SerializedName("profile_path")
    private String profilePath;

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        Crew crew = (Crew) o;

        if (creditId != crew.creditId) return false;
        if (gender != crew.gender) return false;
        if (id != crew.id) return false;
        if (department != null ? !department.equals(crew.department) : crew.department != null)
            return false;
        if (job != null ? !job.equals(crew.job) : crew.job != null) return false;
        if (name != null ? !name.equals(crew.name) : crew.name != null) return false;
        return profilePath != null ? profilePath.equals(crew.profilePath) : crew.profilePath == null;
    }

    @Override
    public int hashCode() {
        int result = creditId != null ? creditId.hashCode() : 0;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + gender;
        result = 31 * result + id;
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (profilePath != null ? profilePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "creditId=" + creditId +
                ", department='" + department + '\'' +
                ", gender=" + gender +
                ", id=" + id +
                ", job='" + job + '\'' +
                ", name='" + name + '\'' +
                ", profilePath='" + profilePath + '\'' +
                '}';
    }
}
