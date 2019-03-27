package com.finalproject.upwork.models;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long task_id;

    @Column(name = "topic", unique = true , nullable = false)
    private String topic;

    @Column(name = "description" , nullable = false)
    private String description;

    @Column(name = "hardness" , nullable = false)
    private String hardnessLevel;

    @Column(name = "type" , nullable = false)
    private String type;

//    @Column(name = "deadline")
//    private Date deadline;

    @Column(name = "payment" , nullable = false)
    private int payment;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id" , nullable = false)
    private UserProfileModel who_posted;



}

