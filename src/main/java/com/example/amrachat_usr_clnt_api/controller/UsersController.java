package com.example.amrachat_usr_clnt_api.controller;

import com.example.amrachat_usr_clnt_api.dao.UsersDao;
import com.example.amrachat_usr_clnt_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersDao usersDao;


    @Autowired
    public UsersController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    @PostMapping("/allUsers")
    public ResponseEntity<?> allUsers() {
        try {
            List<User> users = usersDao.findAll();
            return new ResponseEntity<>(
                    users,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/byId")
    public ResponseEntity<?> byId(@RequestBody User user) {
        try {
            return new ResponseEntity<>(
                    usersDao.findById(user.getId()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteById")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        String resMsg;
        try {
            if(usersDao.existsById(user.getId())) {
                usersDao.delete(user);
                resMsg = "{User with id: " + user.getId() + " deleted}";
            }else {
                resMsg = "{User with id: " + user.getId() + " not exists}";
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
    public ResponseEntity<?> saveUser(@RequestBody User user){
        String exsMsg;
        try {
            User lUser = usersDao.existsById(user.getId())? user : new User();

            lUser.setFirstname(user.getFirstname());
            lUser.setLastname(user.getLastname());
            lUser.setSurname(user.getSurname());
            lUser.setDepartment(user.getDepartment());
            lUser.setPermissions(user.getDepartment());

            usersDao.save(lUser);

            return new ResponseEntity<>(
                    lUser,
                    HttpStatus.OK);
        } catch (Exception e) {
            exsMsg = "{ User save exception " + e.getMessage() + " }";
            return new ResponseEntity<>(
                    exsMsg,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


