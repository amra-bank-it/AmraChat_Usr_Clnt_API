package com.example.amrachat_usr_clnt_api.dao;

import com.example.amrachat_usr_clnt_api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ClientsDao extends JpaRepository<Client,Long> {
    Client findById(long Id);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO AmraChat.chat_clients (id, firstname, lastname, phone, surname)\n" +
            "VALUES (1, 'default', 'chat', 'noPhone', 'client'); ",nativeQuery = true)
    void addFirstClient();
}
