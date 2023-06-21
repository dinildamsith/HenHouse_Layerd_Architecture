package lk.ijse.henHouse.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderTM {
    private String itemCode;
    private String description;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    private JFXButton btn;
}

