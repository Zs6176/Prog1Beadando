package org.example.jatekElemek;

import org.example.jatekElemek.tulajdonsagok.Moral;
import org.example.jatekElemek.tulajdonsagok.Tamadas;
import org.example.jatekElemek.tulajdonsagok.Tudas;

import java.util.Arrays;

public class Hos {
    private Varazslat varazslat[]=new Varazslat[5];
    private Tulajdonsagok tulajdonsagok[] = new Tulajdonsagok[6];
    private Egyseg egysegek[] =new Egyseg[5];
    private int egysegSzam=0;
    private final int meghaltak=1;
    private int atvete=0;
    private int mana=0;
    private int manamax=0;
    private double tamadas=0;

    public int getManamax() {
        return manamax;
    }



    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
        if(this.manamax==0){
            this.manamax=mana;
        }
    }

    public double getTamadas() {
        return tamadas;
    }

    public void setTamadas(double tamadas) {
        this.tamadas = tamadas;
    }

    public int getAtvete() {
        return atvete;
    }
    public void setAtvete(int atvete) {
        this.atvete = atvete;
    }
    public int getMeghaltak() {
        return meghaltak;
    }
    public Varazslat[] getVarazslat() {
        return varazslat;
    }
    public void setVarazslat(Varazslat varazslat,int i) {
        this.varazslat[i] = varazslat;
    }
    public Tulajdonsagok[] getTulajdonsagok() {
        return tulajdonsagok;
    }
    public void setTulajdonsagok(Tulajdonsagok tulajdonsagok, int i) {
        this.tulajdonsagok[i] = tulajdonsagok;
    }
    public Egyseg[] getEgysegek() {
        return egysegek;
    }
    public void setEgysegek(Egyseg egysegek,int i) {
        this.egysegek[i] = egysegek;
    }
    public int getEgysegSzam() {
        return egysegSzam;
    }
    public void setEgysegSzam(int egysegSzam) {
        this.egysegSzam = egysegSzam;
    }

    public Hos(){};

    public void varazslatFeltolt(Varazslat varazslat){
        for (int i=0;i<getVarazslat().length;i++){
            if(getVarazslat()[i]==null){
                if(varazslat.isKivalasztva()==false){

                }else {
                    setVarazslat(varazslat, i);
                    break;
                }
            }else if(getVarazslat()[i].getNev()==varazslat.getNev()){
                if(varazslat.isKivalasztva()==false){
                    setVarazslat(null,i);
                }else {
                    setVarazslat(varazslat, i);
                    break;
                }
            }else{
                continue;
            }
        }
    }
    public void egysegFeltolt(Egyseg egyseg){
        for (int i = 0; i < getEgysegek().length; i++) {
            if (getEgysegek()[i] == null) {
                if(egyseg.getDarabszam()==0){
                }else {
                    setEgysegek(egyseg, i);
                    ertekadas(egyseg,egyseg.getDarabszam());
                    break;                }
            } else if(getEgysegek()[i].getNev() == egyseg.getNev()) {
                if(egyseg.getDarabszam()==0){
                    setEgysegek(null,i);
                    break;
                }else {
                    setEgysegek(egyseg, i);
                    ertekadas(egyseg,egyseg.getDarabszam());
                    break;
                }
            } else {
                continue;
            }
        }
    }
    public void tulajdonsagFeltolt(Tulajdonsagok tulajdonsagok){
        for (int i=0;i<getTulajdonsagok().length;i++){
            if(getTulajdonsagok()[i]==null){
                setTulajdonsagok(tulajdonsagok,i);
                if(getTulajdonsagok()[i].getNev()=="Tudas"){
                    setMana(((Tudas)getTulajdonsagok()[i]).getManapontNovel());
                }
                break;
            }else if(getTulajdonsagok()[i].getNev()==tulajdonsagok.getNev()){
                setTulajdonsagok(tulajdonsagok,i);
                if(getTulajdonsagok()[i].getNev()=="Tudas"){
                    setMana(((Tudas)getTulajdonsagok()[i]).getManapontNovel());
                }
                break;
            }else{
                continue;
            }
        }
    }

    public void egysegekHalala(){
        for (int i = egysegSzam-1; i >0 ; i--) {
            for (int j = 0; j < i; j++) {
                if(egysegek[j].getEletero()<egysegek[j+1].getEletero()) {
                    Egyseg egyseg = egysegek[j];
                    egysegek[j] = egysegek[j + 1];
                    egysegek[j + 1] = egyseg;
                }
            }
        }
        for (int i = 0; i < egysegek.length; i++) {
            if(egysegek[i]!=null){
                if(egysegek[i].getEletero()<=0){
                    egysegek[i]=null;
                }
            }
        }
        egysegekSzama();
    }

    public void egysegekSzama(){
        egysegSzam=0;
        for (int i=0;i<getEgysegek().length;i++){
            if(getEgysegek()[i]!=null){
                egysegSzam++;
            }
        }
    }

    public int ossesit(){
        int ossz=0;
        this.egysegSzam=0;
        for (int i=0; i<getTulajdonsagok().length;i++){
            if(getTulajdonsagok()[i]==null){
                continue;
            }else
            ossz+=getTulajdonsagok()[i].getOsz();
        }
        for (int i=0;i<getVarazslat().length;i++){
            if(getVarazslat()[i]==null){
                continue;
            }else
                ossz+=getVarazslat()[i].getKoltseg();
        }
        for (int i=0;i<getEgysegek().length;i++){
            if(getEgysegek()[i]==null){
                continue;
            }else
                ossz+=getEgysegek()[i].getKoltseg();
                this.egysegSzam++;
        }
        return ossz;
    }

    public void darabModosit(Egyseg egyseg,int darab){
        egyseg.darabszamModosit(darab);
        for(int i=0;i< tulajdonsagok.length;i++){
            switch (tulajdonsagok[i].getNev()){
                case "Tamadas": {
                    double szam = ((double) egyseg.getMinSebzes()) * (((Tamadas) tulajdonsagok[i]).getEgysegSebzesNovesel());
                    egyseg.setMinSebzes((int) szam, false);
                    szam = ((double) egyseg.getMaxSebzes()) * (((Tamadas) tulajdonsagok[i]).getEgysegSebzesNovesel());
                    egyseg.setMaxSebzes((int) szam, false);
                    tamadas = ((Tamadas) tulajdonsagok[i]).getEgysegSebzesNovesel();
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }

    public void ertekadas(Egyseg egyseg,int darab){
        egyseg.darabszamModosit(darab);
        for(int i=0;i< tulajdonsagok.length;i++){
            switch (tulajdonsagok[i].getNev()){
                case "Moral" :{
                    egyseg.setKezdemenyezes(egyseg.getKezdemenyezes() + (((Moral) tulajdonsagok[i]).getKezdemenyezesNovel()));
                    break;
                }
                case "Tamadas": {
                    double szam = ((double) egyseg.getMinSebzes()) * (((Tamadas) tulajdonsagok[i]).getEgysegSebzesNovesel());
                    egyseg.setMinSebzes((int) szam, false);
                    szam = ((double) egyseg.getMaxSebzes()) * (((Tamadas) tulajdonsagok[i]).getEgysegSebzesNovesel());
                    egyseg.setMaxSebzes((int) szam, false);
                    tamadas = ((Tamadas) tulajdonsagok[i]).getEgysegSebzesNovesel();
                    break;
                }
                default:{
                    break;
                }
            }
        }

    }

    public String toStringTulajdonsag() {
        for (int i=0;i<tulajdonsagok.length;i++)
            if(getTulajdonsagok()[i]==null){
                continue;
            }else {
                System.out.println("H "+i+" " + getTulajdonsagok()[i].toString());
            }
        return "0";
    }
    public String toStringVarazslat() {
        for (int i=0;i<varazslat.length;i++)
            if(getVarazslat()[i]==null){
                continue;
            }else {
                System.out.println("H "+i+" " + getVarazslat()[i].toString());
            }
        return "0";
    }
    public String toStringEgyseg() {
        for (int i=0;i<egysegek.length;i++)
            if(getEgysegek()[i]==null){
                continue;
            }else {
                System.out.println("Darabszam "+getEgysegSzam()+" hely "+i+" " + getEgysegek()[i].toString());
            }
        return "0";
    }
}
