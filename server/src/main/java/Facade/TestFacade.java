package Facade;

import Model.Test;
import Service.TestService;

import java.util.ArrayList;

public class TestFacade {
    private TestService testService;

    public TestFacade(){
        this.testService = new TestService();
    }

    public void deleteTest(int id){
        testService.delete(id);
    }

    public void insertTest(Test test){
        testService.insert(test);
    }

    public void updateTest(String field, String value, int id){
        testService.update(field, value, id);
    }

    public ArrayList<Test> findAllTest(){
        return testService.findAll();
    }
}
