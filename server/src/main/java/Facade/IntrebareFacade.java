package Facade;

import Model.Intrebare;
import Service.IntrebareService;

import java.util.ArrayList;

public class IntrebareFacade {
    private IntrebareService intrebareService;

    public IntrebareFacade(){
        this.intrebareService = new IntrebareService();
    }

    public void deleteIntrebare(int id){
        intrebareService.delete(id);
    }

    public void insertIntrebare(Intrebare intrebare){
        intrebareService.insert(intrebare);
    }

    public void updateIntrebare(String field, String value, int id){
        intrebareService.update(field, value, id);
    }

    public ArrayList<Intrebare> findAllIntrebare(){
        return intrebareService.findAll();
    }
}
