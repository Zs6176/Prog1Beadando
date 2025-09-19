package org.example;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.example.jatekElemek.*;
import org.example.jatekElemek.tulajdonsagok.Szerencse;
import org.example.jatekElemek.tulajdonsagok.Tamadas;
import org.example.jatekElemek.tulajdonsagok.Varazsero;
import org.example.jatekElemek.tulajdonsagok.Vedekezes;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Palya implements Initializable {

    public AnchorPane acpVilagLetrehoz;
    public AnchorPane acpLeftHos;
    public AnchorPane acpRightHos;
    public AnchorPane acpFelso;

    private final Vilag vilag = new Vilag(12, 10);
    private final Vilag balHos = new Vilag(3, 10);
    private final Vilag jobbHos = new Vilag(3, 10);
    public  final Vilag felso =new Vilag(20,1);
    private final ImageView[][] imageViews = new ImageView[vilag.getSorok()][vilag.getOszlopok()];
    private final ImageView[][] imageHosBal = new ImageView[balHos.getSorok()][balHos.getOszlopok()];
    private final ImageView[][] imageHosJobb = new ImageView[jobbHos.getSorok()][jobbHos.getOszlopok()];
    private final ImageView[][] imageFelso = new ImageView[felso.getSorok()][felso.getOszlopok()];
    final Hos gonoszNagyur = Player.hosList.get(1);
    final Hos hos = Player.hosList.get(0);
    public VBox palyaVBox;
    Egyseg egyseg[] = new Egyseg[11];
    Varazslat varazslat;
    private static int lehejezetek = 0;
    int korok = 1;
    boolean hossTamad=false;
    public static boolean gepElen=false;
    String szoveg;



    private List<Platform> egysegekPozicioja = new LinkedList<>();

    private void gyoztesHirdetes() {
        boolean hos = false;
        boolean gonosz = false;
        for (int i = 0; i < egyseg.length; i++) {
            if (egyseg[i] != null) {
                if (egyseg[i].isJoOldal()) {
                    hos = true;
                } else if (!egyseg[i].isJoOldal()) {
                    gonosz = true;
                }
            }
        }
        Label label = new Label();
        Button button = new Button();
        label.setFont(new Font(50));
        label.setLayoutX(800);
        label.setLayoutY(350);
        button.setFont(new Font(40));
        button.setLayoutX(800);
        button.setLayoutY(450);
        button.setOnMouseClicked(MouseEvent -> {
            System.exit(0);
        });
        if(!hos&&!gonosz) {
            palyaVBox.getChildren().clear();
                label.setText("Döntetlen lett");
                button.setText("Döntetlen!");
            palyaVBox.getChildren().add(label);
            palyaVBox.getChildren().add(button);
        }else if(!gonosz &&hos){
            palyaVBox.getChildren().clear();
            if(gepElen) {
                label.setText("Nyertel megmenteted a világot!");
                button.setText("Nertél!");
            }else {
                label.setText("Az egyes játékos nyert");
                button.setText("Vége!");
            }
            palyaVBox.getChildren().add(label);
            palyaVBox.getChildren().add(button);
        }else if(!hos&&gonosz){
            palyaVBox.getChildren().clear();
            if(gepElen) {
                label.setText("Vesztetel elpusztult a világ!");
                button.setText("Vesztetel");
            }else{
                label.setText("Az kettes játékos nyert");
                button.setText("Vége!");
            }
            palyaVBox.getChildren().add(label);
            palyaVBox.getChildren().add(button);
        }
    }

    private void inicializal(){
        int k=0;
        egysegekPozicioja.clear();
        for (int i = 0; i < vilag.getSorok(); i++) {
            for (int j = 0; j < vilag.getOszlopok(); j++) {
                if(vilag.getPlatforms()[i][j].getEgysegFajta()!=null){
                    egysegekPozicioja.add(vilag.getPlatforms()[i][j]);
                }
            }
        }
        egysegekPozicioja.sort(new Comparator<Platform>() {
            @Override
            public int compare(Platform platform, Platform t1) {
                return Integer.compare(t1.getEgysegFajta().getKezdemenyezes(),platform.getEgysegFajta().getKezdemenyezes());
            }
        });
        /*
        for (Platform p:egysegekPozicioja) {
            System.out.println(p.toString());
        }
         */
    }

    public Set<Platform> kifejt(Set<Platform> halmaz){
        Set<Platform> ujHalmaz = new HashSet<>();

        for(Platform p:halmaz){
            Platform p1 = new Platform(p.getI()-1,p.getJ());
            Platform p2 = new Platform(p.getI()+1,p.getJ());
            Platform p3 = new Platform(p.getI(),p.getJ()-1);
            Platform p4 = new Platform(p.getI(),p.getJ()+1);

            if(!egysegekPozicioja.contains(p1)){
                ujHalmaz.add(p1);
            }
            if(!egysegekPozicioja.contains(p2)){
                ujHalmaz.add(p2);
            }
            if(!egysegekPozicioja.contains(p3)) {
                ujHalmaz.add(p3);
            }
            if(!egysegekPozicioja.contains(p4)){
                ujHalmaz.add(p4);
            }
        }
        return ujHalmaz;
    }

    private int mehetEOda(int i,int j, ImageView img){
        Set<Platform> vizsgalando = new HashSet<>();
        vizsgalando.add(egysegekPozicioja.get(egyforma()));
        int koltseg=egysegekPozicioja.get(egyforma()).getEgysegFajta().getSebeseg();
        koltseg++;
        while (true) {
            vizsgalando = kifejt(vizsgalando);
            koltseg--;
            for (Platform p : vizsgalando) {
                if(koltseg==0){
                    szoveg="Ide nem léphetsz túl messze van";
                    return 0;
                }
                if(p.getI()>=vilag.getSorok()||p.getJ()>=vilag.getOszlopok()||p.getI()<0||p.getJ()<0){
                    continue;
                }
                if (p.getI() == i && p.getJ() == j) {
                    return koltseg;
                }
            }
        }

    }

    private int egyforma(){
        for (Egyseg e:egyseg) {

            if(e!=null&&e.isLepet()) {
                continue;
            }
            for (int i = 0; i < egysegekPozicioja.size(); i++) {
                if(e!=null&&egysegekPozicioja.get(i)!=null){
                    if(e == egysegekPozicioja.get(i).getEgysegFajta()){
                        if(e.isLepet()==egysegekPozicioja.get(i).getEgysegFajta().isLepet()&&e.isJoOldal()==egysegekPozicioja.get(i).getEgysegFajta().isJoOldal()){
                            return i;
                        }
                    }
                }
            }
        }
        return 0;
    }

    private int soronKovetkezoPlatform(){
        int index=0;
        for (Platform p:egysegekPozicioja) {
            if(!p.getEgysegFajta().isLepet()) {
                return index;
            }
            index++;
        }
        return index;
    }

    public void felso(){
        acpFelso.getChildren().clear();
        int k=0;
        for(int i=0;i<felso.getSorok();i++) {
            for (int j = 0; j < felso.getOszlopok(); j++) {
                ImageView img = new ImageView(KepekElerese.alap);
                Label label = new Label(korok+". Kör");
                Label label1 = new Label(szoveg);
                Button button = new Button("Várakozás");
                if (egyseg.length>j &&egyseg[j] != null) {
                    if (!egyseg[j].isLepet()) {
                        if (egyseg[j].isJoOldal()) {
                            img = new ImageView(KepekElerese.kivalasztJo(egyseg[j]));
                        } else {
                            img = new ImageView(KepekElerese.kivalasztRosz(egyseg[j]));
                        }
                        label.setLayoutX(1000);
                        label1.setLayoutX(1000);
                        label1.setLayoutY(60);
                        label.setFont(new Font(30));
                        label1.setFont(new Font(15));
                        img.setFitWidth(80);
                        img.setFitHeight(80);
                        img.setX(k * 80);
                        button.setLayoutX(1400);
                        button.setLayoutY(0);
                        button.setFont(new Font(30));
                        button.setOnMouseClicked(MouseEvent->{
                            if(!kellMegLehejezni()) {
                                egyseg[soronKov()].setLepet(true);
                                if (!mindenLépet()) {
                                    korok++;
                                    osszEgyseg();
                                    reset();
                                    felso();
                                }
                                felso();
                            }
                        });
                        imageFelso[i][j] = img;
                        acpFelso.getChildren().add(img);
                        acpFelso.getChildren().add(label);
                        acpFelso.getChildren().add(label1);
                        acpFelso.getChildren().add(button);
                        k++;
                    }
                }
            }
        }
    }

    private void kepValaszto(int sor, int oszlop,int i) {
        ImageView img;
        if (egyseg[i].isJoOldal()) {
            img = new ImageView(KepekElerese.kivalasztJo(egyseg[i]));
        } else {
            img = new ImageView(KepekElerese.kivalasztRosz(egyseg[i]));
        }
        kepFelvitel(sor, oszlop, img);
        felhelyez(sor,oszlop,img);
    }

    private boolean vanMegEllenfel(boolean oldal){
        for (int i = 0; i < egyseg.length; i++) {
            if(egyseg[i]!=null){
                if(egyseg[i].isJoOldal()==oldal){
                    return true;
                }
            }
        }
        return false;
    }

    private void kepFelvitel(int sor, int oszlop, ImageView img) {
        img.setFitWidth(80);
        img.setFitHeight(80);
        img.setY(sor * 80);
        img.setX(oszlop * 80);
        final int row = sor;
        final int column = oszlop;
            img.setOnMouseClicked((MouseEvent e) -> {
                if (column <= 1 && lehejezetek < hos.getEgysegSzam()) {
                    felhelyezes(row, column, true);
                    oldalsav();
                    inicializal();
                    felso();
                    if (gepElen && lehejezetek >= hos.getEgysegSzam()) {
                        gepFelpakol();
                        meghivas();
                    }
                } else if (lehejezetek < (gonoszNagyur.getEgysegSzam() + hos.getEgysegSzam()) && lehejezetek >= hos.getEgysegSzam() && column >= 10) {
                    felhelyezes(row, column, false);
                    oldalsav();
                    inicializal();
                    felso();
                } else if (!kellMegLehejezni()) {
                    if (varazslat != null) {
                        varazslas(row, column);
                        osszEgyseg();
                        felso();
                        oldalsav();
                        inicializal();
                        gyoztesHirdetes();
                        meghivas();
                    } else if (hossTamad) {
                        hosTamad(row, column);
                        osszEgyseg();
                        felso();
                        oldalsav();
                        inicializal();
                        gyoztesHirdetes();
                        meghivas();
                    } else if (vilag.getPlatforms()[row][column].getEgysegFajta() == null) {
                        if (mindenLépet()) {
                            mozgatas(row, column, hos, gonoszNagyur);
                            meghivas();
                            if (gepElen) {
                                if (!egyseg[soronKov()].isJoOldal()) {
                                    gepMozgatasVagyTamadas();
                                   meghivas();
                                }
                            }
                            if (!mindenLépet()) {
                                korok++;
                                reset();
                                felso();
                            }
                        }
                    } else {
                        if(e.getButton() == MouseButton.SECONDARY){
                            tamadas(row, column, true);
                        }else {
                            tamadas(row, column, false);
                        }
                        meghivas();
                        if (gepElen) {
                            if (!egyseg[soronKov()].isJoOldal()) {
                                meghivas();
                            }
                        }
                        if (!mindenLépet()) {
                            korok++;
                            osszEgyseg();
                            reset();
                            felso();
                        }
                    }
                }
            });
    }

    private boolean kellMegLehejezni(){
        for (int i = 0; i < egyseg.length; i++) {
            if(egyseg[i]==null)continue;
            if(!egyseg[i].isFelhejezve()){
                return true;
            }
        }
        return false;
    }

    private boolean mindenLépet(){
        for (Egyseg value : egyseg) {
            if (value == null) continue;
            if (!value.isLepet()) {
                return true;
            }
        }
        return false;
    }

    private void felhelyez(int sor,int oszlop,ImageView img){
        imageViews[sor][oszlop] = img;
        acpVilagLetrehoz.getChildren().add(img);
    }

    private void oldalValasztas(int sor, int oszlop, int index) {
        if (egyseg[index].isJoOldal()) {
            vilag.setPlatforms(egyseg[index], sor, oszlop, 0);
        } else {
            vilag.setPlatforms(egyseg[index], sor, oszlop, 1);
        }
    }

    private void oldalsavEgysegek(int sor, int oszlop, int kepTav, int kepMag, int xElehelyezkedes, Egyseg e, ImageView[][] oldal, AnchorPane pane,boolean jo) {
        ImageView img = new ImageView(KepekElerese.alap);
        Label label = new Label();
        Label darabb = new Label();
        Label nev = new Label();
        if (!e.isLepet()) {
            label.setText("Nem Lépet");
        } else if (e.isLepet()) {
            label.setText("Lepet");
        }
        if(jo) {img = new ImageView(KepekElerese.kivalasztJo(e));}
        else {img = new ImageView(KepekElerese.kivalasztRosz(e));}
        nev.setText(e.getNev());
        nev.setLayoutY((sor * kepTav) + kepMag);
        nev.setLayoutX(xElehelyezkedes);
        label.setLayoutY((sor * kepTav) + (kepMag + 15));
        label.setLayoutX(xElehelyezkedes);
        darabb.setText(Integer.toString(e.getDarabszam()));
        darabb.setLayoutY((sor * kepTav) + (kepMag + (15 * 2)));
        darabb.setLayoutX(xElehelyezkedes);
        img.setY(sor * kepTav);
        img.setX(xElehelyezkedes);
        img.setFitWidth(kepMag);
        img.setFitHeight(kepMag);
        oldal[sor][oszlop] = img;
        pane.getChildren().add(img);
        pane.getChildren().add(nev);
        pane.getChildren().add(label);
        pane.getChildren().add(darabb);
    }

    public void oldalsav() {
        acpLeftHos.getChildren().clear();
        acpRightHos.getChildren().clear();
        int kepTav = 125;
        int kepMagasagSzeleseg = 75;
        int jobbX = 240;
        int k=0;
        boolean fentvan=false;
        for (int j = 0; j < balHos.getOszlopok(); j++) {
            for (int i = 0; i < balHos.getSorok(); i++) {
                if (j == 0) {
                    if (egyseg[i] != null && egyseg[i].isJoOldal()) {
                        oldalsavEgysegek(k, j, kepTav, kepMagasagSzeleseg, 0, egyseg[i], imageHosBal, acpLeftHos, true);
                        k++;
                    }
                }else if(j==1){
                    if(hos.getVarazslat().length>i&&hos.getVarazslat()[i]!=null&&hos.getVarazslat()[i].isKivalasztva()){
                        ImageView img = new ImageView(KepekElerese.Vrazslat(hos.getVarazslat()[i]));
                        img.setFitHeight(75);
                        img.setFitWidth(75);
                        img.setX(100);
                        img.setY(i*kepTav);

                        final int index=i;
                        img.setOnMouseClicked(mouseEvent -> {
                            if (egyseg[soronKov()].isJoOldal()) {
                                if (hos.getAtvete() == 0)
                                    varazslat = hos.getVarazslat()[index];
                                if (mouseEvent.getButton() == MouseButton.SECONDARY)
                                    varazslat = null;
                            }
                        });

                        imageHosBal[i][j] = img;
                        acpLeftHos.getChildren().add(img);
                    }
                }else if(j==2){
                    if(!fentvan) {
                        ImageView img = new ImageView(KepekElerese.hosTamad);
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setMinHeight(75);
                        progressBar.setMinWidth(300);
                        progressBar.setLayoutX(90);
                        progressBar.setLayoutY(240);
                        progressBar.setRotate(-90);
                        double pro = (double) hos.getMana()/hos.getManamax();
                        progressBar.setProgress(pro);
                        img.setFitHeight(75);
                        img.setFitWidth(75);
                        img.setX(200);
                        img.setY(i * kepTav);

                        img.setOnMouseClicked(mouseEvent -> {
                            if (egyseg[soronKov()].isJoOldal()){
                                if (hos.getAtvete() == 0)
                                    hossTamad = true;
                                if (mouseEvent.getButton() == MouseButton.SECONDARY)
                                    hossTamad=false;
                            }
                        });

                        imageHosBal[i][j] = img;
                        acpLeftHos.getChildren().add(img);
                        acpLeftHos.getChildren().add(progressBar);
                        fentvan=true;
                    }
                }
            }
        }
        k=0;
        fentvan=false;
        for (int j = 0; j < jobbHos.getOszlopok(); j++) {
            for (int i = 0; i < jobbHos.getSorok(); i++) {
                if (j == 2) {
                    if (egyseg[i] != null && !egyseg[i].isJoOldal()) {
                        oldalsavEgysegek(k, j, kepTav, kepMagasagSzeleseg, jobbX, egyseg[i], imageHosJobb, acpRightHos, false);
                        k++;
                    }
                }else if(j==1){
                    if(gonoszNagyur.getVarazslat().length>i&&gonoszNagyur.getVarazslat()[i]!=null&&gonoszNagyur.getVarazslat()[i].isKivalasztva()) {
                        ImageView img = new ImageView(KepekElerese.Vrazslat(gonoszNagyur.getVarazslat()[i]));
                        img.setFitHeight(75);
                        img.setFitWidth(75);
                        img.setX(140);
                        img.setY(i * kepTav);

                        if(!gepElen) {
                            final int index = i;
                            img.setOnMouseClicked(mouseEvent -> {
                                if (!egyseg[soronKov()].isJoOldal()) {
                                    if (gonoszNagyur.getAtvete() == 0)
                                        varazslat = gonoszNagyur.getVarazslat()[index];
                                    if (mouseEvent.getButton() == MouseButton.SECONDARY)
                                        varazslat = null;
                                }
                            });
                        }
                        imageHosJobb[i][j] = img;
                        acpRightHos.getChildren().add(img);
                    }
                }else if(j==0){
                    if(!fentvan){
                        ImageView img = new ImageView(KepekElerese.hosTamad);
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setMinHeight(75);
                        progressBar.setMinWidth(300);
                        progressBar.setLayoutX(-70);
                        progressBar.setLayoutY(240);
                        progressBar.setRotate(-90);
                        double pro = (double) gonoszNagyur.getMana()/gonoszNagyur.getManamax();
                        progressBar.setProgress(pro);
                        img.setFitHeight(75);
                        img.setFitWidth(75);
                        img.setX(40);
                        img.setY(i * kepTav);

                        if(!gepElen) {
                            img.setOnMouseClicked(mouseEvent -> {
                                if (!egyseg[soronKov()].isJoOldal()) {
                                    if (gonoszNagyur.getAtvete() == 0)
                                        hossTamad = true;
                                    if (mouseEvent.getButton() == MouseButton.SECONDARY)
                                        hossTamad = false;
                                }
                            });
                        }
                        imageHosJobb[i][j] = img;
                        acpRightHos.getChildren().add(img);
                        acpRightHos.getChildren().add(progressBar);
                        fentvan=true;
                    }
                }
            }
        }
    }

    public void reset() {
        if(!mindenLépet()) {
            hos.setAtvete(0);
            gonoszNagyur.setAtvete(0);
            for (int i = 0; i < egyseg.length; i++) {
                if (egyseg[i] != null) {
                    if (egyseg[i].getFagyasztva() != 0) {
                        egyseg[i].setFagyasztva(0);
                        egyseg[i].setViszatamadot(false);
                        continue;
                    }
                    egyseg[i].setLepet(false);
                }
            }
            meghivas();
        }
    }

    public void felhelyezes(int i, int j,boolean joe) {
        ImageView img = new ImageView(KepekElerese.alap);
        for(int k=0;k<egyseg.length;k++){
            if(egyseg[k]!=null){
                if(vilag.getPlatforms()[i][j].getEgysegFajta() == null) {
                    if (!egyseg[k].isFelhejezve()) {
                        if (egyseg[k].isJoOldal() == joe) {
                            kepValaszto(i, j, k);
                            egyseg[k].setFelhejezve(true);
                            oldalValasztas(i, j, k);
                            szoveg=egyseg[k].getNev()+" Felhelyezve";
                            lehejezetek++;
                            break;
                        }
                    }
                }
            }else continue;
        }
    }

    public void osszEgyseg(){
        int k=0,h=0;
        for (int i=0;i< egyseg.length;i++){
            if(egyseg[i]!=null) {
                egyseg[i] = null;
            }
                if (i < hos.getEgysegSzam()) {
                    if (hos.getEgysegek()[i] != null) {

                        egyseg[i] = hos.getEgysegek()[i];
                    }
                } else {
                    if(k<gonoszNagyur.getEgysegSzam()) {
                        if (gonoszNagyur.getEgysegek()[k] != null) {
                            egyseg[i] = gonoszNagyur.getEgysegek()[k];
                            k++;
                        }
                    }
                }
        }
        for(int i=egyseg.length-1;i>0;i--){
            for (int j=0;j<i;j++){
                if(egyseg[j]==null || egyseg[j+1]==null) {
                }else if(egyseg[j].getKezdemenyezes()<egyseg[j+1].getKezdemenyezes()){
                    Egyseg egy = egyseg[j+1];
                    egyseg[j+1]=egyseg[j];
                    egyseg[j]=egy;
                }
            }
        }
    }

    private int soronKov(){
        for(int i=0;i< egyseg.length;i++){
            if(egyseg[i]==null)continue;
            if(!egyseg[i].isLepet()) return i;
        }
        return 0;
    }

    public void mozgatas(int s, int o, Hos player, Hos playerKetto) {
        ImageView img = new ImageView(KepekElerese.alap);
        if(mehetEOda(s,o,img)!=0) {
            for (int k = 0; k < egyseg.length; k++) {
                if (egyseg[k] != null) {
                    if (!egyseg[k].isLepet()) {
                        if (vilag.getPlatforms()[s][o].getEgysegFajta() == null) {
                            szoveg="A "+ (String) (egyseg[k].isJoOldal()?"Hos":"Gonosznagyur")+" "+egyseg [k].getNev()+" lepet a "+s+"/"+o+" mezőre";
                            kepValaszto(s, o, k);
                            egyseg[k].setLepet(true);
                            oldalValasztas(s, o, k);
                            for (int i = 0; i < vilag.getSorok(); i++) {
                                for (int j = 0; j < vilag.getOszlopok(); j++) {
                                    if (i == s && j == o) {
                                        continue;
                                    } else if (vilag.getPlatforms()[i][j].getEgysegFajta() == egyseg[k]) {
                                        img = new ImageView(KepekElerese.alap);
                                        kepFelvitel(i, j, img);
                                        imageViews[i][j] = img;
                                        acpVilagLetrehoz.getChildren().add(img);
                                        vilag.setPlatforms(null, i, j, 2);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean benevan(int s, int o,int st,int ot) {
        for (int i = (s - 1); i < (s + 2); i++) {
            for (int j = (o - 1); j < (o + 2); j++) {
                if(i==st&&j==ot){
                    return true;
                }
            }
        }
        return false;
    }

    private Tulajdonsagok tulKeres(String nev , Hos hos){
        for (int i = 0;i<hos.getTulajdonsagok().length;i++){
            if(nev == hos.getTulajdonsagok()[i].getNev()){
                return hos.getTulajdonsagok()[i];
            }
        }
        return hos.getTulajdonsagok()[0];
    }

    private int sebzes(Egyseg e){
        double rn = Math.random();
        double random = rn*(e.getMaxSebzes()-e.getMinSebzes()+1)+e.getMinSebzes();
        return (int)random;
    }

    private double vedelem(Egyseg e){
        Tulajdonsagok tul = tulKeres("Vedekezes",e.isJoOldal()?hos:gonoszNagyur);
        return ((Vedekezes)tul).getEgysegErtSebzesCsokent();
    }

    public void toStringEgysegek(){
        for(int i=0;i< egyseg.length;i++){
            if(egyseg[i]==null)continue;
            System.out.println(i+" hello "+egyseg[i].toString());
        }
        System.out.println();
    }

    public void hosTorolEgyseg(Egyseg e){
        if(e.isJoOldal()){
            hos.egysegekHalala();
        }else {
            gonoszNagyur.egysegekHalala();
        }
    }

    private void darabCsokent(int sor,int oszlop){
            double db=(double) vilag.getPlatforms()[sor][oszlop].getEgysegFajta().getEletero()/vilag.getPlatforms()[sor][oszlop].getEgysegFajta().getEgyEletero();
            int dbint = (int)db;
            db=db-dbint;
        int elet;
        if (vilag.getPlatforms()[sor][oszlop].getEgysegFajta().isJoOldal()) {
                hos.darabModosit(vilag.getPlatforms()[sor][oszlop].getEgysegFajta(), dbint);
                elet=vilag.getPlatforms()[sor][oszlop].getEgysegFajta().getEletero();
                vilag.getPlatforms()[sor][oszlop].getEgysegFajta().setEletero(elet+(int)(db*vilag.getPlatforms()[sor][oszlop].getEgysegFajta().getEgyEletero()),false);
            } else {
                gonoszNagyur.darabModosit(vilag.getPlatforms()[sor][oszlop].getEgysegFajta(), dbint);
            elet=vilag.getPlatforms()[sor][oszlop].getEgysegFajta().getEletero();
                vilag.getPlatforms()[sor][oszlop].getEgysegFajta().setEletero(elet+(int)(db*vilag.getPlatforms()[sor][oszlop].getEgysegFajta().getEgyEletero()),false);
            }
    }

    private boolean vanEKoruloteValaki(Egyseg e, boolean oldal){
        int q=0;
        for (Platform platform : egysegekPozicioja) {
            q++;
            if (platform.getEgysegFajta()==e) {
                int i = platform.getI();
                int j = platform.getJ();
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if(k>=vilag.getSorok()||l>=vilag.getOszlopok()||k<0||l<0){
                            continue;
                        }
                        if(vilag.getPlatforms()[k][l].getEgysegFajta()!=null) {
                            if (vilag.getPlatforms()[k][l].getEgysegFajta().isJoOldal() == oldal) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void tamadas(int s,int o,boolean loves) {
        Egyseg e = new Egyseg();
        e = egyseg[soronKov()];
        ImageView img = new ImageView(KepekElerese.alap);
        for (int i = 0; i < vilag.getSorok(); i++) {
            for (int j = 0; j < vilag.getOszlopok(); j++) {
                try {
                    if (vilag.getPlatforms()[i][j].getEgysegFajta() != null) {
                        if (vilag.getPlatforms()[i][j].getEgysegFajta() == egyseg[soronKov()]) {
                            if (egyseg[soronKov()].isJoOldal() != vilag.getPlatforms()[s][o].getEgysegFajta().isJoOldal()) {
                                if (benevan(i, j, s, o)) {
                                    int seb = 0;
                                    int elet = 0;
                                    switch (e.getNev()) {
                                        case "Bergyilkos": {
                                            Tulajdonsagok tul = tulKeres("Szerencse", e.isJoOldal() ? hos : gonoszNagyur);
                                            if (Math.random() < 0.05+((Szerencse)tul).getKritikusTalalatokEselye()-1) {
                                                vilag.getPlatforms()[s][o].getEgysegFajta().setEletero(0, false);
                                                break;
                                            } else {
                                                seb = (int) (sebzes(egyseg[soronKov()]) * vedelem(vilag.getPlatforms()[s][o].getEgysegFajta()));
                                                if (Math.random() < ((Szerencse) tul).getKritikusTalalatokEselye() - 1) {
                                                    seb *= 2;
                                                }
                                                elet = vilag.getPlatforms()[s][o].getEgysegFajta().getEletero() - seb;
                                                vilag.getPlatforms()[s][o].getEgysegFajta().setEletero(elet, false);
                                                darabCsokent(s, o);
                                                break;
                                            }
                                        }
                                        case "IceMan": {
                                            seb = (int) (sebzes(egyseg[soronKov()]) * vedelem(vilag.getPlatforms()[s][o].getEgysegFajta()));
                                            Tulajdonsagok tul = tulKeres("Szerencse", e.isJoOldal() ? hos : gonoszNagyur);
                                            if (Math.random() < ((Szerencse) tul).getKritikusTalalatokEselye() - 1) {
                                                seb *= 2;
                                            }
                                            elet = vilag.getPlatforms()[s][o].getEgysegFajta().getEletero() - seb;
                                            vilag.getPlatforms()[s][o].getEgysegFajta().setEletero(elet, false);
                                            vilag.getPlatforms()[s][o].getEgysegFajta().setFagyasztva(1);
                                            darabCsokent(s, o);
                                            break;
                                        }
                                        default: {
                                            seb = (int) (sebzes(egyseg[soronKov()]) * vedelem(vilag.getPlatforms()[s][o].getEgysegFajta()));
                                            Tulajdonsagok tul = tulKeres("Szerencse", e.isJoOldal() ? hos : gonoszNagyur);
                                            if (Math.random() < ((Szerencse) tul).getKritikusTalalatokEselye() - 1) {
                                                seb *= 2;
                                            }
                                            elet = vilag.getPlatforms()[s][o].getEgysegFajta().getEletero() - seb;
                                            vilag.getPlatforms()[s][o].getEgysegFajta().setEletero(elet, false);
                                            darabCsokent(s, o);
                                            break;
                                        }
                                    }

                                    e.setLepet(true);
                                    szoveg="A"+(String)(e.isJoOldal()?"Hos":"Gonosz Nagyur"+" "+e.getNev()+" megtámadta a "+vilag.getPlatforms()[s][o].getEgysegFajta().getNev() +" egyseget");
                                    if (vilag.getPlatforms()[s][o].getEgysegFajta().getEletero() <= 0) {
                                        vilag.getPlatforms()[s][o].getEgysegFajta().setLepet(true);
                                        hosTorolEgyseg(vilag.getPlatforms()[s][o].getEgysegFajta());
                                        vilag.setPlatforms(null, s, o, 2);
                                        kepFelvitel(s, o, img);
                                        felhelyez(s, o, img);
                                        return;
                                    } else if (vilag.getPlatforms()[s][o].getEgysegFajta().getNev().equals("Griff")) {
                                        seb = (int) (sebzes(vilag.getPlatforms()[s][o].getEgysegFajta()) * vedelem(e));
                                        seb /= 2;
                                        elet = e.getEletero() - seb;
                                        e.setEletero(elet, false);
                                        eleje:
                                        for (int h = 0; h < vilag.getSorok(); h++) {
                                            for (int g = 0; g < vilag.getOszlopok(); g++) {
                                                if (vilag.getPlatforms()[h][g].getEgysegFajta() == e) {
                                                    if (e.getEletero() <= 0) {
                                                        vilag.setPlatforms(null, h, g, 2);
                                                        kepFelvitel(h, g, img);
                                                        felhelyez(h, g, img);
                                                    } else {
                                                        darabCsokent(h, g);
                                                        break eleje;
                                                    }
                                                }
                                            }
                                        }
                                    } else if (vilag.getPlatforms()[s][o].getEgysegFajta().getEletero() > 0) {
                                        if (!vilag.getPlatforms()[s][o].getEgysegFajta().isViszatamadot()) {
                                            seb = (int) (sebzes(vilag.getPlatforms()[s][o].getEgysegFajta()) * vedelem(e));
                                            seb /= 2;
                                            elet = e.getEletero() - seb;
                                            e.setEletero(elet, false);
                                            eleje:
                                            for (int h = 0; h < vilag.getSorok(); h++) {
                                                for (int g = 0; g < vilag.getOszlopok(); g++) {
                                                    if (vilag.getPlatforms()[h][g].getEgysegFajta() == e) {
                                                        if (e.getEletero() <= 0) {
                                                            vilag.setPlatforms(null, h, g, 2);
                                                            kepFelvitel(h, g, img);
                                                            felhelyez(h, g, img);
                                                        } else {
                                                            darabCsokent(h, g);
                                                            break eleje;
                                                        }
                                                    }
                                                }
                                            }
                                            vilag.getPlatforms()[s][o].getEgysegFajta().setViszatamadot(true);
                                        }
                                    }
                                    hosTorolEgyseg(e);
                                    return;

                                } else if (e.getNev().equals("Ijasz")) {
                                    if (loves == true) {
                                        if (vanEKoruloteValaki(e, !e.isJoOldal())) {
                                            szoveg = "Az Ijasz jörbe van véve!";
                                            return;
                                        }
                                        int seb = 0, elet = 0;
                                        seb = (int) (sebzes(egyseg[soronKov()]) * vedelem(vilag.getPlatforms()[s][o].getEgysegFajta()));
                                        Tulajdonsagok tul = tulKeres("Szerencse", e.isJoOldal() ? hos : gonoszNagyur);
                                        if (Math.random() < ((Szerencse) tul).getKritikusTalalatokEselye() - 1) {
                                            seb *= 2;
                                        }
                                        elet = vilag.getPlatforms()[s][o].getEgysegFajta().getEletero() - seb;
                                        vilag.getPlatforms()[s][o].getEgysegFajta().setEletero(elet, false);
                                        e.setLepet(true);
                                        darabCsokent(s, o);
                                        if (vilag.getPlatforms()[s][o].getEgysegFajta().getEletero() < 1) {
                                            vilag.getPlatforms()[s][o].getEgysegFajta().setLepet(true);
                                            hosTorolEgyseg(vilag.getPlatforms()[s][o].getEgysegFajta());
                                            vilag.setPlatforms(null, s, o, 2);
                                            kepFelvitel(s, o, img);
                                            felhelyez(s, o, img);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }catch (NullPointerException n){};
            }
        }
    }

    private void platformNull(int s,int o){
        if (vilag.getPlatforms()[s][o].getEgysegFajta().getEletero() <=0) {
            ImageView img = new ImageView(KepekElerese.alap);
            vilag.getPlatforms()[s][o].getEgysegFajta().setLepet(true);
            hosTorolEgyseg(vilag.getPlatforms()[s][o].getEgysegFajta());
            vilag.setPlatforms(null, s, o, 2);
            kepFelvitel(s, o, img);
            felhelyez(s, o, img);
        }
    }

    private void tuzlabda(int s,int o){
        Tulajdonsagok tul = tulKeres("Varazsero",varazslat.isJoOldal()?hos:gonoszNagyur);
        int seb = ((Varazsero)tul).getVarazslatokErosege()*20;
        int elet=0;
        for (int i = s-1; i <= s+1; i++) {
            for (int j = o-1; j <= o+1; j++) {
                if(i>=vilag.getSorok()||j>=vilag.getOszlopok()||i<0||j<0){
                    continue;
                }
                if(vilag.getPlatforms()[i][j].getEgysegFajta()!=null) {
                    vilag.getPlatforms()[i][j].getEgysegFajta().setEletero(vilag.getPlatforms()[i][j].getEgysegFajta().getEletero() - seb, false);
                    darabCsokent(i,j);
                    platformNull(i, j);
                }
            }
        }
    }

    public void varazslas(int s,int o){
        if(vilag.getPlatforms()[s][o].getEgysegFajta()!=null) {
            if (vilag.getPlatforms()[s][o].getEgysegFajta().isJoOldal() != varazslat.isJoOldal()) {
                switch (varazslat.getNev()) {
                    case "Vilamcsapas": {
                        Tulajdonsagok tul = tulKeres("Varazsero", varazslat.isJoOldal() ? hos : gonoszNagyur);
                        int seb = ((Varazsero) tul).getVarazslatokErosege() * 30;
                        vilag.getPlatforms()[s][o].getEgysegFajta().setEletero(vilag.getPlatforms()[s][o].getEgysegFajta().getEletero() - seb, false);
                        if (varazslat.isJoOldal()) {
                            hos.setMana(hos.getMana()-varazslat.getMana());
                            hos.setAtvete(1);
                        } else {
                            gonoszNagyur.setMana(gonoszNagyur.getMana()-varazslat.getMana());
                            gonoszNagyur.setAtvete(1);
                        }
                        szoveg="Vilamcsapas";
                        darabCsokent(s,o);
                        platformNull(s, o);
                        varazslat = null;
                        break;
                    }
                    case "Fagyasztas": {
                        vilag.getPlatforms()[s][o].getEgysegFajta().setFagyasztva(1);
                        vilag.getPlatforms()[s][o].getEgysegFajta().setLepet(true);
                        varazslat = null;
                        if (varazslat.isJoOldal()) {
                            hos.setMana(hos.getMana()-varazslat.getMana());
                            hos.setAtvete(1);
                        } else {
                            gonoszNagyur.setMana(gonoszNagyur.getMana()-varazslat.getMana());
                            gonoszNagyur.setAtvete(1);
                        }
                        varazslat = null;
                        szoveg="fagyasztas";
                        break;
                    }
                    case "Tuzlabda": {
                        tuzlabda(s, o);
                        if (varazslat.isJoOldal()) {
                            hos.setMana(hos.getMana()-varazslat.getMana());
                            hos.setAtvete(1);
                        } else {
                            gonoszNagyur.setMana(gonoszNagyur.getMana()-varazslat.getMana());
                            gonoszNagyur.setAtvete(1);
                        }
                        varazslat = null;
                        szoveg="tuzlabda";
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }else {
                if(varazslat.getNev().equals("Feltamasztas")) {
                    if(vilag.getPlatforms()[s][o].getEgysegFajta().getEletero()==vilag.getPlatforms()[s][o].getEgysegFajta().getEgyEletero()*vilag.getPlatforms()[s][o].getEgysegFajta().getEredetiDarab()){
                    }else {
                        Tulajdonsagok tul = tulKeres("Varazsero", egyseg[soronKov()].isJoOldal() ? hos : gonoszNagyur);
                        int heal = ((Varazsero) tul).getVarazslatokErosege() * 50;
                        int hp;
                        if(vilag.getPlatforms()[s][o].getEgysegFajta().getEletero()+heal<=vilag.getPlatforms()[s][o].getEgysegFajta().getEgyEletero()*vilag.getPlatforms()[s][o].getEgysegFajta().getEredetiDarab()){
                            hp=vilag.getPlatforms()[s][o].getEgysegFajta().getEletero();
                            vilag.getPlatforms()[s][o].getEgysegFajta().setEletero(hp+heal,false);
                            darabCsokent(s,o);
                        }else{
                            vilag.getPlatforms()[s][o].getEgysegFajta().setEletero(vilag.getPlatforms()[s][o].getEgysegFajta().getEgyEletero()*vilag.getPlatforms()[s][o].getEgysegFajta().getEredetiDarab(),false);
                            darabCsokent(s,o);
                        }
                        if (varazslat.isJoOldal()) {
                            hos.setMana(hos.getMana()-varazslat.getMana());
                            hos.setAtvete(1);
                        } else {
                            gonoszNagyur.setMana(gonoszNagyur.getMana()-varazslat.getMana());
                            gonoszNagyur.setAtvete(1);
                        }
                        szoveg="feltamasztas";
                        varazslat = null;
                    }

                }
            }
        }else {
            if(varazslat.getNev()=="Tuzlabda") {
                tuzlabda(s, o);
                if (varazslat.isJoOldal()) {
                    hos.setMana(hos.getMana() - varazslat.getMana());
                    hos.setAtvete(1);
                } else {
                    gonoszNagyur.setMana(gonoszNagyur.getMana() - varazslat.getMana());
                    gonoszNagyur.setAtvete(1);
                }
                szoveg = "tuzlabda";
                varazslat = null;
            }
            return;
        }
    }

    public void hosTamad(int s,int o){
        if(vilag.getPlatforms()[s][o].getEgysegFajta()!=null){
            if (vilag.getPlatforms()[s][o].getEgysegFajta().isJoOldal()!=egyseg[soronKov()].isJoOldal()){
                int seb = egyseg[soronKov()].isJoOldal()?(int)hos.getTamadas()*10:(int)gonoszNagyur.getTamadas()*10;
                vilag.getPlatforms()[s][o].getEgysegFajta().setEletero(vilag.getPlatforms()[s][o].getEgysegFajta().getEletero()-seb,false);
                darabCsokent(s,o);
                platformNull(s,o);
                if (egyseg[soronKov()].isJoOldal()) {
                    hos.setAtvete(1);
                } else {
                    gonoszNagyur.setAtvete(1);
                }
                hossTamad=false;
                szoveg = "HosTamad";
            }
        }
    }

    public void gepFelpakol() {
        while (kellMegLehejezni()) {
            for (int i = 0; i < gonoszNagyur.getEgysegSzam(); i++) {
                if (!gonoszNagyur.getEgysegek()[i].isFelhejezve()) {
                    int randomSor = (int) Math.round(Math.random() * (9));
                    int randomOszlop = (int) Math.round(Math.random() * (11 - 10) + 10);
                    felhelyezes(randomSor, randomOszlop, false);
                }
            }
        }
    }

    public Egyseg legkevesebHP(boolean oldal){
        int lhp=1000000;
        Egyseg e = new Egyseg();
        for (int i = 0; i < egyseg.length; i++) {
            if(egyseg[i]!=null) {
                if (egyseg[i].isJoOldal() == oldal) {
                    if (egyseg[i].getEletero() < lhp) {
                        e = egyseg[i];
                    }
                }
            }
        }
        return e;
    }

    private Platform egysegKeres(Egyseg e){
        for (Platform p:egysegekPozicioja) {
            if(p.getEgysegFajta()==e){
                return p;
            }
        }
        return null;
    }

    private Platform mozgas(){
        Set<Platform> vizsgalando = new HashSet<>();
        vizsgalando.add(egysegekPozicioja.get(egyforma()));
        int koltseg=egysegekPozicioja.get(egyforma()).getEgysegFajta().getSebeseg();
        int koltKezdet=koltseg;
        while (true) {
            vizsgalando = kifejt(vizsgalando);
            koltseg--;
            for (Platform p:vizsgalando) {
                if(p.getI()>=vilag.getSorok()||p.getJ()>=vilag.getOszlopok()||p.getI()<0||p.getJ()<0){
                    continue;
                }
                if(koltseg==0){
                    return p;
                }
            }
        }
    }
    public void gepMozgatasVagyTamadas() {
        kifele:
        if (egyseg[soronKov()] != null) {
            if (!egyseg[soronKov()].isJoOldal()) {
                if (!egyseg[soronKov()].isLepet()) {
                    varazslatVolt:
                    if (gonoszNagyur.getAtvete() == 0) {
                        Egyseg e = legkevesebHP(true);
                        if (e == null) {
                            gyoztesHirdetes();
                            break kifele;
                        }
                        Platform p = egysegKeres(e);
                        Egyseg egy = legkevesebHP(false);
                        Platform pl = egysegKeres(e);
                        if (gonoszNagyur.getMana() >= 5 && gonoszNagyur.getVarazslat()[0] != null) {
                            for (int j = 0; j < gonoszNagyur.getVarazslat().length; j++) {
                                if (gonoszNagyur.getVarazslat()[j] != null) {
                                    if (gonoszNagyur.getVarazslat()[j].getNev() == "Feltamasztas") {
                                        if (egy.getEletero() < (egy.getEredetiDarab() * egy.getEgyEletero())) {
                                            varazslat = gonoszNagyur.getVarazslat()[j];
                                            varazslas(pl.getI(), pl.getJ());
                                            meghivas();
                                            break varazslatVolt;
                                        } else {
                                            continue;
                                        }
                                    } else if (gonoszNagyur.getMana() > gonoszNagyur.getVarazslat()[j].getMana()) {
                                        varazslat = gonoszNagyur.getVarazslat()[j];
                                        varazslas(p.getI(), p.getJ());
                                        meghivas();
                                        break varazslatVolt;
                                    } else {
                                        continue;
                                    }
                                } else {
                                    hosTamad(p.getI(), p.getJ());
                                    meghivas();
                                    break varazslatVolt;
                                }
                            }
                        } else {
                            hosTamad(p.getI(), p.getJ());
                            meghivas();
                            break varazslatVolt;
                        }
                    } else if (egyseg[soronKov()].getNev() == "Ijasz" && !vanEKoruloteValaki(egyseg[soronKov()], true)) {
                        Egyseg e = legkevesebHP(true);
                        if (e == null) {
                            gyoztesHirdetes();
                            break kifele;
                        }
                        Platform p = egysegKeres(e);
                        if (p == null) {
                            break kifele;
                        }
                        tamadas(p.getI(), p.getJ(), true);
                        meghivas();
                        break kifele;
                    } else if (vanEKoruloteValaki(egyseg[soronKov()], true)) {
                        for (int s = egysegekPozicioja.get(egyforma()).getI() - 1; s <= egysegekPozicioja.get(egyforma()).getI() + 1; s++) {
                            for (int o = egysegekPozicioja.get(egyforma()).getJ() - 1; o <= egysegekPozicioja.get(egyforma()).getJ() + 1; o++) {
                                if (s >= vilag.getSorok() || o >= vilag.getOszlopok() || s < 0 || o < 0) {
                                    continue;
                                }
                                if (vilag.getPlatforms()[s][o].getEgysegFajta() != null) {
                                    if (egysegekPozicioja.get(egyforma()).getEgysegFajta().isJoOldal() != vilag.getPlatforms()[s][o].getEgysegFajta().isJoOldal()) {
                                        tamadas(s, o, false);
                                        meghivas();
                                        break kifele;
                                    }
                                }
                            }
                        }
                    } else {
                        Platform p = mozgas();
                        mozgatas(p.getI(), p.getJ(), hos, gonoszNagyur);
                        meghivas();
                        break kifele;
                    }
                }
            }
        }
    }

    private void meghivas(){
        osszEgyseg();
        oldalsav();
        felso();
        inicializal();
        gyoztesHirdetes();
        if(gepElen){
            if(vanMegEllenfel(true)) {
                if (!egyseg[soronKov()].isJoOldal()) {
                    if (!egyseg[soronKov()].isLepet()) {
                        gepMozgatasVagyTamadas();
                    }
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        osszEgyseg();
        oldalsav();
        felso();
        inicializal();
        for (int i = 0; i < vilag.getSorok(); i++) {
            for (int j = 0; j < vilag.getOszlopok(); j++) {

                ImageView img = new ImageView(KepekElerese.alap);
                kepFelvitel(i, j, img);
                vilag.setPlatforms(null, i, j, 2);
                imageViews[i][j] = img;
                acpVilagLetrehoz.getChildren().add(img);
            }
        }
    }
}
