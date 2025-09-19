package org.example.jatekElemek;

import java.util.Objects;

public class Platform {
    private Egyseg egysegFajta;
    private int i;
    private int j;
    private int oldal;

    public Egyseg getEgysegFajta() {
        return egysegFajta;
    }

    public void setEgysegFajta(Egyseg egysegFajta) {
        this.egysegFajta = egysegFajta;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getOldal() {
        return oldal;
    }

    public void setOldal(int oldal) {
        this.oldal = oldal;
    }

    public Platform() {
    }

    public Platform(Egyseg egysegFajta, int i, int j, int oldal) {
        this.egysegFajta = egysegFajta;
        this.i = i;
        this.j = j;
        this.oldal = oldal;
    }

    public Platform(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platform platform = (Platform) o;
        return i == platform.i && j == platform.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public String toString() {
        return "Platform{" +
                "egysegFajta=" + egysegFajta +
                ", i=" + i +
                ", j=" + j +
                ", oldal=" + oldal +
                '}';
    }
}
