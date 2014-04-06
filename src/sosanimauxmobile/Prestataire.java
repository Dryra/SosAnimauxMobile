/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sosanimauxmobile;

/**
 *
 * @author Dryra
 */
public class Prestataire {
    private String nom ;
    private String prenom ;
    private String domaine ;
    private String jour_disp ;
    private Double prix_serv ;
    private String Email ;
    private int id ;
    private int telephone ;
    private String adresse ;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prennom) {
        this.prenom = prennom;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public void setJour_disp(String jour_disp) {
        this.jour_disp = jour_disp;
    }

    public void setPrix_serv(Double prix_serv) {
        this.prix_serv = prix_serv;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getJour_disp() {
        return jour_disp;
    }

    public Double getPrix_serv() {
        return prix_serv;
    }

    public String getEmail() {
        return Email;
    }

    public int getId() {
        return id;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }
    
}
