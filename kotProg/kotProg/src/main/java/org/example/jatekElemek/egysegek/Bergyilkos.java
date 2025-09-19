package org.example.jatekElemek.egysegek;

import org.example.jatekElemek.Egyseg;

public class Bergyilkos extends Egyseg {
    public Bergyilkos( boolean joOldal,int darabszam) {
        super(4,joOldal,false,false,20,"Bergyilkos",darabszam,15,4,30,15,10);
    }

    @Override
    public String toString() {
        return "Bergyilkos{} " + super.toString();
    }
}
