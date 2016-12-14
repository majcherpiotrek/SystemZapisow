package sample;

/**
 * Created by piotrek on 14.12.16.
 */
public enum Specialization {
    NOSPECIALIZATION("brak specjalizacji"),
    W1K1S1("Sieci i systemy komputerowe"),
    W1K1S2("Inżynieria systemów informatycznych"),
    W1K2S1("Akustyka"),
    W1K2S2("Mikroprocesory"),
    W2K1S1("Motoryzacja"),
    W2K1S2("Maszyny przemysłowe"),
    W2K2S1("Fajne pompy"),
    W2K2S2("Niefajne pompy");

    private String name;

    Specialization(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
