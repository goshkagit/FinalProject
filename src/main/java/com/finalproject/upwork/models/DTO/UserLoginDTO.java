package com.finalproject.upwork.models.DTO;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserLoginDTO {

    private long id;

    @SpecialChars
    @Size(min = 3 , max = 15)
    @NotBlank
    private String nickname;

    @Size(min = 3)
    @NotBlank
    private String password;
}
