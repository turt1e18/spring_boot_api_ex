package com.example.test.myapp

import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo,Long>