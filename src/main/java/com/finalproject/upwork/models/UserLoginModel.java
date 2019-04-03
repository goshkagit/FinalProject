package com.finalproject.upwork.models;

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
@TableGenerator(name = "gen", allocationSize = 100)
@Table(name = "users_login")
public class UserLoginModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;


}
