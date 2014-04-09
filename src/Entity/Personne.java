package Entity;

/**
 *
 * @author Wael Mallek
 */
public class Personne {

    private int id;
    private String nom;
    private String prenom;
    private int telephone ;
    private String  ville ;
    private int age ;
    private String mdp;

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMdp() {
        return mdp;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getVille() {
        return ville;
    }

    public int getAge() {
        return age;
    }

    public Personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

    Personne getPersonne() {
        return null;
    }
}
