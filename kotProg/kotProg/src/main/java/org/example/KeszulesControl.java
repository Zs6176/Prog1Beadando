package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.example.jatekElemek.*;
import org.example.jatekElemek.egysegek.*;
import org.example.jatekElemek.tulajdonsagok.*;
import org.example.jatekElemek.varazslatok.Fagyasztas;
import org.example.jatekElemek.varazslatok.Feltamasztas;
import org.example.jatekElemek.varazslatok.Tuzlabda;
import org.example.jatekElemek.varazslatok.Vilamcsapas;

import java.io.IOException;

public class KeszulesControl{

    public Slider slideMoral;
    public Button btTulSave;
    public Slider slideVarazsero;
    public Slider slideTudas;
    public Slider slideVedekezes;
    public Slider slideSzerencse;
    public Slider slideTamadas;
    public Text textPenz;
    public CheckBox checkBVarazstuz;
    public CheckBox checkBVarazsFel;
    public CheckBox checkBVarazsVilam;
    public Button btVarSave;
    public Label LableVarKolt;
    public Text textVarPenz;
    public Button btStart;
    public Label lablePenz;
    public Text textTulPenz;
    public Button btNextPl;
    public Slider slideJeg;
    public Slider slideBer;
    public Slider slideGriff;
    public Slider slideIj;
    public Label labelFoldDb;
    public Slider slideFold;
    public CheckBox checkBVarazsFagy;
    public Label labelIceDb;
    public Label labelAssiDb;
    public Label labelGriffdb;
    public Label labelIjaszDb;

    Egyseg foldmuves;
    Egyseg ijasz;
    Egyseg griff;
    Egyseg bergyilkos;
    Egyseg iceMan;
    Tulajdonsagok tamadas;
    Tulajdonsagok vedekezes;
    Tulajdonsagok varazsero;
    Tulajdonsagok tudas;
    Tulajdonsagok moral;
    Tulajdonsagok szerencse;
    Varazslat vilamcsapas;
    Varazslat tuzlabda;
    Varazslat feltamasztas;
    Varazslat fagyasztas;
    Hos hos = new Hos();
    Hos gonosz = new Hos();
    Player player = new Player();

    public static int playerszam;
    public static int penz;
    int aktualisP;
    public static int gepElen=0;
    int koltsegOssz;

    Palya palya;

    public void mentes(){
        tamadas = new Tamadas((int)slideTamadas.getValue());
        hos.tulajdonsagFeltolt(tamadas);
        vedekezes = new Vedekezes((int)slideVedekezes.getValue());
        hos.tulajdonsagFeltolt(vedekezes);
        varazsero = new Varazsero((int)slideVarazsero.getValue());
        hos.tulajdonsagFeltolt(varazsero);
        tudas = new Tudas((int)slideTudas.getValue());
        hos.tulajdonsagFeltolt(tudas);
        moral = new Moral((int)slideMoral.getValue());
        hos.tulajdonsagFeltolt(moral);
        szerencse = new Szerencse((int)slideSzerencse.getValue());
        hos.tulajdonsagFeltolt(szerencse);

        boolean joOldal;
        if(gepElen==0){
            if(playerszam==0)joOldal=true;
            else joOldal=false;
        }else {
            joOldal=true;
        }


        vilamcsapas = new Vilamcsapas(checkBVarazsVilam.isSelected(),joOldal);
        hos.varazslatFeltolt(vilamcsapas);
        tuzlabda = new Tuzlabda(checkBVarazstuz.isSelected(),joOldal);
        hos.varazslatFeltolt(tuzlabda);
        feltamasztas = new Feltamasztas(checkBVarazsFel.isSelected(),joOldal);
        hos.varazslatFeltolt(feltamasztas);
        fagyasztas = new Fagyasztas((checkBVarazsFagy.isSelected()),joOldal);
        hos.varazslatFeltolt(fagyasztas);

        foldmuves =new Foldmuves(joOldal,(int) slideFold.getValue());
        hos.egysegFeltolt(foldmuves);
        ijasz =new Ijasz(joOldal,(int) slideIj.getValue());
        hos.egysegFeltolt(ijasz);
        griff =new Griff(joOldal,(int) slideGriff.getValue());
        hos.egysegFeltolt(griff);
        bergyilkos =new Bergyilkos(joOldal,(int) slideBer.getValue());
        hos.egysegFeltolt(bergyilkos);
        iceMan =new IceMan(joOldal,(int) slideJeg.getValue());
        hos.egysegFeltolt(iceMan);
    }

    public void penzFeltolt(){
        aktualisP=penz;
        aktualisP-= hos.ossesit();

        if(playerszam==0){
            if (hos.getEgysegek()[0] == null || aktualisP < 0) {
                btNextPl.setDisable(true);
            } else {
                btNextPl.setDisable(false);
            }
        }else {
            if (hos.getEgysegek()[0] == null || aktualisP < 0) {
                btStart.setDisable(true);
            } else {
                btStart.setDisable(false);
            }
        }
        textPenz.setText(Integer.toString(aktualisP));
    }

    public void btStartClick(ActionEvent actionEvent) throws IOException{
        mentes();
        penzFeltolt();
        if(hos.getEgysegek()[0]==null || aktualisP<0) {
            btStart.setDisable(true);
        }else{
            btStart.setDisable(false);
            if(gepElen==1){
                gepMeghiv();
                Player.hosList.add(hos);
                Player.hosList.add(gonosz);
            }else {
                Player.hosList.add(hos);
            }
            App.setRoot("Palya");
        }
    }
    public void btNextPlClick(ActionEvent actionEvent) throws IOException {
        mentes();
        penzFeltolt();
        if(hos.getEgysegek()[0]==null || aktualisP<0) {
            btNextPl.setDisable(true);
        }else {
            btNextPl.setDisable(false);
            player.hosFeltolt(hos,playerszam);
            playerszam++;
            App.setRoot("Keszules");
        }
    }

    public void btTulSaveClick(ActionEvent actionEvent) {
        mentes();
        penzFeltolt();
    }
    public void btVarSaveClick(ActionEvent actionEvent) {
        mentes();
        penzFeltolt();
    }
    public void btEgySaveClick(ActionEvent actionEvent){
        mentes();
        penzFeltolt();
    }

    public void sliderMoClick(MouseEvent mouseEvent) {
        int ossz = 0;
        ossz+= Tulajdonsagok.koltegNovelCiklus((int)slideMoral.getValue());
        ossz+= Tulajdonsagok.koltegNovelCiklus((int)slideSzerencse.getValue());
        ossz+= Tulajdonsagok.koltegNovelCiklus((int)slideTamadas.getValue());
        ossz+= Tulajdonsagok.koltegNovelCiklus((int)slideTudas.getValue());
        ossz+= Tulajdonsagok.koltegNovelCiklus((int)slideVarazsero.getValue());
        ossz+= Tulajdonsagok.koltegNovelCiklus((int)slideVedekezes.getValue());
        textTulPenz.setText(String.valueOf(ossz));
    }

    public void checkBVarazsVilamAction(ActionEvent actionEvent) {
        int osz= Integer.parseInt(textVarPenz.getText());
        vilamcsapas = new Vilamcsapas(checkBVarazsVilam.isSelected());
        if(!checkBVarazsVilam.isSelected()){
            osz-=vilamcsapas.getKoltseg();
        }else{
            osz+=vilamcsapas.getKoltseg();
        }
        textVarPenz.setText(Integer.toString(osz));

    }
    public void checkBVarazsTuzAction(ActionEvent actionEvent) {
        int osz = Integer.parseInt(textVarPenz.getText());
        tuzlabda = new Tuzlabda(checkBVarazstuz.isSelected());
        if(!checkBVarazstuz.isSelected()){
            osz-=tuzlabda.getKoltseg();
        }else{
            osz+=tuzlabda.getKoltseg();
        }
        textVarPenz.setText(Integer.toString(osz));

    }
    public void checkBVarazsFelAction(ActionEvent actionEvent) {
        int osz= Integer.parseInt(textVarPenz.getText());
        feltamasztas = new Feltamasztas(checkBVarazsFel.isSelected());
        if(!checkBVarazsFel.isSelected()){
            osz-=feltamasztas.getKoltseg();
        }else{
            osz+=feltamasztas.getKoltseg();
        }
        textVarPenz.setText(Integer.toString(osz));
    }
    public void checkBVarazsFagyAction(ActionEvent actionEvent) {
        int osz= Integer.parseInt(textVarPenz.getText());
        fagyasztas= new Feltamasztas(checkBVarazsFagy.isSelected());
        if(!checkBVarazsFagy.isSelected()){
            osz-=fagyasztas.getKoltseg();
        }else{
            osz+=fagyasztas.getKoltseg();
        }
        textVarPenz.setText(Integer.toString(osz));
    }

    public void slFolClick(MouseEvent mouseEvent) {
        labelFoldDb.setText(String.valueOf((int)slideFold.getValue()));
    }

    public void slIjClick(MouseEvent mouseEvent) {
        labelIjaszDb.setText(String.valueOf((int)slideIj.getValue()));
    }

    public void slGriffClick(MouseEvent mouseEvent) {
        labelGriffdb.setText(String.valueOf((int)slideGriff.getValue()));
    }

    public void slBerClick(MouseEvent mouseEvent) {
        labelAssiDb.setText(String.valueOf((int)slideBer.getValue()));
    }

    public void slJegClick(MouseEvent mouseEvent) {
        labelIceDb.setText(String.valueOf((int)slideJeg.getValue()));
    }

    public void gepMeghiv(){
        double random = Math.random();
        if(random<0.25){
            gepFeltolt(7,4,2,2,5,6,false,false,true,false,2,13,10,5,5);
        }else if (random>=0.25&&random<0.5){
            gepFeltolt(3,5,7,7,4,4,true,true,true,true,1,1,15,0,3);
        }else if(random>=0.5&&random<0.75){
            gepFeltolt(5,5,5,5,5,5,true,true,true,false,0,15,10,5,2);
        }else {
            gepFeltolt(6,6,1,1,8,10,false,false,false,false,0,10,4,15,3);
        }

        aktualisP=penz;
        aktualisP-= gonosz.ossesit();
    }


    public void gepFeltolt(int tamadas,int vedekez, int varazsero,int tudas,int moral,int szerencse,
                           boolean vilamcsapas,boolean tuzlabda,boolean feltamadas,boolean fagyasztas,
                           int foldmuves,int ijasz,int griff,int bergyilkos,int iceman){
        this.tamadas = new Tamadas(tamadas);
        gonosz.tulajdonsagFeltolt(this.tamadas);
        this.vedekezes = new Vedekezes(vedekez);
        gonosz.tulajdonsagFeltolt(this.vedekezes);
        this.varazsero = new Varazsero(varazsero);
        gonosz.tulajdonsagFeltolt(this.varazsero);
        this.tudas = new Tudas(tudas);
        gonosz.tulajdonsagFeltolt(this.tudas);
        this.moral = new Moral(moral);
        gonosz.tulajdonsagFeltolt(this.moral);
        this.szerencse = new Szerencse(szerencse);
        gonosz.tulajdonsagFeltolt(this.szerencse);

        this.vilamcsapas = new Vilamcsapas(vilamcsapas,false);
        gonosz.varazslatFeltolt(this.vilamcsapas);
        this.tuzlabda = new Tuzlabda(tuzlabda,false);
        gonosz.varazslatFeltolt(this.tuzlabda);
        this.feltamasztas = new Feltamasztas(feltamadas,false);
        gonosz.varazslatFeltolt(this.feltamasztas);
        this.fagyasztas = new Fagyasztas(fagyasztas,false);
        gonosz.varazslatFeltolt(this.fagyasztas);

        this.foldmuves =new Foldmuves(false,foldmuves);
        gonosz.egysegFeltolt(this.foldmuves);
        this.ijasz =new Ijasz(false,ijasz);
        gonosz.egysegFeltolt(this.ijasz);
        this.griff =new Griff(false,griff);
        gonosz.egysegFeltolt(this.griff);
        this.bergyilkos =new Bergyilkos(false,bergyilkos);
        gonosz.egysegFeltolt(this.bergyilkos);
        this.iceMan =new IceMan(false,iceman);
        gonosz.egysegFeltolt(this.iceMan);

    }

}



