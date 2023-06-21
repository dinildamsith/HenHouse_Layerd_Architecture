package lk.ijse.henHouse.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SupplierTm {
    private String id;
    private String name;
    private String desc;
    private String address;
    private String email;
    private String contact;
}
