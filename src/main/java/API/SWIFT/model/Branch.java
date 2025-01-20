package API.SWIFT.model;

import jakarta.persistence.*;

@Entity
public class Branch {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String address;
    private String bankName;
    private String countryISO2;
    private String swiftCode;
    @ManyToOne
    private Headquarter headquarter;
}
