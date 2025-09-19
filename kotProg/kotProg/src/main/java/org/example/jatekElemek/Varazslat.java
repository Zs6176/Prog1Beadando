package org.example.jatekElemek;

public class Varazslat {
    private int koltseg;
    private int mana;
    private String nev;
    private boolean kivalasztva;
    private boolean joOldal;

    public boolean isJoOldal() {
        return joOldal;
    }

    public void setJoOldal(boolean joOldal) {
        this.joOldal = joOldal;
    }

    public int getKoltseg() {
        return koltseg;
    }

    public void setKoltseg(int koltseg) {
        this.koltseg = koltseg;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public boolean isKivalasztva() {
        return kivalasztva;
    }

    public void setKivalasztva(boolean kivalasztva) {
        this.kivalasztva = kivalasztva;
    }

    public Varazslat(int koltseg, int mana, String nev, boolean kivalasztva, boolean joOldal) {
        setKoltseg(koltseg);
        setMana(mana);
        setNev(nev);
        setKivalasztva(kivalasztva);
        setJoOldal(joOldal);
    }
    public Varazslat(int koltseg, int mana, String nev, boolean kivalasztva) {
        setKoltseg(koltseg);
        setMana(mana);
        setNev(nev);
        setKivalasztva(kivalasztva);
    }

    @Override
    public String toString() {
        return "Varazslat{" +
                "koltseg=" + koltseg +
                ", mana=" + mana +
                ", nev='" + nev + '\'' +
                ", kivalasztva=" + kivalasztva +
                '}';
    }
}
