package lk.ijse.henHouse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomerDTO {
    private String customerId;
    private String customerName;
    private String contact;
    private String address;
    private String gmail;
}
