package swapnil.job_applier.service;

import org.springframework.stereotype.Service;
import swapnil.job_applier.entity.SentEmail;
import swapnil.job_applier.repository.SentEmailRepository;

import java.util.List;

@Service
public class SentEmailService {

    private final SentEmailRepository sentEmailRepository;

    public SentEmailService(
            SentEmailRepository sentEmailRepository) {

        this.sentEmailRepository = sentEmailRepository;
    }

    public List<SentEmail> getAllSentEmails() {

        return sentEmailRepository
                .findAllByOrderBySentAtDesc();
    }
}
