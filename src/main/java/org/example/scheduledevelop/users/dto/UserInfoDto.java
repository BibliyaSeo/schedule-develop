package org.example.scheduledevelop.users.dto;

import lombok.Getter;

@Getter
public class UserInfoDto {
    private final Long id;
    private final String username;

    public UserInfoDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
