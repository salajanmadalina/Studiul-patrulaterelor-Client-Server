package Service;

import Model.Intrebare;
import java.util.ArrayList;

public class IntrebareService extends AbstractService<Intrebare> {
    public Intrebare insert(Intrebare intrebare){
        super.insert(intrebare);
        return intrebare;
    }

    public Intrebare delete(int id){
        super.delete(id);
        return null;
    }

    public Intrebare update(String field, String value, int id){
        super.update(field, value, id);
        return null;
    }

    public ArrayList<Intrebare> findAll(){
        return (ArrayList<Intrebare>) super.findAll();
    }
}
