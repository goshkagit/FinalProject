package com.finalproject.upwork.models;

import com.finalproject.upwork.models.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_login")
public class UserLoginModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loginId")
    private Long loginId;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Column(name = "password", nullable = false)
    private String password;


}
