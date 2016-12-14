package sample;

/**
 * Created by piotrek on 14.12.16.
 */
public enum FieldsOfStudies {
    W1K1("Informatyka"),
    W1K2("Elektronika"),
    W2K1("Mechanika i budowa maszyn"),
    W2K2("Mechanika pomp");

    private String name;

    FieldsOfStudies(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
