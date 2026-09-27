package swapnil.job_applier.controller;


import org.springframework.web.bind.annotation.*;
import swapnil.job_applier.entity.SentEmail;
import swapnil.job_applier.service.SentEmailService;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class SentEmailController {

    private final SentEmailService sentEmailService;

    public SentEmailController(
            SentEmailService sentEmailService) {

        this.sentEmailService = sentEmailService;
    }

    @GetMapping
    public List<SentEmail> getHistory() {

        return sentEmailService.getAllSentEmails();
    }
}
