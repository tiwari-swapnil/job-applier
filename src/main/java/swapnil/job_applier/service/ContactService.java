package swapnil.job_applier.service;


import org.springframework.stereotype.Service;
import swapnil.job_applier.entity.Contact;
import swapnil.job_applier.repository.ContactRepository;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public Contact createContact(Contact contact) {
        if (contact.getStatus() == null || contact.getStatus().isBlank()) {
            contact.setStatus("NOT_SENT");
        }

        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, Contact updatedContact) {
        Contact contact = getContactById(id);

        contact.setName(updatedContact.getName());
        contact.setEmail(updatedContact.getEmail());
        contact.setCompany(updatedContact.getCompany());
        contact.setPosition(updatedContact.getPosition());
        contact.setType(updatedContact.getType());
        contact.setStatus(updatedContact.getStatus());

        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}