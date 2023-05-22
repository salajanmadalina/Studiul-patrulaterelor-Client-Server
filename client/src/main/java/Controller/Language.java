package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class Language {
    private List<Observer> observers = new ArrayList<>();
    private Locale locale = Locale.getDefault();
    private ArrayList<Locale> locales = new ArrayList<Locale>(){
        {
            add(Locale.getDefault());
            add(Locale.ITALY);
            add(Locale.FRANCE);
        }
    };
    private ResourceBundle rb = ResourceBundle.getBundle("MyResources", locale);
    private static Language singleton = null;

    private Language(){
    }

    private Language(int language){
        rb = ResourceBundle.getBundle("MyResources", locales.get(language));
        notifyObservers();
    }

    public static Language getInstance(){
        if(singleton == null){
            singleton = new Language(0);
        }
        return singleton;
    }

    public int getLanguage(){
        if(locale.equals(Locale.getDefault()))
            return 0;
        if(locale.equals(Locale.ITALY))
            return 1;
        if(locale.equals(Locale.FRANCE))
            return 2;
        return 0;
    }

    public void setCurrentLanguage(int language) {
        rb = ResourceBundle.getBundle("MyResources", locales.get(language));
        locale = locales.get(language);
        notifyObservers();
    }

    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}