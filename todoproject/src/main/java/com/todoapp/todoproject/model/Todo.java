package com.todoapp.todoproject.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="todo_table")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = true, name="description")
    private String description;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user ;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = true)
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusType status;
 
}
