package com.example.tms_v1.repositories;

import com.example.tms_v1.models.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TokenRepository extends MongoRepository<Token, String> {
//    Token save(Token t);

//    List<Token> sortByDate(); :TODO - sort by date?
    List<Token> findAll();
    List<Token> findAllByDate(String date);

    List<Token> findAllByPatientid(String patientid);
    void deleteById(String id);


}
