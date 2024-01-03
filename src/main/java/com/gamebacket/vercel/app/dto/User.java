package com.gamebacket.vercel.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    private List<String> phones;
}
