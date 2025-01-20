package API.SWIFT.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Headquarter {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String address;
    private String bankName;
    private String countryISO2;
    private String countryName;
    private String swiftCode;

    public Headquarter(String address, String bankName, String countryISO2, String countryName, String swiftCode) {
        this.address = address;
        this.bankName = bankName;
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
        this.swiftCode = swiftCode;
    }

    public Headquarter() {

    }
}
