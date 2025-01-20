package API.SWIFT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/swift-codes")
@Controller
public class SwiftController {

    @GetMapping("/")
    public String index() {
        return "Hello";
    }
}
