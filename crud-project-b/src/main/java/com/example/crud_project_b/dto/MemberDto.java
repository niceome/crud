package com.example.crud_project_b.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    @NotBlank(message = "아이디는 필수")
    private String username;

    @NotBlank(message = "비밀번호는 필수")
    private String password;

}
