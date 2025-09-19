package org.example.jatekElemek.tulajdonsagok;

import org.example.jatekElemek.Tulajdonsagok;

public class Szerencse extends Tulajdonsagok {
    private double kritikusTalalatokEselye=1;

    public double getKritikusTalalatokEselye() {
        return kritikusTalalatokEselye;
    }

    public void setKritikusTalalatokEselye() {
        this.kritikusTalalatokEselye += (((double) getSzint()/100)*5);
    }

    public Szerencse(int szint) {
        super(szint, "Szerencse");
        setKritikusTalalatokEselye();
    }

    @Override
    public String toString() {
        return "Szerencse{" +
                "kritikusTalalatokEselye=" + kritikusTalalatokEselye +
                "} " + super.toString();
    }
}
