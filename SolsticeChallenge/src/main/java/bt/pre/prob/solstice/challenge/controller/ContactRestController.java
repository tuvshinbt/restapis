/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.controller;

import bt.pre.prob.solstice.challenge.exception.InBadRequestException;
import bt.pre.prob.solstice.challenge.exception.ContactNotFoundException;
import bt.pre.prob.solstice.challenge.model.ContactModel;
import bt.pre.prob.solstice.challenge.service.ContactCriteria;
import bt.pre.prob.solstice.challenge.service.ContactService;
import bt.pre.prob.solstice.challenge.util.JsonConverter;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author tuvshuu
 */
@RestController("contact")
@RequestMapping(value = "/restapi/contact")
public class ContactRestController {

    private static final Logger logger = LogManager.getLogger(ContactRestController.class);

    @Autowired
    private ContactService contactService;
    @Autowired
    private JsonConverter jsonConverter;

    @GetMapping("{id}")
    public ContactModel retrieve(@PathVariable Integer id) {
        logger.info("retrieve() has been called.");
        return contactService.retrieve(id);
    }

    @GetMapping("all")
    public List<ContactModel> retrieveAll() {
        logger.info("retrieveAll() has been called.");
        return contactService.retrieveAll(null, null);
    }

    @GetMapping("all/{state}")
    public List<ContactModel> retrieveAllByState(@PathVariable String state) {
        logger.info("retrieveAllByState() has been called.");
        return contactService.retrieveAll(state, null);
    }

    @GetMapping("all/{state}/{city}")
    public List<ContactModel> retrieveAllByStateAndCity(@PathVariable String state, @PathVariable String city) {
        logger.info("retrieveAllByStateAndCity() has been called.");
        return contactService.retrieveAll(state, city);
    }

    @GetMapping("search/{searchBy}/{value}")
    public ContactModel search(@PathVariable String searchBy, @PathVariable String value) {
        logger.info("search() has been called.");
        if (ContactCriteria.EMAIL.toString().equalsIgnoreCase(searchBy)) {
            return contactService.search(ContactCriteria.EMAIL, value);
        } else if (ContactCriteria.PHONE_NUMBER.toString().equalsIgnoreCase(searchBy)) {
            return contactService.search(ContactCriteria.PHONE_NUMBER, value);
        } else {
            throw new InBadRequestException("Unknown filter!");
        }
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestParam(name = "profileImageFile", required = true) MultipartFile profileImageFile,
            @RequestParam(name = "contactModel", required = true) String contactModelStr,
            UriComponentsBuilder ucBuilder) throws IOException {
        logger.info("create() has been called.");
        ContactModel contactModel = convertContractModel(profileImageFile, contactModelStr);
        Integer id = contactService.create(contactModel);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/restapi/contact/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Integer id,
            @RequestParam(name = "profileImageFile", required = true) MultipartFile profileImageFile,
            @RequestParam(name = "contactModel", required = true) String contactModelStr) throws IOException {
        logger.info("update() has been called.");
        ContactModel contactModel = convertContractModel(profileImageFile, contactModelStr);
        contactService.update(id, contactModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ContactModel convertContractModel(MultipartFile profileImageFile, String contactModelStr) throws IOException {
        ContactModel contactModel = jsonConverter.<ContactModel>jsonToObject(contactModelStr, ContactModel.class);
        contactModel.setProfileImageFile(profileImageFile);
        return contactModel;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        logger.info("delete() has been called.");
        contactService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleAllExceptions(Exception ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>("Oops! ERROR " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public final ResponseEntity<String> handleContactNotFoundException(ContactNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InBadRequestException.class)
    public final ResponseEntity<String> handleInBadRequestException(InBadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public final ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public final ResponseEntity<String> handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
