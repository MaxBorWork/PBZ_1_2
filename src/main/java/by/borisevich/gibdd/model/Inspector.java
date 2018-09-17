package by.borisevich.gibdd.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Inspector")
public class Inspector {

    @Id
    @Column(name = "inspector_id")
    @GeneratedValue
    private int id;

    private String fullName;
    private String job;
    private String rank;

    @OneToMany(mappedBy = "inspector", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarInspection> carInspectionsList;

    public Inspector(int id, String fullName, String job, String rank, List<CarInspection> carInspectionsList) {
        this.id = id;
        this.fullName = fullName;
        this.job = job;
        this.rank = rank;
        this.carInspectionsList = carInspectionsList;
    }

    public Inspector() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<CarInspection> getCarInspectionsList() {
        return carInspectionsList;
    }

    public void setCarInspectionsList(List<CarInspection> carInspectionsList) {
        this.carInspectionsList = carInspectionsList;
    }
}
