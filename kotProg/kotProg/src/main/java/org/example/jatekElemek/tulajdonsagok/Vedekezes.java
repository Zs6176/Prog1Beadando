package org.example.jatekElemek.tulajdonsagok;

import org.example.jatekElemek.Tulajdonsagok;

public class Vedekezes extends Tulajdonsagok {
    private double egysegErtSebzesCsokent = 1;

    public double getEgysegErtSebzesCsokent() {
        return egysegErtSebzesCsokent;
    }

    public void setEgysegErtSebzesCsokent() {
        this.egysegErtSebzesCsokent -= (((double) getSzint()/100)*5);
    }

    public Vedekezes(int szint) {
        super(szint, "Vedekezes");
        setEgysegErtSebzesCsokent();
    }

    @Override
    public String toString() {
        return "Vedekezes{" +
                "egysegErtSebzesCsokent=" + egysegErtSebzesCsokent +
                "} " + super.toString();
    }
}
