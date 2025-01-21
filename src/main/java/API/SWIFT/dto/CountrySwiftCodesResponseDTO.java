package API.SWIFT.dto;

import java.util.List;

public class CountrySwiftCodesResponseDTO {
    private String countryISO2;
    private String countryName;
    private List<SwiftCodeResponseDTO> swiftCodes;

    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setSwiftCodes(List<SwiftCodeResponseDTO> swiftCodes) {
        this.swiftCodes = swiftCodes;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public String getCountryName() {
        return countryName;
    }

    public List<SwiftCodeResponseDTO> getSwiftCodes() {
        return swiftCodes;
    }
}
