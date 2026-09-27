package swapnil.job_applier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swapnil.job_applier.entity.SentEmail;

import java.util.List;

@Repository
public interface SentEmailRepository extends JpaRepository<SentEmail, Long> {
    List<SentEmail> findAllByOrderBySentAtDesc();
}
