package org.example.jatekElemek.varazslatok;

import org.example.jatekElemek.Varazslat;

public class Fagyasztas extends Varazslat {

    public Fagyasztas(boolean kivalasztva,boolean joOldal) {super(100, 5, "Fagyasztas", kivalasztva,joOldal);}

    public Fagyasztas(boolean kivalasztva) {super(100, 5, "Fagyasztas", kivalasztva);}


    @Override
    public String toString() {
        return "Fagyasztas{} " + super.toString();
    }
}
