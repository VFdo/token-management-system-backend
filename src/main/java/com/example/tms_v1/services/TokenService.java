package com.example.tms_v1.services;

import com.example.tms_v1.models.Token;
import com.example.tms_v1.models.TokenState;
import com.example.tms_v1.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepo;

//    add new -check if patient&date exists (p)
    public Token createToken(Token t){
        return tokenRepo.save(t);
    }

//    edit -change state (m)
    public List<Token> updateToken(String date){
        try{
            List<Token> t = tokenRepo.findAllByDate(date);
            t.forEach(item -> {
                item.setState(TokenState.DEACTIVATED);
            });
            tokenRepo.saveAll(t);
            return t;
        }
        catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }

//    search -by patient (m/p)
    public List<Token> getAllTokensPatient(String patientid){
        List<Token> tokenList = tokenRepo.findAllByPatientid(patientid);
        if(tokenList.isEmpty()){
            throw new RuntimeException("No tokens to show");
        }
        else{
            return tokenList;
        }
    }

//    get all
public List<Token> getAllTokens(){
    List<Token> allTokens = tokenRepo.findAll();
    if(allTokens.isEmpty()){
        throw new RuntimeException("No tokens to shows");
    }
    else{
        return tokenRepo.findAll();
    }
}

}
