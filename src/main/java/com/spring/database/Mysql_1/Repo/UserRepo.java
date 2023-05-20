package com.spring.database.Mysql_1.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.database.Mysql_1.entity.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

}
