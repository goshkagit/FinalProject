package com.finalproject.upwork.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "submited")
public class SubmitedModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserLoginModel submitedUsersId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "submited_tasks" , referencedColumnName = "task_ID")
    private TaskModel taskModel;

}
