package it.epicode.BE_W6_Exam.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
