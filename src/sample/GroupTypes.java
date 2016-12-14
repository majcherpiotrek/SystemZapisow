package sample;

/**
 * Created by piotrek on 14.12.16.
 */
public enum GroupTypes {
    LCTR("Wykład"),
    EX("Ćwiczenia"),
    SEM("Seminarium"),
    LAB("Laboratorium"),
    PR("Projekt");

    private String name;

    GroupTypes(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
