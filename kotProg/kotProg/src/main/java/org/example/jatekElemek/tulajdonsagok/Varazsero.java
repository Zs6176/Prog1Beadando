package org.example.jatekElemek.tulajdonsagok;

import org.example.jatekElemek.Tulajdonsagok;

public class Varazsero extends Tulajdonsagok {
    private int varazslatokErosege = 0;

    public int getVarazslatokErosege() {
        return varazslatokErosege;
    }

    public void setVarazslatokErosege() {
        this.varazslatokErosege = getSzint();
    }

    public Varazsero(int szint) {
        super(szint, "Varazsero");
        setVarazslatokErosege();
    }

    @Override
    public String toString() {
        return "Varazsero{" +
                "varazslatokErosege=" + varazslatokErosege +
                "} " + super.toString();
    }
}
