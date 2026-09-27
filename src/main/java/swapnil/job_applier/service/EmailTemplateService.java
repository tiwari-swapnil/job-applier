package swapnil.job_applier.service;

import org.springframework.stereotype.Service;
import swapnil.job_applier.entity.EmailTemplate;
import swapnil.job_applier.repository.EmailTemplateRepository;

import java.util.List;

@Service
public class EmailTemplateService {

    private final EmailTemplateRepository templateRepository;

    public EmailTemplateService(EmailTemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public List<EmailTemplate> getAllTemplates() {
        return templateRepository.findAll();
    }

    public EmailTemplate getTemplateById(Long id) {
        return templateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }

    public EmailTemplate createTemplate(EmailTemplate template) {
        return templateRepository.save(template);
    }

    public EmailTemplate updateTemplate(
            Long id,
            EmailTemplate updatedTemplate) {

        EmailTemplate template = getTemplateById(id);

        template.setName(updatedTemplate.getName());
        template.setSubject(updatedTemplate.getSubject());
        template.setBody(updatedTemplate.getBody());

        return templateRepository.save(template);
    }

    public void deleteTemplate(Long id) {
        templateRepository.deleteById(id);
    }
}
