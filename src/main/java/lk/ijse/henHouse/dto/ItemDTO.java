package lk.ijse.henHouse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemDTO {

    private String itemCode;
    private String description;
    private Double unitPrice;
    private Integer qty;

    public ItemDTO(String description, Integer qty) {
        this.description = description;
        this.qty = qty;
    }

}
