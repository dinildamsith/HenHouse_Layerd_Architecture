package lk.ijse.henHouse.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Supplier {
    private String id;
    private String name;
    private String desc;
    private String address;
    private String email;
    private String contact;

    public Supplier(String id, String name, String desc, String address, String email, String contact) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.address = address;
        this.email = email;
        this.contact = contact;
    }
}
