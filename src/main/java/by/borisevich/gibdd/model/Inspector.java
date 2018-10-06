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

    private String name;
    private String surname;
    private String secondName;
    private String job;
    private String rank;

    @OneToMany(mappedBy = "inspector", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarInspection> carInspectionsList;

    public Inspector(String name, String surname, String secondName, String job, String rank) {
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.job = job;
        this.rank = rank;
    }

    public Inspector() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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
