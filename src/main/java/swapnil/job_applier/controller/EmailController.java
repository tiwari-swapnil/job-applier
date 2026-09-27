package swapnil.job_applier.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swapnil.job_applier.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestParam Long contactId,
            @RequestParam Long templateId,
            @RequestParam("file") MultipartFile file) {

        try {

            emailService.sendEmail(
                    contactId,
                    templateId,
                    file
            );

            return ResponseEntity.ok(
                    "Email sent successfully with resume!"
            );

        } catch (Exception e) {

            return ResponseEntity.internalServerError()
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}