package org.example.jatekElemek;

public class Egyseg {
    private int darabszam;
    private int sebeseg;
    private int eletero;
    private int egyEletero;
    private int koltseg;
    private int maxSebzes;
    private int minSebzes;
    private String nev;
    private int kezdemenyezes;
    private boolean lepet = false;
    private boolean felhejezve=false;
    private boolean joOldal;
    private int fagyasztva=0;
    private int eredetiDarab;
    private int eredetiMaxSzam;
    private int eredetiMinSzam;
    private boolean viszatamadot = false;

    public boolean isViszatamadot() {
        return viszatamadot;
    }

    public void setViszatamadot(boolean viszatamadot) {
        this.viszatamadot = viszatamadot;
    }

    public int getEredetiMaxSzam() {
        return eredetiMaxSzam;
    }

    public void setEredetiMaxSzam(int eredetiMaxSzam) {
        this.eredetiMaxSzam = eredetiMaxSzam;
    }

    public int getEredetiMinSzam() {
        return eredetiMinSzam;
    }

    public void setEredetiMinSzam(int eredetiMinSzam) {
        this.eredetiMinSzam = eredetiMinSzam;
    }

    public int getEredetiDarab() {
        return eredetiDarab;
    }

    public void setEredetiDarab(int eredetiDarab) {
        this.eredetiDarab = eredetiDarab;
    }

    public int getFagyasztva() {
        return fagyasztva;
    }

    public void setFagyasztva(int fagyasztva) {
        this.fagyasztva = fagyasztva;
    }

    public int getEgyEletero() {
        return egyEletero;
    }

    public void setEgyEletero(int egyEletero) {
        this.egyEletero = egyEletero;
    }

    public int getDarabszam() {
        return darabszam;
    }

    public void setDarabszam(int darabszam) {
        if(darabszam>=250){
            this.darabszam=250;
        }else if(darabszam<0){
            this.darabszam=0;
        }else{
            this.darabszam=darabszam;
        }
    }

    public int getEletero() {
        return eletero;
    }

    public void setEletero(int eletero,boolean kell) {
        if(kell) {
            eletero *= getDarabszam();
            this.eletero = eletero;
        }else {
            this.eletero = eletero;
        }
    }

    public int getKoltseg() {
        return koltseg;
    }

    public void setKoltseg(int koltseg) {
        koltseg*=getDarabszam();
        this.koltseg = koltseg;
    }

    public int getMaxSebzes() {
        return maxSebzes;
    }

    public void setMaxSebzes(int maxSebzes,boolean kell) {
        if(kell) {
            maxSebzes *= getDarabszam();
            this.maxSebzes = maxSebzes;
        }else {
            this.maxSebzes = maxSebzes;
        }
    }

    public int getMinSebzes() {
        return minSebzes;
    }

    public void setMinSebzes(int minSebzes,boolean kell) {
        if(kell) {
            minSebzes *= getDarabszam();
            this.minSebzes = minSebzes;
        }else {
            this.minSebzes = minSebzes;
        }
    }

    public int getSebeseg() {
        return sebeseg;
    }

    public void setSebeseg(int sebeseg) {
        this.sebeseg = sebeseg;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getKezdemenyezes() {
        return kezdemenyezes;
    }

    public void setKezdemenyezes(int kezdemenyezes) {
        this.kezdemenyezes = kezdemenyezes;
    }

    public boolean isLepet() {
        return lepet;
    }

    public void setLepet(boolean lepet) {
        this.lepet = lepet;
    }

    public boolean isFelhejezve() {
        return felhejezve;
    }

    public void setFelhejezve(boolean felhejezve) {
        this.felhejezve = felhejezve;
    }

    public boolean isJoOldal() {
        return joOldal;
    }

    public void setJoOldal(boolean joOldal) {
        this.joOldal = joOldal;
    }

    public Egyseg() {
    }

    public Egyseg(int egyEletero,boolean joOldal ,boolean felhejezve,boolean lepet,int kezdemenyezes,String nev ,int darabszam, int sebeseg, int eletero, int koltseg, int maxSebzes, int minSebzes) {
        setNev(nev);
        setDarabszam(darabszam);
        setEredetiDarab(darabszam);
        setSebeseg(sebeseg);
        setEletero(eletero,true);
        setKoltseg(koltseg);
        setEredetiMaxSzam(maxSebzes);
        setMaxSebzes(maxSebzes,true);
        setEredetiMinSzam(minSebzes);
        setMinSebzes(minSebzes,true);
        setLepet(lepet);
        setKezdemenyezes(kezdemenyezes);
        setFelhejezve(felhejezve);
        setJoOldal(joOldal);
        setEgyEletero(egyEletero);
    }

    public void darabszamModosit(int darabszam){
        setDarabszam(darabszam);
        setEletero(getEgyEletero(),true);
            setDarabszam(darabszam+1);
            setMinSebzes(eredetiMinSzam, true);
            setMaxSebzes(eredetiMaxSzam, true);
    }

    @Override
    public String toString() {
        return "Egyseg{" +
                "darabszam=" + darabszam +
                ", sebeseg=" + sebeseg +
                ", eletero=" + eletero +
                ", koltseg=" + koltseg +
                ", maxSebzes=" + maxSebzes +
                ", minSebzes=" + minSebzes +
                ", nev='" + nev + '\'' +
                ", kezdemenyezes=" + kezdemenyezes +
                ", lepet=" + lepet +
                ", felhejezve=" + felhejezve +
                ", joOldal=" + joOldal +
                '}';
    }
}
