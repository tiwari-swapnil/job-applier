package swapnil.job_applier.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swapnil.job_applier.entity.EmailTemplate;
import swapnil.job_applier.service.EmailTemplateService;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class EmailTemplateController {

    private final EmailTemplateService templateService;

    public EmailTemplateController(EmailTemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping
    public List<EmailTemplate> getAllTemplates() {
        return templateService.getAllTemplates();
    }

    @GetMapping("/{id}")
    public EmailTemplate getTemplateById(@PathVariable Long id) {
        return templateService.getTemplateById(id);
    }

    @PostMapping
    public ResponseEntity<EmailTemplate> createTemplate(
            @Valid @RequestBody EmailTemplate template) {

        return ResponseEntity.ok(
                templateService.createTemplate(template)
        );
    }

    @PutMapping("/{id}")
    public EmailTemplate updateTemplate(
            @PathVariable Long id,
            @Valid @RequestBody EmailTemplate template) {

        return templateService.updateTemplate(id, template);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTemplate(@PathVariable Long id) {

        templateService.deleteTemplate(id);

        return ResponseEntity.ok("Template deleted successfully");
    }
}