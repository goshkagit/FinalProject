package com.finalproject.upwork.models;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_login")
public class UserLoginModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nickname", unique = true , nullable = false)
    private String nickname;

    @Column(name = "password" , nullable = false)
    private String password;

    @OneToMany(mappedBy = "whoPostedId" , cascade = CascadeType.ALL)
    private Set<TaskModel> taskModels = new HashSet<>();

    @OneToMany(mappedBy = "submitedUsersId" , cascade = CascadeType.ALL)
    private Set<SubmitedModel> submitedModels  = new HashSet<>();



}
