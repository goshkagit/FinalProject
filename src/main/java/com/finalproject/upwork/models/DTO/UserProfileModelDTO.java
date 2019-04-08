package com.finalproject.upwork.models.DTO;

import com.finalproject.upwork.validation.annotations.Page;
import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserProfileModelDTO {

    private long id;

    @SpecialChars
    @com.finalproject.upwork.validation.annotations.Size
    private String surname;

    @SpecialChars
    @com.finalproject.upwork.validation.annotations.Size
    private String name;

    @SpecialChars
    private String skill;

    @Email
    @com.finalproject.upwork.validation.annotations.Size
    private String email;

    @Page
    @com.finalproject.upwork.validation.annotations.Size
    private String portfolio;


}
