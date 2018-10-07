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
    private int inspector_id;
    private int car_id;

    private String dateOfInspection;
    private String resultOfInspection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", insertable=false, updatable=false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspector_id", insertable=false, updatable=false)
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

    public int getInspector_id() {
        return inspector_id;
    }

    public void setInspector_id(int inspector_id) {
        this.inspector_id = inspector_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }
}
