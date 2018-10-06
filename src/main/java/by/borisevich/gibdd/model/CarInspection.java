package by.borisevich.gibdd.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "CarInspection")
public class CarInspection {

    @Id
    @Column(name = "inspection_id")
    @GeneratedValue
    private int id;

    private String dateOfInspection;
    private String resultOfInspection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspector_id")
    private Inspector inspector;

    public CarInspection(String dateOfInspection, String resultOfInspection) {
        this.dateOfInspection = dateOfInspection;
        this.resultOfInspection = resultOfInspection;
    }

    public CarInspection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfInspection() {
        return dateOfInspection;
    }

    public void setDateOfInspection(String dateOfInspection) {
        this.dateOfInspection = dateOfInspection;
    }

    public String getResultOfInspection() {
        return resultOfInspection;
    }

    public void setResultOfInspection(String resultOfInspection) {
        this.resultOfInspection = resultOfInspection;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }
}
