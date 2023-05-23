package Service;

import Model.User;
import java.util.ArrayList;

public class UserService extends AbstractService<User> {
    public User insert(User user){
        super.insert(user);
        return user;
    }

    public User delete(int id){
        super.delete(id);
        return null;
    }

    public User update(String field, String value, int id){
        super.update(field, value, id);
        return null;
    }

    public ArrayList<User> findAll(){
        return (ArrayList<User>) super.findAll();
    }
}
