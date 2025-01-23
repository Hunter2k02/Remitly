package API.SWIFT.controller;

import API.SWIFT.dto.DeleteRequestDTO;
import API.SWIFT.dto.MessageResponse;
import API.SWIFT.dto.SwiftCodeRequestDTO;
import API.SWIFT.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/swift-codes")
@RestController
public class SwiftController {
    private final BankService bankService;

    public SwiftController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{swift-code}")
    public ResponseEntity<?> swiftCode(@PathVariable("swift-code") String swiftCode) {
        return bankService.getBankBySwiftCode(swiftCode);
    }
    @GetMapping("/country/{countryISO2}")
    public ResponseEntity<?> country(@PathVariable("countryISO2") String countryISO2) {
        return bankService.getBanksByCountryISO2code(countryISO2.toUpperCase());
    }
    @PostMapping("/")
    public ResponseEntity<MessageResponse> addBank(@RequestBody SwiftCodeRequestDTO dto) {
        return bankService.addBank(dto);
    }
    @DeleteMapping("/")
    public ResponseEntity<MessageResponse> deleteBank(@RequestBody DeleteRequestDTO dto) {
        return bankService.deleteBank(dto);
    }
}
