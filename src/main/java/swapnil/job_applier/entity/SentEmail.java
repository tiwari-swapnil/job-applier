package swapnil.job_applier.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "sent_emails")
@Data
public class SentEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contactId;

    private String recipientEmail;

    private String subject;

    private String status;

    private LocalDateTime sentAt;


}