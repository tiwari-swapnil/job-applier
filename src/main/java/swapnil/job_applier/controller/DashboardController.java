package swapnil.job_applier.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swapnil.job_applier.repository.ContactRepository;
import swapnil.job_applier.repository.EmailTemplateRepository;
import swapnil.job_applier.repository.SentEmailRepository;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final ContactRepository contactRepository;
    private final EmailTemplateRepository templateRepository;
    private final SentEmailRepository sentEmailRepository;

    public DashboardController(
            ContactRepository contactRepository,
            EmailTemplateRepository templateRepository,
            SentEmailRepository sentEmailRepository) {

        this.contactRepository = contactRepository;
        this.templateRepository = templateRepository;
        this.sentEmailRepository = sentEmailRepository;
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {

        long totalContacts = contactRepository.count();
        long totalTemplates = templateRepository.count();
        long emailsSent = sentEmailRepository.count();
        long notSent = totalContacts - emailsSent;

        if (notSent < 0) {
            notSent = 0;
        }

        return Map.of(
                "totalContacts", totalContacts,
                "totalTemplates", totalTemplates,
                "emailsSent", emailsSent,
                "notSent", notSent
        );
    }
}
