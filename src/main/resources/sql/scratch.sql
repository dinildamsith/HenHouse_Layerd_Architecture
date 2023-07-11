CREATE DATABASE hen_shop;

USE hen_shop;

CREATE TABLE Employee (
                          Employee_id VARCHAR(16) PRIMARY KEY,
                          Employee_name VARCHAR(20),
                          Date_of_birth DATE,
                          Mobile_num VARCHAR(10) UNIQUE,
                          Job_title ENUM('Admin'),
                          Gmail VARCHAR(30) UNIQUE
);

CREATE TABLE User (
                      User_id VARCHAR(30) UNIQUE,
                      Password VARCHAR(30)
);

CREATE TABLE Customer (
                          Customer_id VARCHAR(6) PRIMARY KEY,
                          Customer_name VARCHAR(20) NOT NULL,
                          Customer_contact VARCHAR(13) UNIQUE,
                          Customer_gmail VARCHAR(35) UNIQUE,
                          Customer_address VARCHAR(20) NOT NULL
);

CREATE TABLE Order (
                       Order_id VARCHAR(6) PRIMARY KEY,
                       Order_date DATE
                           Customer_id VARCHAR (6)

                           CONSTRAINT FOREIGN KEY (Customer_id) REFERENCES Customer(Customer_id)ON CASCADE DELETE ON CASCADE UPDATE;
);

CREATE TABLE ShopOrder (
                           Qty INT,
                           Total DECIMAL(6,2)
);

CREATE TABLE Item (
                      Item_code VARCHAR(6) PRIMARY KEY,
                      Description VARCHAR(26) NOT NULL,
                      Selling_unit_price DECIMAL(6,2) NOT NULL,
                      Qty_on_hand INT NOT NULL
);

CREATE TABLE Supply (
                        Expire_date DATE,
                        Manufacture_date DATE,
                        Unit_selling_price DECIMAL(6,2),
                        Qty INT,
                        Item_code VARCHAR (6),
                        Suppliers_id VARCHAR (6)

                            CONSTRAINT FOREIGN KEY (Item_code) REFERENCES Item(Item_code)ON CASCADE DELETE ON CASCADE UPDATE;
CONSTRAINT FOREIGN KEY (Suppliers_id) REFERENCES Suppliers(Supplier_id) ON CASCADE DELETE ON CASCADE UPDATE ;
);

CREATE TABLE Suppliers (
                           Supplier_id VARCHAR(6) PRIMARY KEY,
                           Supplier_name VARCHAR(19) NOT NULL,
                           Description VARCHAR(26),
                           Email VARCHAR(35) UNIQUE,
                           Contact VARCHAR(13) UNIQUE,
                           Address VARCHAR(30)
);

CREATE TABLE Attendance(
                           Attendance_id VARCHAR(6)PRIMARY KEY,
                           Attendance_date DATE,
                           Attendance_no_hours INT,
                           Employee_id VARCHAR(16)

                               CONSTRAINT FOREIGN KEY (Employee_id) REFERENCES Employee(Employee_id)ON CASCADE DELETE ON CASCADE UPDATE ;

);