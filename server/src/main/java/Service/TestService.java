package Service;

import Model.Test;
import java.util.ArrayList;

public class TestService extends AbstractService<Test> {
    public Test insert(Test test){
        super.insert(test);
        return test;
    }

    public Test delete(int id){
        super.delete(id);
        return null;
    }

    public Test update(String field, String value, int id){
        super.update(field, value, id);
        return null;
    }

    public ArrayList<Test> findAll(){
        return (ArrayList<Test>) super.findAll();
    }
}
