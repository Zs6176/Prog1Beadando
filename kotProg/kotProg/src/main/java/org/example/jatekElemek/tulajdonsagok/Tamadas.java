package org.example.jatekElemek.tulajdonsagok;

import org.example.jatekElemek.Tulajdonsagok;

public class Tamadas extends Tulajdonsagok {
    private double egysegSebzesNovesel = 1.0;

    public double getEgysegSebzesNovesel() {
        return egysegSebzesNovesel;
    }

    public void setEgysegSebzesNovesel() {
        this.egysegSebzesNovesel += ((double) getSzint()/10);
    }

    public Tamadas(int szint) {
        super(szint, "Tamadas");
        setEgysegSebzesNovesel();
    }

    @Override
    public String toString() {
        return "Tamadas{" +
                "egysegSebzesNovesel=" + egysegSebzesNovesel +
                "} " + super.toString();
    }
}
