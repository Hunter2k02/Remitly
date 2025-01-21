package API.SWIFT.model;

import jakarta.persistence.*;

@Entity
public class Bank {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String address;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "country_iso2")
    private String countryISO2;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "swift_code")
    private String swiftCode;
    @Column(name = "is_headquarter")
    private boolean isHeadquarter;

    public Bank(String address, String bankName, String countryISO2, String countryName, String swiftCode, boolean isHeadquarter) {
        this.address = address;
        this.bankName = bankName;
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
        this.swiftCode = swiftCode;
        this.isHeadquarter = isHeadquarter;
    }

    public Bank() {

    }

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
        this.countryISO2 = countryISO2;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public boolean isHeadquarter() {
        return isHeadquarter;
    }

    public void setHeadquarter(boolean headquarter) {
        isHeadquarter = headquarter;
    }
}