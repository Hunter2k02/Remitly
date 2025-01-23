package API.SWIFT;

import API.SWIFT.dto.DeleteRequestDTO;
import API.SWIFT.dto.SwiftCodeRequestDTO;
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

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwiftControllerTests {

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
    void unitTestGetSwiftCodeDetails() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/TESTTEST123"))
                .andExpect(jsonPath("$.countryISO2", equalTo("PL")))
                .andExpect(jsonPath("$.countryName", equalTo("POLAND")))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void integrationTestGetSwiftCodesByCountry() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/country/PL"))
                .andExpect(status().isOk());
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
