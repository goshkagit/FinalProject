package com.finalproject.upwork.models;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profile_details")
public class UserProfileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private UserLoginModel userLoginModel;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "skill" , nullable = false)
    private String skill;

    @Column(name = "portfolio")
    private String portfolio;

    @Column(name = "surname" )
    private String surname;

    @Column(name = "mail", unique = true , nullable = false)
    private String mail;


}
