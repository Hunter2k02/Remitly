package API.SWIFT.controller;

import API.SWIFT.service.DataImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/swift-codes")
@RestController
public class SwiftController {

    @GetMapping("/")
    public String index() {
        return "Hello";
    }
}
