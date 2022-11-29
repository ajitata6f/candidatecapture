package com.ajitata.candidatecapture.serializer;

import com.ajitata.candidatecapture.model.Candidate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CandidateSerializer extends StdSerializer<Candidate> {

    public CandidateSerializer () {
        this(null);
    }

    public CandidateSerializer (Class<Candidate> t) {
        super(t);
    }

    @Override
    public void serialize(Candidate candidate, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeStringField("registrationNumber", candidate.registrationNumberProperty().get());
        gen.writeStringField("firstName", candidate.firstNameProperty().get());
        gen.writeStringField("lastName", candidate.lastNameProperty().get());
        gen.writeStringField("middleName", candidate.lastNameProperty().get());
        gen.writeStringField("email", candidate.emailProperty().get());
        gen.writeStringField("phoneNumber", candidate.phoneNumberProperty().get());
        gen.writeStringField("state", candidate.stateProperty().get());
        gen.writeStringField("lga", candidate.lgaProperty().get());
        gen.writeStringField("gender", candidate.genderProperty().get());
        gen.writeStringField("centreCode", candidate.centreCodeProperty().get());
        gen.writeStringField("exam", candidate.examProperty().get());
        gen.writeStringField("year", candidate.yearProperty().get());

        gen.writeEndObject();
    }

}
