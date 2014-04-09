/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Dryra
 */
public class Animal {
    private  int id ;
    private  int id_adh ;
    private String nom;
    private String age;
    private String race;
    private String poids;
    private String couleur;
    private String vaccin;
    private String etat;
    private String Sexe;
    private String autre;
    private String espece;
    private String adresse;
    private double lat ;
    private double lon ;

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

     public void setId_adh(int id_adh) {
        this.id_adh = id_adh;
    }

    public int getId_adh() {
        return id_adh;
    }
    public void setAutre(String autre) {
        this.autre = autre;
    }



    public String getAutre() {
        return autre;
    }

    



   public void setNom(String nom) {
        this.nom = nom;
    }
    

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }
    public String getSexe() {
        return Sexe;
    }

    public String getAge() {
        return age;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getEspece() {
        return espece;
    }

    public String getEtat() {
        return etat;
    }

    public String getPoids() {
        return poids;
    }

    public String getRace() {
        return race;
    }

    public String getVaccin() {
        return vaccin;
    }

   public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVaccin(String vaccin) {
        this.vaccin = vaccin;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public void setRace(String race) {
        this.race = race;
    }}
