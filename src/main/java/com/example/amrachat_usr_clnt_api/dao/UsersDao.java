package com.example.amrachat_usr_clnt_api.dao;

import com.example.amrachat_usr_clnt_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDao extends JpaRepository<User,Long> {
    User findByFirstname(String firstname);
    User findById(long id);
}
