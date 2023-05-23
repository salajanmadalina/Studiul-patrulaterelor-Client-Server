package Facade;

import Model.User;
import Service.UserService;

import java.util.ArrayList;

public class UserFacade {
    private UserService userService;

    public UserFacade(){
        this.userService = new UserService();
    }

    public void deleteUser(int id){
        userService.delete(id);
    }

    public void insertUser(User user){
        userService.insert(user);
    }

    public void updateUser(String field, String value, int id){
        userService.update(field, value, id);
    }

    public ArrayList<User> findAllUser(){
        return userService.findAll();
    }

}
