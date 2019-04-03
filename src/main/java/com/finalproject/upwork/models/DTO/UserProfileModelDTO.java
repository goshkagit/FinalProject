package com.finalproject.upwork.models.DTO;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserProfileModelDTO {

    private long id;

    @SpecialChars
    @Size(min = 2)
    private String surname;

    @SpecialChars
    @Size(min = 3)
    private String name;

    @SpecialChars
    private String skill;

    @Email
    @NotBlank
    private String email;

    @Size(min = 3, max = 100)
    private String portfolio;


}
