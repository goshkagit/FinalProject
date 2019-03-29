package com.finalproject.upwork.models;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    @Enumerated(EnumType.STRING)
    private Hardness hardness;

    @Column(name = "type" , nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "payment" , nullable = false)
    private int payment;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id" , nullable = false)
    private UserProfileModel who_posted;



}

