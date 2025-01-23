package API.SWIFT;

import API.SWIFT.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwiftTests {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void integrationTestAddSwiftCode() throws Exception {
        SwiftCodeRequestDTO swiftCodeRequest = new SwiftCodeRequestDTO();
        swiftCodeRequest.setAddress("Cracow");
        swiftCodeRequest.setBankName("Bank");
        swiftCodeRequest.setSwiftCode("TESTTEST123");
        swiftCodeRequest.setCountryISO2("pl");
        swiftCodeRequest.setHeadquarter(true);
        swiftCodeRequest.setCountryName("poland");

        mockMvc.perform(post("/v1/swift-codes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(swiftCodeRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("SWIFT code saved successfully"));

    }
    @Test
    @Order(2)
    void integrationTestAddSwiftCodeConflict() throws Exception {
        SwiftCodeRequestDTO swiftCodeRequest = new SwiftCodeRequestDTO();
        swiftCodeRequest.setAddress("Cracow");
        swiftCodeRequest.setBankName("Bank");
        swiftCodeRequest.setSwiftCode("TESTTEST123");
        swiftCodeRequest.setCountryISO2("pl");
        swiftCodeRequest.setHeadquarter(true);
        swiftCodeRequest.setCountryName("poland");

        mockMvc.perform(post("/v1/swift-codes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(swiftCodeRequest)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("SWIFT code already exists"));
    }

    @Test
    @Order(3)
    void integrationTestGetSwiftCodeDetails() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/TESTTEST123"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void integrationTestGetSwiftCodesByCountry() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/country/PL"))
                .andExpect(status().isOk());
    }
    @Test
    @Order(4)
    void unitTestCheckDtoCountryNameAndCountryISO2ToUppercase() throws Exception{
        BranchResponseDTO branchResponseDTO = new BranchResponseDTO();
        branchResponseDTO.setCountryName("test");
        branchResponseDTO.setCountryISO2("pl");
        assertEquals("TEST", branchResponseDTO.getCountryName(), "CountryName should be uppercase");
        assertEquals("PL", branchResponseDTO.getCountryISO2(), "CountryISO2 should be uppercase");

        CountrySwiftCodesResponseDTO swiftCodesResponseDTO = new CountrySwiftCodesResponseDTO();
        swiftCodesResponseDTO.setCountryName("test");
        swiftCodesResponseDTO.setCountryISO2("pl");
        assertEquals("TEST", swiftCodesResponseDTO.getCountryName(),  "CountryName should be uppercase");
        assertEquals("PL", swiftCodesResponseDTO.getCountryISO2(), "CountryISO2 should be uppercase");

        HeadquarterResponseDTO headquarterResponseDTO = new HeadquarterResponseDTO();
        headquarterResponseDTO.setCountryName("test");
        headquarterResponseDTO.setCountryISO2("pl");
        assertEquals("TEST", headquarterResponseDTO.getCountryName(),  "CountryName should be uppercase");
        assertEquals("PL", headquarterResponseDTO.getCountryISO2(), "CountryISO2 should be uppercase");

        SwiftCodeRequestDTO swiftCodeRequestDTO = new SwiftCodeRequestDTO();
        swiftCodeRequestDTO.setCountryName("test");
        swiftCodeRequestDTO.setCountryISO2("pl");
        assertEquals("TEST", swiftCodeRequestDTO.getCountryName(),  "CountryName should be uppercase");
        assertEquals("PL", swiftCodeRequestDTO.getCountryISO2(), "CountryISO2 should be uppercase");



    }

    @Test
    @Order(5)
    void integrationTestDeleteSwiftCodeNotFound() throws Exception {
        DeleteRequestDTO deleteRequestDTO = new DeleteRequestDTO(
                "AAAAAAAAAAA",
                "Bank",
                "au"
        );
        mockMvc.perform(delete("/v1/swift-codes/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteRequestDTO)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("SWIFT code not found"))
        ;

    }
    @Test
    @Order(6)
    void integrationTestDeleteSwiftCodeBadRequest() throws Exception {
        DeleteRequestDTO deleteRequestDTO = new DeleteRequestDTO(
                "TESTTEST123",
                "Bank",
                "au"
        );
        mockMvc.perform(delete("/v1/swift-codes/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteRequestDTO)))
                .andExpect(jsonPath("$.message").value("One or more parameters do not match"));

    }
    @Test
    @Order(7)
    void integrationTestDeleteSwiftCode() throws Exception {
        DeleteRequestDTO deleteRequestDTO = new DeleteRequestDTO(
                "TESTTEST123",
                          "Bank",
                        "pl"
        );
        mockMvc.perform(delete("/v1/swift-codes/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deleteRequestDTO)))
                .andExpect(status().isOk()
                );

    }



}
