package com.webserver.chooseyournews.model;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
public class User {
    public static final String DEFAULT_AVATAR = "default.jpg";

    private String login;
    private String password;
    private String userAvatar;
}
