package swapnil.job_applier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/templates")
    public String templates() {
        return "templates";
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        return "send-email";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }
}
