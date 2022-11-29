module com.ajitata.candidatecapture {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.rabbitmq.client;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j.simple;
    requires java.sql;

    opens com.ajitata.candidatecapture to javafx.fxml;
    opens com.ajitata.candidatecapture.controller to javafx.fxml;
    opens com.ajitata.candidatecapture.model to javafx.base;

    exports com.ajitata.candidatecapture;
}