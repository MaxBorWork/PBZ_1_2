package by.borisevich.gibdd.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @Column(name = "car_id")
    @GeneratedValue
    private int id;

    private String stateNumber;
    private String motorNumber;
    private String color;
    private String model;
    private String techPassportNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private CarOwner carOwner;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarInspection> carInspections;
//
//    public Car(String stateNumber, String motorNumber, String color, String model,
//               String techPassportNumber, CarOwner carOwner, List<CarInspection> carInspections) {
//        this.stateNumber = stateNumber;
//        this.motorNumber = motorNumber;
//        this.color = color;
//        this.model = model;
//        this.techPassportNumber = techPassportNumber;
//        this.carOwner = carOwner;
//        this.carInspections = carInspections;
//    }

    public Car(String stateNumber, String motorNumber, String color, String model, String techPassportNumber) {
        this.stateNumber = stateNumber;
        this.motorNumber = motorNumber;
        this.color = color;
        this.model = model;
        this.techPassportNumber = techPassportNumber;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getMotorNumber() {
        return motorNumber;
    }

    public void setMotorNumber(String motorNumber) {
        this.motorNumber = motorNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTechPassportNumber() {
        return techPassportNumber;
    }

    public void setTechPassportNumber(String techPassportNumber) {
        this.techPassportNumber = techPassportNumber;
    }

    public CarOwner getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(CarOwner carOwner) {
        this.carOwner = carOwner;
    }

    public List<CarInspection> getCarInspections() {
        return carInspections;
    }

    public void setCarInspections(List<CarInspection> carInspections) {
        this.carInspections = carInspections;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", stateNumber='" + stateNumber + '\'' +
                ", motorNumber='" + motorNumber + '\'' +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", techPassportNumber='" + techPassportNumber + '\'' +
                '}';
    }
}
