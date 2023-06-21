package lk.ijse.henHouse.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    String id;
    String name;
    String address;
    String contact;
    String job;
    String gmail;
    double salary;


    public Employee(String id, String name, String contact, String job, String gmail, double salary, String address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.job = job;
        this.gmail = gmail;
        this.salary = salary;
        this.address = address;
    }
}
