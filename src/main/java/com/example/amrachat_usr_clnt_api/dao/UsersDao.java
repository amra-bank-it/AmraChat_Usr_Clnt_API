package com.example.amrachat_usr_clnt_api.dao;

import com.example.amrachat_usr_clnt_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UsersDao extends JpaRepository<User,Long> {
    User findByFirstname(String firstname);
    User findById(long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO AmraChat.chat_users (id, department, firstname, lastname, permissions, surname)\n" +
            "VALUES (1, 'Amra', 'Support', 'Chat', 'oper', 'User');",nativeQuery = true)
    void addFirstClient();
}
