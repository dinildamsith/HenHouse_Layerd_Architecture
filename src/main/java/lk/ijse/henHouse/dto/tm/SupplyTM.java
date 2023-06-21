package lk.ijse.henHouse.dto.tm;

import java.time.LocalDate;

public class SupplyTM {

    private String StockId;
    private String ItemCode;
    private String supplyId;
    private int qty;
    private double unitPrice;
    private LocalDate expiringDate;
    private LocalDate manufacturingDate;

    public SupplyTM(String stockId, String itemCode, String supplyId, int qty, double unitPrice, LocalDate expiringDate, LocalDate manufacturingDate) {
        StockId = stockId;
        ItemCode = itemCode;
        this.supplyId = supplyId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.expiringDate = expiringDate;
        this.manufacturingDate = manufacturingDate;
    }

    public SupplyTM() {
    }

    public SupplyTM(String stockId, String itemCode, String supplyId, int qty, double unitPrice, LocalDate expiringDate) {
        StockId = stockId;
        ItemCode = itemCode;
        this.supplyId = supplyId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.expiringDate = expiringDate;
    }

    public SupplyTM(String stockId) {
        StockId = stockId;
    }

    public SupplyTM(String value, String value1, LocalDate parse, LocalDate parse1, double unitPrice, int parseInt) {
        ItemCode=value;
        supplyId=value1;
        manufacturingDate=parse;
        expiringDate=parse1;
        this.unitPrice=unitPrice;
        qty=parseInt;

    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getStockId() {
        return StockId;
    }

    public void setStockId(String stockId) {
        StockId = stockId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(LocalDate expiringDate) {
        this.expiringDate = expiringDate;
    }
}
