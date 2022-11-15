/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionutilisateur;

/**
 *
 * @author khaled
 */
public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String addresse;
    private String numtel;
   
    public int getId() {
        return id;
    }
public Utilisateur(){
    
}
    public Utilisateur(int id, String nom, String prenom, String addresse, String numtel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = addresse;
        this.numtel = numtel;
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }
    
}

