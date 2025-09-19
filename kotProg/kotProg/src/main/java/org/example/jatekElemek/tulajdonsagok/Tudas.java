package org.example.jatekElemek.tulajdonsagok;

import org.example.jatekElemek.Tulajdonsagok;

public class Tudas extends Tulajdonsagok {
    private int manapontNovel=0;

    public int getManapontNovel() {
        return manapontNovel;
    }

    public void setManapontNovel() {
        this.manapontNovel = getSzint()*10;
    }

    public Tudas(int szint) {
        super(szint, "Tudas");
        setManapontNovel();
    }

    @Override
    public String toString() {
        return "Tudas{" +
                "manapontNovel=" + manapontNovel +
                "} " + super.toString();
    }
}
