package API.SWIFT.controller;

import API.SWIFT.dto.DeleteRequestDTO;
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
    @GetMapping("/country/{countryISO2code}")
    public ResponseEntity<?> country(@PathVariable("countryISO2code") String countryISO2) {
        return bankService.getBanksByCountryISO2code(countryISO2.toUpperCase());
    }
    @PostMapping("/")
    public ResponseEntity<?> addBank(@RequestBody SwiftCodeRequestDTO dto) {
        return bankService.addBank(dto);
    }
    @DeleteMapping("/")
    public ResponseEntity<?> deleteBank(@RequestBody DeleteRequestDTO dto) {
        return bankService.deleteBank(dto);
    }
}
