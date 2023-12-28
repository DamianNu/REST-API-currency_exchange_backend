package com.kodilla.currencyexchange.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity(name = "LOGIN_ATTEMPTS")
@AllArgsConstructor
@NoArgsConstructor
public class LoginAttempt {
    private Long id;
    private String login;
    private LocalDateTime loginTime;
    private boolean successful;

    public LoginAttempt(String login, boolean successful) {
        this.login = login;
        this.successful = successful;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    @Column(name = "LOGIN_TIME")
    public LocalDateTime getLoginTime() {
        LocalDateTime time = LocalDateTime.now();
        return time;
    }

    @Column(name = "IS_SUCCESSFUL")
    public boolean isSuccessful() {
        return successful;
    }
}
