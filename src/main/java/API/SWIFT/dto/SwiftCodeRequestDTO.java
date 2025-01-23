package API.SWIFT.dto;

public class SwiftCodeRequestDTO {
    private String address;
    private String bankName;
    private String countryISO2;
    private String countryName;
    private Boolean isHeadquarter;
    private String swiftCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2.toUpperCase();
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName.toUpperCase();
    }

    public Boolean getHeadquarter() {
        return isHeadquarter;
    }

    public void setHeadquarter(Boolean headquarter) {
        isHeadquarter = headquarter;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String fieldsNotEmpty() {
        if(address == null || address.isEmpty() ) {
            return "address";
        }
        else if(bankName == null || bankName.isEmpty() ) {
            return "bankName";
        }
        else if(countryISO2 == null || countryISO2.isEmpty() ) {
            return "countryISO2";
        }
        else if(swiftCode == null || swiftCode.isEmpty() ) {
            return "swiftCode";
        }
        else if(isHeadquarter == null) {
            return "isHeadquarter";
        }
        else{
            return "";
        }
    }
}
