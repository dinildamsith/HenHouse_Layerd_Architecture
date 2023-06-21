package lk.ijse.henHouse.dto;

import java.time.LocalDate;

public class SupplyDTO {

    String item_code;
    String Supplier_Id;
    LocalDate manufactureDate;
    LocalDate expireDate;
    double unitSellingPrice;
    private String ItemCode;
    int qty;
     String StockId;
     String supplyId;



    public SupplyDTO() {
    }

    public SupplyDTO(String item_code, String supplier_Id, LocalDate manufactureDate, LocalDate expireDate, double unitSellingPrice, int qty) {
        this.item_code = item_code;
        Supplier_Id = supplier_Id;
        this.manufactureDate = manufactureDate;
        this.expireDate = expireDate;
        this.unitSellingPrice = unitSellingPrice;
        this.qty = qty;
    }


    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getSupplier_Id() {
        return Supplier_Id;
    }

    public void setSupplier_Id(String supplier_Id) {
        Supplier_Id = supplier_Id;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public double getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public void setUnitSellingPrice(double unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
