package by.borisevich.gibdd.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CarOwner")
public class CarOwner {

    @Id
    @Column(name = "owner_id", insertable = false, updatable = false)
    @GeneratedValue
    private int id;

    private String name;
    private String surname;
    private String secondName;
    private String address;
    private String dateOfBirth;
    private String sex;
    private String driverCertificateNumber;

    @OneToMany(mappedBy = "carOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> car;

    public CarOwner(String name, String surname, String secondName, String address,
                    String dateOfBirth, String sex, String driverCertificateNumber) {
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
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

    public String getSex() {
        return sex;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "CarOwner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondName='" + secondName + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", sex='" + sex + '\'' +
                ", driverCertificateNumber='" + driverCertificateNumber + '\'' +
                '}';
    }
}
