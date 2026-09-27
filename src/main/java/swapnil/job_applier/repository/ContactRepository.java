package swapnil.job_applier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swapnil.job_applier.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
