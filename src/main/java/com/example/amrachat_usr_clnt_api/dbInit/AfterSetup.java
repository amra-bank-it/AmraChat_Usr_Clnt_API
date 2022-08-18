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
            User user =
                    new User(1L,
                            "support",
                            "amra",
                            "chat",
                            "Support",
                            "Amra"
                            );

            usersDao.save(user);
        }
        if(!clientsDao.existsById(1L)){
            Client client = new Client(
                    1L,
                    "TestClient",
                    "TestClient",
                    "TestClient",
                    "+00000000000"

            );
            clientsDao.save(client);
        }

        System.out.println("Yaaah, I am running........");
    }
}


