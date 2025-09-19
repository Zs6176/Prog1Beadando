package org.example.jatekElemek.egysegek;

import org.example.jatekElemek.Egyseg;

public class Ijasz extends Egyseg {

    public Ijasz(boolean joOldal ,int darabsazam) {
        super(7,joOldal,false,false,9,"Ijasz",darabsazam,4,7,6,4,2);
    }

    @Override
    public String toString() {
        return "Ijasz{} " + super.toString();
    }
}
