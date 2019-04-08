package com.finalproject.upwork.models.DTO;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserLoginDTO {

    private long id;

    @SpecialChars
    @com.finalproject.upwork.validation.annotations.Size
    private String nickname;


    @com.finalproject.upwork.validation.annotations.Size
    private String password;
}
