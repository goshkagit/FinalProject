package com.finalproject.upwork.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@TableGenerator(name = "gen", allocationSize = 100)
@Table(name = "submitted")
public class SubmittedModel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen")
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "userId")
    private UserProfileModel submittedUsersId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "taskId")
    private TaskModel submittedTaskId;

}
