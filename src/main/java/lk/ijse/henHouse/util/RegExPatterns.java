package lk.ijse.henHouse.util;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class RegExPatterns {
    private static final Pattern namePattern = Pattern.compile("^[a-zA-Z '.-]{4,}$");
    private static final Pattern emailPattern = Pattern.compile("^([a-z]{0,22})(\\d{0,20})([@gmail.com]{10,10})?$");
    private static final Pattern doublePattern = Pattern.compile("^[0-9]+\\.?[0-9]*$");
    private static final Pattern intPattern = Pattern.compile("^[1-9][0-9]?$|^100$");
    private static final Pattern customerId = Pattern.compile("^C-[0-9]{2,}$");
    private static final Pattern employeeId = Pattern.compile("^E-[0-9]{2,}$");
    private static final Pattern supplierId = Pattern.compile("^S-[0-9]{2,}$");
    private static final Pattern contactPattern = Pattern.compile("^0[0-9]{9}$");
    private static final Pattern passwordPattern = Pattern.compile("^[0-9a-zA-Z]{8}$");
    private static final Pattern datePattern = Pattern.compile("^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|1\\d|2[0-9]|3[01])$");

    public static Pattern getDatePattern() {
        return datePattern;
    }

    public static Pattern getCustomerId() {
        return customerId;
    }

    public static Pattern getNamePattern() {
        return namePattern;
    }

    public static Pattern getDoublePattern() {
        return doublePattern;
    }

    public static Pattern getEmailPattern() {
        return emailPattern;
    }

    public static Pattern getIntPattern() {
        return intPattern;
    }

    public static Pattern getContactPattern() {
        return contactPattern;
    }

    public static Pattern getEmployeeId() {
        return employeeId;
    }

    public static Pattern getSupplierId() {
        return supplierId;
    }

    public static Pattern getPasswordPattern() {
        return passwordPattern;
    }
}
