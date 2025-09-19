package org.example.jatekElemek;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class KepekElerese {

    public final static Image alap = new Image("file:img/alapMezo.png");
    public final static Image lephet = new Image("file:img/lephet.png");
    public final static Image farmerJo = new Image("file:img/egysegek/farmerJo.png");
    public final static Image farmerRosz = new Image("file:img/egysegek/farmerRosz.png");
    public final static Image ijaszJo = new Image("file:img/egysegek/ijaszJo.png");
    public final static Image ijaszRosz = new Image("file:img/egysegek/ijaszRosz.png");
    public final static Image griffJo = new Image("file:img/egysegek/griffJo.png");
    public final static Image griffRosz = new Image("file:img/egysegek/griffRosz.png");
    public final static Image assasinJo = new Image("file:img/egysegek/assasinJo.png");
    public final static Image assasinRosz = new Image("file:img/egysegek/assasinRosz.png");
    public final static Image iceManJo = new Image("file:img/egysegek/iceManJo.png");
    public final static Image iceManRosz = new Image("file:img/egysegek/iceManRosz.png");
    public final static Image villamcsapas = new Image("file:img/varazslatok/villamcsapas.png");
    public final static Image tuzlabda = new Image("file:img/varazslatok/tuzlabda.png");
    public final static Image feltamasztas = new Image("file:img/varazslatok/feltamasztas.png");
    public final static Image fagyasztas = new Image("file:img/varazslatok/fagyasztas.png");
    public final static Image hosTamad = new Image("file:img/hosTamad.png");


    public static Image kivalasztJo(Egyseg egyseg) {
        switch (egyseg.getNev()) {
            case "Foldmuves":
                return farmerJo;
            case "Ijasz":
                return ijaszJo;
            case "Griff":
                return griffJo;
            case "Bergyilkos":
                return assasinJo;
            case "IceMan":
                return iceManJo;
            default:
                break;
        }
        return null;
    }
    public static Image kivalasztRosz(Egyseg egyseg) {
        switch (egyseg.getNev()) {
            case "Foldmuves":
                return farmerRosz;
            case "Ijasz":
                return ijaszRosz;
            case "Griff":
                return griffRosz;
            case "Bergyilkos":
                return assasinRosz;
            case "IceMan":
                return iceManRosz;
            default:
                break;
        }
        return null;
    }

    public static Image Vrazslat(Varazslat v){
        switch (v.getNev()){
            case "Vilamcsapas":
                return villamcsapas;
            case "Tuzlabda":
                return tuzlabda;
            case "Feltamasztas":
                return feltamasztas;
            case "Fagyasztas":
                return fagyasztas;
            default:
                return null;
        }
    }

}
