package com.finalproject.upwork.models.DTO.GetDTO;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class GetUserLoginDTO {

    private long id;

    @SpecialChars
    @Size(min = 3, max = 15)
    private String nickname;

}
