package com.kodilla.currencyexchange.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime date;

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Id
    @Column(name = "ID_USER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @NotBlank
    @Email
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    @Size(min = 6)
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    @Column(name = "TIME_REGISTRATION")
    public LocalDateTime getDate() {
        LocalDateTime time = LocalDateTime.now();
        return time;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
