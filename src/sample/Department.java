package sample;

/**
 * Created by Maciej on 07.12.2016.
 */
public enum Department {
    W1("Elektronika"),
    W2("Mechaniczny");

    private String name;

    private Department(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
