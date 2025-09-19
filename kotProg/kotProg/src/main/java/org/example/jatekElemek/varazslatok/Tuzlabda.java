package org.example.jatekElemek.varazslatok;

import org.example.jatekElemek.Varazslat;

public class Tuzlabda extends Varazslat {
    public Tuzlabda(boolean kivalasztva, boolean joOldal) {
        super(120,9,"Tuzlabda", kivalasztva,joOldal);
    }

    public Tuzlabda(boolean kivalasztva) {
        super(120,9,"Tuzlabda", kivalasztva);
    }


    @Override
    public String toString() {
        return "Tuzlabda{} " + super.toString();
    }
}
