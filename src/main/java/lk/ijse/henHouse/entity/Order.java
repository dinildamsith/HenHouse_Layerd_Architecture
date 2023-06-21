package lk.ijse.henHouse.entity;

import com.jfoenix.controls.JFXButton;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private String itemCode;
    private String description;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    private JFXButton btn;

    public Order(String itemCode, String description, Integer qty, Double unitPrice, Double total, JFXButton btn) {
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
        this.btn = btn;
    }
}
