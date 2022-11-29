package com.ajitata.candidatecapture.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Objects;

public class Candidate implements Serializable {

    private StringProperty registrationNumber;

    private StringProperty firstName;

    private StringProperty lastName;

    private StringProperty middleName;

    private StringProperty email;

    private StringProperty phoneNumber;

    private StringProperty state;

    private StringProperty lga;

    private StringProperty gender;

    private StringProperty centreCode;

    private StringProperty exam;

    private StringProperty year;

    public StringProperty registrationNumberProperty() {
        if (Objects.isNull(registrationNumber)) {
            registrationNumber = new SimpleStringProperty(this, "registrationNumber");
        }
        return registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumberProperty().get();
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumberProperty().set(registrationNumber);
    }

    public String getFirstName() {
        return firstNameProperty().get();
    }

    public StringProperty firstNameProperty() {
        if (Objects.isNull(firstName)) {
            firstName = new SimpleStringProperty(this, "firstName");
        }
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstNameProperty().set(firstName);
    }

    public String getLastName() {
        return lastNameProperty().get();
    }

    public StringProperty lastNameProperty() {
        if (Objects.isNull(lastName)) {
            lastName = new SimpleStringProperty(this, "lastName");
        }
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastNameProperty().set(lastName);
    }

    public String getMiddleName() {
        return middleNameProperty().get();
    }

    public StringProperty middleNameProperty() {
        if (Objects.isNull(middleName)) {
            middleName = new SimpleStringProperty(this, "middleName");
        }
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleNameProperty().set(middleName);
    }

    public String getEmail() {
        return emailProperty().get();
    }

    public StringProperty emailProperty() {
        if (Objects.isNull(email)) {
            email = new SimpleStringProperty(this, "email");
        }
        return email;
    }

    public void setEmail(String email) {
        this.emailProperty().set(email);
    }

    public String getPhoneNumber() {
        return phoneNumberProperty().get();
    }

    public StringProperty phoneNumberProperty() {
        if (Objects.isNull(phoneNumber)) {
            phoneNumber = new SimpleStringProperty(this, "phoneNumber");
        }
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumberProperty().set(phoneNumber);
    }

    public String getState() {
        return stateProperty().get();
    }

    public StringProperty stateProperty() {
        if (Objects.isNull(state)) {
            state = new SimpleStringProperty(this, "state");
        }
        return state;
    }

    public void setState(String state) {
        this.stateProperty().set(state);
    }

    public String getLga() {
        return lgaProperty().get();
    }

    public StringProperty lgaProperty() {
        if (Objects.isNull(lga)) {
            lga = new SimpleStringProperty(this, "lga");
        }
        return lga;
    }

    public void setLga(String lga) {
        this.lgaProperty().set(lga);
    }

    public String getGender() {
        return genderProperty().get();
    }

    public StringProperty genderProperty() {
        if (Objects.isNull(gender)) {
            gender = new SimpleStringProperty(this, "gender");
        }
        return gender;
    }

    public void setGender(String gender) {
        this.genderProperty().set(gender);
    }

    public String getCentreCode() {
        return centreCodeProperty().get();
    }

    public StringProperty centreCodeProperty() {
        if (Objects.isNull(centreCode)) {
            centreCode = new SimpleStringProperty(this, "centreCode");
        }
        return centreCode;
    }

    public void setCentreCode(String centreCode) {
        this.centreCodeProperty().set(centreCode);
    }

    public String getExam() {
        return examProperty().get();
    }

    public StringProperty examProperty() {
        if (Objects.isNull(exam)) {
            exam = new SimpleStringProperty(this, "exam");
        }
        return exam;
    }

    public void setExam(String exam) {
        this.examProperty().set(exam);
    }

    public String getYear() {
        return yearProperty().get();
    }

    public StringProperty yearProperty() {
        if (Objects.isNull(year)) {
            year = new SimpleStringProperty(this, "year");
        }
        return year;
    }

    public void setYear(String year) {
        this.yearProperty().set(year);
    }

}
