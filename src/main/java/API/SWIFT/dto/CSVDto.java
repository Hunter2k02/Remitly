package API.SWIFT.dto;

public class CSVDTO {
    private final String countryISO2;
    private final String swiftCode;
    private final String codeType;
    private final String bankName;
    private final String address;
    private final String townName;
    private final String countryName;
    private final String timeZone;
    private final boolean isHeadquarter;

    public CSVDTO(String countryISO2, String swiftCode, String codeType, String bankName, String address, String townName, String countryName, String timeZone, boolean isHeadquarter) {
        this.countryISO2 = countryISO2;
        this.swiftCode = swiftCode;
        this.codeType = codeType;
        this.bankName = bankName;
        this.address = address;
        this.townName = townName;
        this.countryName = countryName;
        this.timeZone = timeZone;
        this.isHeadquarter = isHeadquarter;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public String getCodeType() {
        return codeType;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAddress() {
        return address;
    }

    public String getTownName() {
        return townName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public boolean isHeadquarter() {
        return isHeadquarter;
    }

    @Override
    public String toString() {
        return "CSVDto{" +
                "countryISO2='" + countryISO2 + '\'' +
                ", swiftCode='" + swiftCode + '\'' +
                ", codeType='" + codeType + '\'' +
                ", bankName='" + bankName + '\'' +
                ", address='" + address + '\'' +
                ", townName='" + townName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", isHeadquarter=" + isHeadquarter +
                '}';
    }
}
