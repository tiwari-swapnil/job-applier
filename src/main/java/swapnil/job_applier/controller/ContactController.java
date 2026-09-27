package swapnil.job_applier.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swapnil.job_applier.entity.Contact;
import swapnil.job_applier.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(
            @Valid @RequestBody Contact contact) {

        return ResponseEntity.ok(contactService.createContact(contact));
    }

    @PutMapping("/{id}")
    public Contact updateContact(
            @PathVariable Long id,
            @Valid @RequestBody Contact contact) {

        return contactService.updateContact(id, contact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {

        contactService.deleteContact(id);

        return ResponseEntity.ok("Contact deleted successfully");
    }
}
