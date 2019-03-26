package com.finalproject.upwork.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profile_details")
public class UserProfileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private UserLoginModel loginDetails;

    @Column(name = "user_id")
    private long user_id ;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "skill" , nullable = false)
    private String skill;

    @Column(name = "portfolio")
    private String portfolio;

    @Column(name = "surname" )
    private String surname;

    @Column(name = "email", unique = true , nullable = false)
    private String email;


}
