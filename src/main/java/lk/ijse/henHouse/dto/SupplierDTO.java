package lk.ijse.henHouse.dto;

public class SupplierDTO {
    String supName;
    String companyName;

    public SupplierDTO() {
    }

    public SupplierDTO(String supName, String companyName) {
        this.supName = supName;
        this.companyName = companyName;
    }


    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
