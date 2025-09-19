package org.example.jatekElemek;


public class Tulajdonsagok {
    private int koltseg = 5;
    private int szint = 1;
    private int osz = 5;
    private String nev;


    public int getKoltseg() {
        return koltseg;
    }

    public void setKoltseg(int koltseg) {
        this.koltseg = koltseg;
    }

    public int getSzint() {
        return szint;
    }

    public void setSzint(int szint) {
        if(szint>=10){
            this.szint=10;
        }else if (szint<1){
            this.szint=1;
        }else {
            this.szint = szint;
        }
    }

    public int getOsz() {
        return osz;
    }

    public void setOsz() {
        koltegNovel(getSzint());
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Tulajdonsagok(int szint , String nev) {
        setSzint(szint);
        setNev(nev);
        setOsz();
    }

    public static int koltegNovelCiklus(int max) {
        double noveles = 5;
        int aktualisertek = 5;
        int ossz = 5;
        for (int i = 1; i < max; i++) {
            noveles *= 0.1;
            if ((noveles) != (int) (noveles)) {
                int ertek = (int) noveles + 1;
                aktualisertek = aktualisertek + ertek;
                ossz += aktualisertek;
            } else {
                aktualisertek += (int) noveles;
                ossz += aktualisertek;
            }
            noveles = aktualisertek;
        }
        return ossz;
    }

    public void koltegNovel(int szint) {
        double noveles = getKoltseg();
        for (int i = 1; i < szint; i++) {
            noveles *= 0.1;
            if ((noveles) != (int) (noveles)) {
                int ertek = (int) noveles + 1;
                setKoltseg(getKoltseg() + ertek);
                this.osz += getKoltseg();
            } else {
                setKoltseg(getKoltseg() + (int) noveles);
                this.osz += getKoltseg();
            }
            noveles=getKoltseg();
        }
    }

    @Override
    public String toString() {
        return "Tulajdonsagok{" +
                "koltseg=" + koltseg +
                ", szint=" + szint +
                ", osz=" + osz +
                ", nev='" + nev + '\'' +
                '}';
    }
}