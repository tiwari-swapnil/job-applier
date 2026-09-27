package swapnil.job_applier.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swapnil.job_applier.entity.Contact;
import swapnil.job_applier.entity.EmailTemplate;
import swapnil.job_applier.entity.SentEmail;
import swapnil.job_applier.repository.ContactRepository;
import swapnil.job_applier.repository.EmailTemplateRepository;
import swapnil.job_applier.repository.SentEmailRepository;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final ContactRepository contactRepository;
    private final EmailTemplateRepository templateRepository;
    private final SentEmailRepository sentEmailRepository;

    public EmailService(
            JavaMailSender mailSender,
            ContactRepository contactRepository,
            EmailTemplateRepository templateRepository,
            SentEmailRepository sentEmailRepository) {

        this.mailSender = mailSender;
        this.contactRepository = contactRepository;
        this.templateRepository = templateRepository;
        this.sentEmailRepository = sentEmailRepository;
    }

    public void sendEmail(
            Long contactId,
            Long templateId,
            MultipartFile file) throws Exception {

        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() ->
                        new RuntimeException("Contact not found"));

        EmailTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() ->
                        new RuntimeException("Template not found"));

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Resume file is required");
        }

        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null ||
                !originalFileName.toLowerCase().endsWith(".pdf")) {

            throw new RuntimeException("Only PDF files are allowed");
        }

        String subject = personalize(
                template.getSubject(),
                contact
        );

        String body = personalize(
                template.getBody(),
                contact
        );

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                true
        );

        helper.setTo(contact.getEmail());
        helper.setSubject(subject);
        helper.setText(body);

        ByteArrayResource resource =
                new ByteArrayResource(file.getBytes());

        helper.addAttachment(
                originalFileName,
                resource
        );

        mailSender.send(message);

        contact.setStatus("SENT");
        contactRepository.save(contact);


        // sent email task
        SentEmail sentEmail = new SentEmail();

        sentEmail.setContactId(contact.getId());

        sentEmail.setRecipientEmail(
                contact.getEmail()
        );

        sentEmail.setSubject(subject);

        sentEmail.setStatus("SENT");

        sentEmail.setSentAt(
                LocalDateTime.now()
        );

        sentEmailRepository.save(sentEmail);
    }

    private String personalize(
            String text,
            Contact contact) {

        return text
                .replace("{Name}", safe(contact.getName()))
                .replace("{Company}", safe(contact.getCompany()))
                .replace("{Position}", safe(contact.getPosition()));
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }
}