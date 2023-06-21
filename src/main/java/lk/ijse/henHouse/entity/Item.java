package lk.ijse.henHouse.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String itemCode;
    private String description;
    private Double unitPrice;
    private Integer qty;

    public Item(String itemCode, String description, Double unitPrice, Integer qty) {
        this.itemCode = itemCode;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }
}
