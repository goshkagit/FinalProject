package com.finalproject.upwork.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "submitted")
public class SubmittedModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    private UserProfileModel submittedUsersId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "task_id")
    private TaskModel submittedTaskId;

}
