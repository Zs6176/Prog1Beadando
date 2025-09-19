package org.example.jatekElemek.varazslatok;

import org.example.jatekElemek.Varazslat;

public class Feltamasztas  extends Varazslat {
    public Feltamasztas(boolean kivalaszt,boolean joOldal) {
        super(120,6, "Feltamasztas",kivalaszt,joOldal);
    }

    public Feltamasztas(boolean kivalaszt) {
        super(120,6, "Feltamasztas",kivalaszt);
    }


    @Override
    public String toString() {
        return "Feltamasztas{} " + super.toString();
    }
}
