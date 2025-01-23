package API.SWIFT.dto;


public class DeleteRequestDTO {
    private String swiftCode;
    private String bankName;
    private String countryISO2;

    public DeleteRequestDTO(String swiftCode, String bankName, String countryISO2) {
        this.swiftCode = swiftCode;
        this.bankName = bankName;
        this.countryISO2 = countryISO2.toUpperCase();
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
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
}
