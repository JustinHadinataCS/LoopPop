package com.LoopPop.LoopPop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoopPopController {
    @GetMapping("/index")
    public String index() {
        return "index"; // This will look for src/main/resources/templates/index.html
    }
}
