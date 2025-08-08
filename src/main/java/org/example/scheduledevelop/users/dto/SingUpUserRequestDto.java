package org.example.scheduledevelop.users.dto;

import lombok.Getter;

@Getter
public class SingUpUserRequestDto {
    private final String username;
    private final String email;
    private final String password;

    public SingUpUserRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
