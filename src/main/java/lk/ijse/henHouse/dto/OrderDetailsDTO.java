package lk.ijse.henHouse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderDetailsDTO {
    private String orderId;
    private String itemCode;
    private Integer qty;
    private Double total;
    private Double unitPrice;

    public OrderDetailsDTO(Integer qty, String itemCode) {
        this.qty=qty;
        this.itemCode=itemCode;
    }
}
