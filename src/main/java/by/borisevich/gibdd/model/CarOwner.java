package by.borisevich.gibdd.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "CarOwner")
public class CarOwner {

    @Id
    @Column(name = "owner_id", insertable = false, updatable = false)
    @GeneratedValue
    private int id;

    private String fullName;
    private String address;
    private String dateOfBirth;
    private String sex;
    private String driverCertificateNumber;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "carOwner")
    private Car car;

    public CarOwner(String fullName, String address, String dateOfBirth, String sex, String driverCertificateNumber) {
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.driverCertificateNumber = driverCertificateNumber;
    }

    public CarOwner() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String isSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDriverCertificateNumber() {
        return driverCertificateNumber;
    }

    public void setDriverCertificateNumber(String driverCertificateNumber) {
        this.driverCertificateNumber = driverCertificateNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
