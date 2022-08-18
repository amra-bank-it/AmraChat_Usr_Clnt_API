package com.example.amrachat_usr_clnt_api.controller;

import com.example.amrachat_usr_clnt_api.dao.*;
import com.example.amrachat_usr_clnt_api.model.Client;
import com.example.amrachat_usr_clnt_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsDao clientsDao;


    @Autowired
    public ClientsController(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }


    @PostMapping("/allClients")
    public ResponseEntity<?> allClients() {
        try {
            List<Client> clients = clientsDao.findAll();
            return new ResponseEntity<>(
                    clients,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/byId")
    public ResponseEntity<?> byId(@RequestBody Client client) {
        try {
            return new ResponseEntity<>(
                    clientsDao.findById(client.getId()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteById")
    public ResponseEntity<?> deleteUser(@RequestBody Client client) {
        String resMsg;
        try {
            if(client.getId()==1){
                throw new Exception("Permission denied for delete client with id = 1");
            }

            if(clientsDao.existsById(client.getId())) {
                clientsDao.delete(client);
                resMsg = "{Client with id: " + client.getId() + " deleted}";
            }else {
                resMsg = "{Client with id: " + client.getId() + " not exists}";
            }
            return new ResponseEntity<>(
                    resMsg,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody Client client){
        String exsMsg;
        try {
            Client lClient = clientsDao.existsById(client.getId())? client : new Client();

            lClient.setFirstname(client.getFirstname());
            lClient.setLastname(client.getLastname());
            lClient.setSurname(client.getSurname());
            lClient.setPhone(client.getPhone());

            clientsDao.save(lClient);

            return new ResponseEntity<>(
                    lClient,
                    HttpStatus.OK);
        } catch (Exception e) {
            exsMsg = "{ Client save exception " + e.getMessage() + " }";
            return new ResponseEntity<>(
                    exsMsg,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

