package com.example.tms_v1.services;

import com.example.tms_v1.models.Token;
import com.example.tms_v1.models.TokenState;
import com.example.tms_v1.repositories.TokenRepository;
import com.example.tms_v1.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TokenService {
    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
    @Autowired
    private TokenRepository tokenRepo;

    public Token createToken(Token t){
        if(tokenCountDate(t.getDate()) >= 20){
            logger.error("cannot insert more than 20 per day");
            throw new RuntimeException("cannot insert more than 20");
        }
        else{
            logger.trace("token created");
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
            logger.trace("tokens deactivate");
            return t;
        }
        catch (Exception ex){
            logger.error(String.valueOf(ex));
            System.out.println(ex);
            return null;
        }
    }

    public List<Token> getTokensPatient(String patientId) {
        try {
            List<Token> tokenList = tokenRepo.findAllByPatientid(patientId);
            return tokenList;
        }
        catch(Exception ex){
            logger.error(String.valueOf(ex));
            return null;
        }
    }

    public List<Token> getAllTokens(){
        try{
            List<Token> allTokens = tokenRepo.findAll();
            return allTokens;
        }
        catch (Exception ex){
            logger.error(String.valueOf(ex));
            return null;
        }
    }

    public int getTokenCount(){
        try{
            List<Token> allTokens = tokenRepo.findAll();
            int tokenNumber = allTokens.size() + 1;
            return tokenNumber;
        }
        catch (Exception ex){
            logger.error(String.valueOf(ex));
            return 0;
        }
    }

    private int tokenCountDate(String date){
        try{
            List<Token> tokensToDate = tokenRepo.findAllByDate(date);
            int count = tokensToDate.size();
            return count;
        }
        catch (Exception ex){
            logger.error(String.valueOf(ex));
            return 0;
        }
    }

    public String deleteToken(Token t){
        try{
            tokenRepo.deleteById(t.getId());
            logger.trace("deleted token");
            return "deleted";
        }
        catch (Exception ex){
            logger.error(String.valueOf(ex));
            return "error";
        }
    }


}
