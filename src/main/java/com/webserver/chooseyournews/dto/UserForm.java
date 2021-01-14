package com.webserver.chooseyournews.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
public class UserForm {

    private String login;
    private String password;
}
