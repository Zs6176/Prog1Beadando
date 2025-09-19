package org.example.jatekElemek.egysegek;

import org.example.jatekElemek.Egyseg;

public class Foldmuves  extends Egyseg {
    public Foldmuves(boolean joOldal ,int darabszam) {
        super(4,joOldal,false,false,8,"Foldmuves",darabszam,4,3,2,1,1);
    }

    @Override
    public String toString() {
        return "Foldmuves{} " + super.toString();
    }
}
