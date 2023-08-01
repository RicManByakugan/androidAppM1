package com.example.wm.model;

public class Post {
    private String numero;
    private String modele;
    private String marque;
    private int annee;

    // Constructors
    public Post() {
    }

    public Post(String numero, String modele, String marque, int annee) {
        this.numero = numero;
        this.modele = modele;
        this.marque = marque;
        this.annee = annee;
    }

    // Getters and Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
