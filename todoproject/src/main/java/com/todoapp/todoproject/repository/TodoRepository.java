package com.todoapp.todoproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.todoproject.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findByName(String name);
    List<Todo> findAllByUserId(Integer id);
}
