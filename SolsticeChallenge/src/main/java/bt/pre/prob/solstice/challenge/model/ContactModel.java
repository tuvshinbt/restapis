/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.model;

import bt.pre.prob.solstice.challenge.entity.Address;
import bt.pre.prob.solstice.challenge.entity.Company;
import bt.pre.prob.solstice.challenge.entity.Contact;
import bt.pre.prob.solstice.challenge.entity.Person;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tuvshuu
 */
public class ContactModel {

    private String fullName;
    private Date birthDate;
    private String profileImage;
    @JsonIgnore
    private MultipartFile profileImageFile;
    private Integer contactId;
    private String email;
    private Long phoneNumber;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private Integer zipCode;
    private String companyName;
    private Long companyPhone;

    public ContactModel() {
    }

    public ContactModel(Person person) {
        this.fullName = person.getFullName();
        this.birthDate = person.getBirthDate();
        this.profileImage = person.getContact().getProfileImage();
        this.contactId = person.getContact().getId();
        this.email = person.getContact().getEmail();
        this.phoneNumber = person.getContact().getPhoneNumber();
        this.address1 = person.getAddress().getAddress1();
        this.address2 = person.getAddress().getAddress2();
        this.city = person.getAddress().getCity();
        this.state = person.getAddress().getState();
        this.zipCode = person.getAddress().getZipCode();
        this.companyName = person.getCompany().getName();
        this.companyPhone = person.getCompany().getPhone();
    }

    public Person person() {
        return new Person(fullName, birthDate,
                new Contact(profileImage, email, phoneNumber),
                new Address(address1, address2, city, state, zipCode),
                new Company(companyName, companyPhone));
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public MultipartFile getProfileImageFile() {
        return profileImageFile;
    }

    public void setProfileImageFile(MultipartFile profileImageFile) {
        this.profileImageFile = profileImageFile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(Long companyPhone) {
        this.companyPhone = companyPhone;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "ContactModel{" + "fullName=" + fullName + ", birthDate=" + birthDate + ", profileImage=" + profileImage + ", contactId=" + contactId + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", companyName=" + companyName + ", companyPhone=" + companyPhone + '}';
    }

}
