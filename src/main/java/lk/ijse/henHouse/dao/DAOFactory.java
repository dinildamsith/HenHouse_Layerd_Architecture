package lk.ijse.henHouse.dao;

import lk.ijse.henHouse.dao.custom.impl.*;


public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
      CUSTOMER ,CUSTOMER_UNIQ ,EMPLOYEE ,EMPLOYEE_UNIQ ,ITEM ,ORDER ,ORDER_DETAILS ,SUPPLIER ,SUPPLIER_UNIQ ,SUPPLY ,SUPPLY_UNIQ ,USER
    }

    public Object getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case CUSTOMER_UNIQ:
                return new  CustomerUniqMethodDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case EMPLOYEE_UNIQ:
                return new EmployeeUniqMethodDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderImpl();
            case ORDER_DETAILS:
                return new OrderDetailsImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIER_UNIQ:
                return new SupplierUniqMethodDAOImpl();
            case SUPPLY:
                return new SupplyDAOImpl();
            case SUPPLY_UNIQ:
                return new SupplyUniqMethodDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }


}
