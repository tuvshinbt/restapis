/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.service.impl;

import bt.pre.prob.solstice.challenge.entity.Address;
import bt.pre.prob.solstice.challenge.entity.Contact;
import bt.pre.prob.solstice.challenge.entity.Person;
import bt.pre.prob.solstice.challenge.exception.AddressNotFoundException;
import bt.pre.prob.solstice.challenge.exception.ContactNotFoundException;
import bt.pre.prob.solstice.challenge.exception.InBadRequestException;
import bt.pre.prob.solstice.challenge.model.ContactModel;
import bt.pre.prob.solstice.challenge.repository.AddressRepository;
import bt.pre.prob.solstice.challenge.repository.ContactRepository;
import bt.pre.prob.solstice.challenge.repository.PersonRepository;
import bt.pre.prob.solstice.challenge.service.ContactCriteria;
import bt.pre.prob.solstice.challenge.service.ContactService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tuvshuu
 */
@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger logger = LogManager.getLogger(ContactServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Value("${file.upload.path}")
    private String FILE_UPLOAD_PATH;

    @Override
    public Integer create(ContactModel contactModel) {
        logger.info("ContactService create");
        try {
            // createFile
            contactModel.setProfileImage(createFile(contactModel.getProfileImageFile(), contactModel.getFullName()));
            // save data
            Person person = personRepository.save(contactModel.person());
            return person.getContact().getId();
        } catch (ConstraintViolationException e) {
            throw new DataIntegrityViolationException("Duplicated contact!");
        }
    }

    @Override
    public ContactModel retrieve(Integer id) {
        logger.info("ContactService retrieve");
        return new ContactModel(getPersonByContactId(id));
    }

    public Person getPersonByContactId(Integer id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            return contact.get().getPerson();
        } else {
            throw new ContactNotFoundException("Contact NOT found!");
        }
    }

    @Override
    public void update(Integer id, ContactModel contactModel) {
        logger.info("ContactService update");
        // create new file
        contactModel.setProfileImage(createFile(contactModel.getProfileImageFile(), contactModel.getFullName()));
        // save data
        Person newPerson = contactModel.person();
        Person oldPerson = getPersonByContactId(id);
        // delete old file
        deleteFile(oldPerson.getContact().getProfileImage());
        newPerson.getAddress().setId(oldPerson.getAddress().getId());
        newPerson.getCompany().setId(oldPerson.getCompany().getId());
        newPerson.getContact().setId(oldPerson.getContact().getId());
        newPerson.setId(oldPerson.getId());
        personRepository.save(newPerson);
    }

    @Override
    public void delete(Integer id) {
        logger.info("ContactService delete");
        deleteFile(getPersonByContactId(id).getContact().getProfileImage());
        personRepository.deleteById(id);
    }

    @Override
    public ContactModel search(ContactCriteria contactCriteria, String value) {
        logger.info("ContactService search");
        Contact contact = null;
        if (ContactCriteria.EMAIL == contactCriteria) {
            contact = contactRepository.findByEmail(value);
        } else if (ContactCriteria.PHONE_NUMBER == contactCriteria) {

            contact = contactRepository.findByPhoneNumber(Long.parseLong(value));
        } else {
            throw new InBadRequestException("Unknown filter!");
        }
        if (contact != null) {
            return new ContactModel(contact.getPerson());
        } else {
            throw new ContactNotFoundException("Contact NOT found!");
        }
    }

    @Override
    public List<ContactModel> retrieveAll(String state, String city) {
        logger.info("ContactService retrieveAll");
        List<Person> persons;
        if ((state == null || state.isEmpty()) && (city == null || city.isEmpty())) {
            persons = personRepository.findAll();
        } else {
            List<Address> addresses;
            if (city == null || city.isEmpty()) {
                addresses = addressRepository.findByState(state);
            } else {
                addresses = addressRepository.findByStateAndCity(state, city);
            }
            if (addresses == null) {
                throw new AddressNotFoundException("Address NOT found!");
            }
            persons = personRepository.findByAddressIn(addresses);
            if (persons.isEmpty()) {
                throw new ContactNotFoundException("Contact NOT found");
            }
        }
        List<ContactModel> contacts = new ArrayList<>();
        persons.forEach(p -> contacts.add(new ContactModel(p)));
        return contacts;
    }

    private String createFile(MultipartFile file, String fileName) {
        try {
            if (FILE_UPLOAD_PATH != null) {
                Path rootPath = Paths.get(FILE_UPLOAD_PATH);
                if (!Files.exists(rootPath)) {
                    Files.createDirectories(rootPath);
                }
            }
            String fileFullName = (FILE_UPLOAD_PATH != null ? FILE_UPLOAD_PATH : "") + System.currentTimeMillis() + "_" + fileName.replace(" ", "_") + "_" + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(fileFullName);
            Files.write(path, bytes);
            logger.info("Successfully created file - " + fileFullName);
            return fileFullName;
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    private void deleteFile(String fileName) {
        try {
            if (fileName != null && !fileName.isEmpty()) {
                Path fileToDeletePath = Paths.get(File.separator + fileName);
                Files.delete(fileToDeletePath);
                logger.info("Successfully deleted file - " + fileName);
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
