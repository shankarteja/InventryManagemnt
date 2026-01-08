package com.todoexample.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todoexample.Entinty.Todo;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
