package org.example.jatekElemek.tulajdonsagok;

import org.example.jatekElemek.Tulajdonsagok;

public class Moral extends Tulajdonsagok{

    private int kezdemenyezesNovel = 1;

    public int getKezdemenyezesNovel() {
        return kezdemenyezesNovel;
    }

    public void setKezdemenyezesNovel() {
        this.kezdemenyezesNovel = getSzint();
    }

    public Moral(int szint) {
        super(szint, "Moral");
        setKezdemenyezesNovel();
    }

    @Override
    public String toString() {
        return "Moral{" +
                "kezdemenyezesNovel=" + kezdemenyezesNovel +
                "} " + super.toString();
    }
}
