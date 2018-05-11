package com.example.user.softeng;

public class Model {
    public Model(String s, String c, String t, int d, int n){

        subject = s;
        cde =c;
        tme = t;
        drop = d;
        numdays= n;
    }

    public int drop, numdays;
    public String cde, tme, subject;

    public int getDrop() {
        return drop;
    }

    public void setDrop(int drop) {
        this.drop = drop;
    }

    public int getNumdays() {
        return numdays;
    }

    public void setNumdays(int numdays) {
        this.numdays = numdays;
    }

    public String getCde() {
        return cde;
    }

    public void setCde(String cde) {
        this.cde = cde;
    }

    public String getTme() {
        return tme;
    }

    public void setTme(String tme) {
        this.tme = tme;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
