package org.example.jatekElemek.egysegek;

import org.example.jatekElemek.Egyseg;

public class IceMan extends Egyseg {
    public IceMan(boolean joOldal,int darabszam) {
        super(50,joOldal,false,false,10,"IceMan",darabszam,5,50,50,20,15);
    }

    @Override
    public String toString() {
        return "IceMan{} " + super.toString();
    }
}
