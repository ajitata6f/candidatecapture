package com.ajitata.candidatecapture.controller;

import com.ajitata.candidatecapture.MainApplication;
import com.ajitata.candidatecapture.messaging.MessagePublisher;
import com.ajitata.candidatecapture.messaging.QueueConfig;
import com.ajitata.candidatecapture.model.Candidate;
import com.ajitata.candidatecapture.serializer.CandidateSerializer;
import com.ajitata.candidatecapture.util.RegistrationNumberUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class CandidatesController implements Initializable {

    @FXML
    private TableView<Candidate> candidateTableView;

    @FXML
    private TableColumn<Candidate, String> serialNumberColumn;

    @FXML
    private TableColumn<Candidate, String> registrationNumberColumn;

    @FXML
    private TableColumn<Candidate, String> firstNameColumn;

    @FXML
    private TableColumn<Candidate, String> middleNameColumn;

    @FXML
    private TableColumn<Candidate, String> lastNameColumn;

    @FXML
    private TableColumn<Candidate, String> phoneNumberColumn;

    @FXML
    private TableColumn<Candidate, String> emailColumn;

    @FXML
    private TableColumn<Candidate, String> stateColumn;

    @FXML
    private TableColumn<Candidate, String> lgaColumn;

    @FXML
    private TableColumn<Candidate, String> genderColumn;

    @FXML
    private TableColumn<Candidate, String> centreColumn;

    @FXML
    private TableColumn<Candidate, String> examColumn;

    @FXML
    private TableColumn<Candidate, String> yearColumn;

    @FXML
    private ProgressIndicator progressIndicator;

    static ObservableList<Candidate> candidateObservableList = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        candidateTableView.setItems(candidateObservableList);

        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        lgaColumn.setCellValueFactory(new PropertyValueFactory<>("lga"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        centreColumn.setCellValueFactory(new PropertyValueFactory<>("centreCode"));
        examColumn.setCellValueFactory(new PropertyValueFactory<>("exam"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        serialNumberColumn.setCellFactory(param -> new TableCell<>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(Integer.toString(getIndex() + 1));
                }
            }
        });

    }

    @FXML
    public void onAddCandidate () throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("add-candidate-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Candidate Capture");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onCandidateUpload () throws IOException, TimeoutException {
        Task<Void> candidateUploadTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("Uploading candidate...");
                progressIndicator.setVisible(true);

                MessagePublisher messagePublisher = new MessagePublisher();

                try (Connection connection = messagePublisher.getConnection(); Channel channel = messagePublisher.getChannel(connection)) {
                    ObjectMapper objectMapper = new ObjectMapper();

                    SimpleModule module = new SimpleModule();
                    module.addSerializer(Candidate.class, new CandidateSerializer());
                    objectMapper.registerModule(module);

                    for (int i= 0; i < 5000; i++) {
                        candidateObservableList.add(getCandidate());
                    }


                    for (Candidate candidate: candidateObservableList) {
                        String serializedCandidate = objectMapper.writeValueAsString(candidate);

                        channel.basicPublish(QueueConfig.QUEUE_EXCHANGE, QueueConfig.ROUTING_KEY, null, serializedCandidate.getBytes());
                    }
                }

                return null;
            }

            @Override
            protected void succeeded() {
                progressIndicator.setVisible(false);

                candidateObservableList.clear();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Candidate upload successful...");
                alert.show();
            }
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(candidateUploadTask);
    }

    private Candidate getCandidate () {
        Candidate candidate = new Candidate();
        candidate.setRegistrationNumber(RegistrationNumberUtil.getRegistrationNumber());
        candidate.setFirstName("Abubakar");
        candidate.setLastName("Muhammad");
        candidate.setMiddleName("Auwal");
        candidate.setCentreCode("0140152");
        candidate.setState("Kaduna");
        candidate.setLga("Kaduna North");
        candidate.setGender("Male");
        candidate.setEmail("abubakarmuhammad@gmail.com");
        candidate.setPhoneNumber("08134869493");
        candidate.setExam("SSCE_INT");
        candidate.setYear("2022");

        return candidate;
    }

}
