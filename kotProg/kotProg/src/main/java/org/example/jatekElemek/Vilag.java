package org.example.jatekElemek;

public class Vilag {
    private int sorok;
    private int oszlopok;
    private Platform platforms[][];

    public Vilag(int oszlopok,int sorok){
        setOszlopok(oszlopok);
        setSorok(sorok);
        generate(getSorok(),getOszlopok());
    }

    public int getSorok() {
        return sorok;
    }

    public void setSorok(int sorok) {
        this.sorok = sorok;
    }

    public int getOszlopok() {
        return oszlopok;
    }

    public void setOszlopok(int oszlopok) {
        this.oszlopok = oszlopok;
    }

    public Platform[][] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Egyseg nev,int i,int j,int oldal) {
        this.platforms[i][j].setEgysegFajta(nev);
        this.platforms[i][j].setI(i);
        this.platforms[i][j].setJ(j);
        this.platforms[i][j].setOldal(oldal);
    }

    public void generate(int sor, int oszlopok){
        platforms = new Platform[getSorok()][getOszlopok()];
        for(int i=0;i<getSorok();i++) {
            for(int j=0;j<getOszlopok();j++) {
                platforms[i][j] = new Platform();
            }
        }
    }


}
