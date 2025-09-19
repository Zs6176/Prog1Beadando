package org.example.jatekElemek.varazslatok;

import org.example.jatekElemek.Varazslat;

public class Vilamcsapas  extends Varazslat {
    public Vilamcsapas(boolean kivalasztva,boolean joOldal) {
        super(60,5,"Vilamcsapas", kivalasztva,joOldal);
    }

    public Vilamcsapas(boolean kivalasztva) {
        super(60,5,"Vilamcsapas", kivalasztva);
    }

    @Override
    public String toString() {
        return "Vilamcsapas{} " + super.toString();
    }
}
