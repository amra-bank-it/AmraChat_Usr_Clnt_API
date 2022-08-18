package com.example.amrachat_usr_clnt_api.dbInit;

import com.example.amrachat_usr_clnt_api.dao.*;
import com.example.amrachat_usr_clnt_api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AfterSetup {

    private final ClientsDao clientsDao;
    private final UsersDao usersDao;

    @Autowired
    public AfterSetup(ClientsDao clientsDao, UsersDao usersDao) {
        this.clientsDao = clientsDao;
        this.usersDao = usersDao;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        if(!usersDao.existsById(1L)){
           usersDao.addFirstClient();
        }
        if(!clientsDao.existsById(1L)){
            clientsDao.addFirstClient();
        }

        System.out.println("Yaaah, I am running........");
    }
}


