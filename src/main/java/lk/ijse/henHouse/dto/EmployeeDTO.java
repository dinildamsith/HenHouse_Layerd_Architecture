package lk.ijse.henHouse.dto;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {
    String id;
    String name;
    String address;
    String contact;
    String job;
    String gmail;
    double salary;


    public EmployeeDTO(String id, String name, String contact, String job, String gmail, double salary, String address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.job = job;
        this.gmail = gmail;
        this.salary = salary;
        this.address = address;
    }



}
