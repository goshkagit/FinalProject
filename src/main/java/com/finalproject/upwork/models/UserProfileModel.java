package com.finalproject.upwork.models;

import com.finalproject.upwork.models.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableGenerator(name = "gen"  ,allocationSize = 100)
@Table(name = "user_profile_details")
public class UserProfileModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE , generator = "gen")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId" , referencedColumnName = "id")
    private UserLoginModel userId;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "skill" , nullable = false)
    @Enumerated(EnumType.STRING)
    private Type skill;

    @Column(name = "portfolio")
    private String portfolio;

    @Column(name = "surname" )
    private String surname;

    @Column(name = "email", unique = true , nullable = false)
    private String email;


}
