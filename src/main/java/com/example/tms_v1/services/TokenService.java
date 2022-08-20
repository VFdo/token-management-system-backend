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

    public Token createToken(Token t){
        if(tokenCountDate(t.getDate()) >= 20){
            System.out.println("cannot insert more than 20 per day");
            throw new RuntimeException("cannot insert more than 20");
        }
        else{
            return tokenRepo.save(t);
        }
    }

    public List<Token> deactivateToken(String date){
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

    public List<Token> getAllTokensPatient(Token t){
        List<Token> tokenList = tokenRepo.findAllByPatientid(t.getPatientid());
        return tokenList;
    }

    public List<Token> getAllTokens(){
        List<Token> allTokens = tokenRepo.findAll();
        return allTokens;
    }

    public int getTokenCount(){
        List<Token> allTokens = tokenRepo.findAll();
        int tokenNumber = allTokens.size() + 1;
        return tokenNumber;
    }

    private int tokenCountDate(String date){
        List<Token> tokensToDate = tokenRepo.findAllByDate(date);
        int count = tokensToDate.size();
        return count;
    }

    public String deleteToken(Token t){
        tokenRepo.deleteById(t.getId());
        return "deleted";
    }
}
