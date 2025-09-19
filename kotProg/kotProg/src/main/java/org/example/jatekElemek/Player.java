package org.example.jatekElemek;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public static List<Hos> hosList = new ArrayList<>();

    public void hosFeltolt(Hos hos , int i){
        hosList.add(hos);
    }
}
