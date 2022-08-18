package com.example.tms_v1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document (collection = "tokens")
public class Token {
    @Id
    private String id;
    private String patientid;
    private String date;
    private TokenState state;

    public Token(String patientid, String date, TokenState state) {
        this.patientid = patientid;
        this.date = date;
        this.state = state;
    }

//    public Token(String patientid, String date, Set<String> state) {
//    }
}
