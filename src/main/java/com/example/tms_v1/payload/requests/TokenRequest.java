package com.example.tms_v1.payload.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;
@Component
@Getter @Setter
public class TokenRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String patientId;

    @NotBlank
    @Size(max = 10)
    private String date;

}
