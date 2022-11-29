package com.ajitata.candidatecapture.controller;

import com.ajitata.candidatecapture.model.Candidate;
import com.ajitata.candidatecapture.util.RegistrationNumberUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCandidateController implements Initializable {

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField middlenameTextField;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private ComboBox<String> stateComboBox;

    @FXML
    private ComboBox<String> lgaComboBox;

    @FXML
    private TextField centreCodeTextField;

    @FXML
    private TextField examTypeTextField;

    @FXML
    private TextField examYearTextField;

    @FXML
    private Button cancelButton;

    private final ObservableList<String> genderModel = FXCollections.observableArrayList("Male", "Female");

    private final ObservableList<String> stateModel = FXCollections.observableArrayList("Kaduna");

    private final ObservableList<String> lgaModel = FXCollections.observableArrayList("Kaduna North");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        genderComboBox.setItems(genderModel);
        stateComboBox.setItems(stateModel);
        lgaComboBox.setItems(lgaModel);

    }

    @FXML
    public void onCreateCandidate () {
        Candidate candidate = new Candidate();
        Optional.ofNullable(firstnameTextField.getText()).ifPresentOrElse(candidate::setFirstName, () -> {displayAlert("Firstname cannot be empty"); return;});
        Optional.ofNullable(lastnameTextField.getText()).ifPresentOrElse(candidate::setLastName, () -> displayAlert("Lastname cannot be empty"));
        Optional.ofNullable(emailTextField.getText()).ifPresentOrElse(candidate::setEmail, () -> displayAlert("Email cannot be empty"));
        Optional.ofNullable(phoneNumberTextField.getText()).ifPresentOrElse(candidate::setPhoneNumber, () -> displayAlert("Phonenumber cannot be empty"));
        candidate.setMiddleName(middlenameTextField.getText());
        Optional.ofNullable(genderComboBox.getSelectionModel().getSelectedItem()).ifPresentOrElse(candidate::setGender, () -> displayAlert("Please select a gender"));
        Optional.ofNullable(stateComboBox.getSelectionModel().getSelectedItem()).ifPresentOrElse(candidate::setState, () -> displayAlert("Please select a state"));
        Optional.ofNullable(lgaComboBox.getSelectionModel().getSelectedItem()).ifPresentOrElse(candidate::setLga, () -> displayAlert("Please select a Local Government Area"));

        candidate.setRegistrationNumber(RegistrationNumberUtil.getRegistrationNumber());
        candidate.setCentreCode(centreCodeTextField.getText());
        candidate.setExam(examTypeTextField.getText());
        candidate.setYear(examYearTextField.getText());

        CandidatesController.candidateObservableList.add(candidate);
    }

    @FXML
    public void onCancel () {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

    private void displayAlert (String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
