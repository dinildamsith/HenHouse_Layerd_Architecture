package lk.ijse.henHouse.bo;

import lk.ijse.henHouse.bo.SuperBO;
import lk.ijse.henHouse.bo.custom.impl.*;


public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,CUSTOMER_UNIQ,EMPLOYEE,EMPLOYEE_UNIQ,ITEM,ORDER_PLACE,USER,SUPPLIER,SUPPLIER_DETAILS,SUPPLIER_UNIQ,SUPPLY,SUPPLY_UNIQ,ORDER
    }

    //Object creation logic for BO objects
    public Object getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case CUSTOMER_UNIQ:
                return  new CustomerUniqMethodBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case EMPLOYEE_UNIQ:
                return new EmployeeUniqMethodBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER_PLACE:
                return new OrderPlaceBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SUPPLIER_DETAILS:
                return new SupplierDetailsBOImpl();
            case SUPPLIER_UNIQ:
                return new SupplierUniqMethodBOImpl();
            case SUPPLY:
                return new SupplyBOImpl();
            case  SUPPLY_UNIQ:
                return new SupplyUniqMethodBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }

}
