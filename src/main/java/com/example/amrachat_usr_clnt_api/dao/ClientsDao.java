package com.example.amrachat_usr_clnt_api.dao;

import com.example.amrachat_usr_clnt_api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsDao extends JpaRepository<Client,Long> {
    Client findById(long Id);
}
