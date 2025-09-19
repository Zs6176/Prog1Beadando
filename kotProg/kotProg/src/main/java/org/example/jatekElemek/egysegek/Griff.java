package org.example.jatekElemek.egysegek;

import org.example.jatekElemek.Egyseg;

public class Griff extends Egyseg {
    public Griff(boolean joOldal,int darabszam) {
        super(30,joOldal,false,false,15,"Griff",darabszam,7,30,15,10,5);
    }

    @Override
    public String toString() {
        return "Griff{} " + super.toString();
    }
}
