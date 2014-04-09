

package Entity;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author slim
 */
public class Pension {

    private int id;
    private int id_prest;
    private String jour_disp;
    private double note;
    private String adr;
    private int tel;
    private double prix_serv;
    private String nom;
    private int nb ;

    public Pension(int nb,int id, int id_prest, String adr, int tel, double prix_serv, String jour_disp, double note, String nom) {
        this.id = id;
        this.id_prest = id_prest;
        this.adr = adr;
        this.tel = tel;
        this.prix_serv = prix_serv;
        this.jour_disp = jour_disp;
        this.note = note;
        this.nom = nom;
        this.nb=nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public int getNb() {
        return nb;
    }

    public Pension() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_prest() {
        return id_prest;
    }

    public void setId_prest(int id_prest) {
        this.id_prest = id_prest;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public double getPrix_serv() {
        return prix_serv;
    }

    public void setPrix_serv(double prix_serv) {
        this.prix_serv = prix_serv;
    }

    public String getJour_disp() {
        return jour_disp;
    }

    public void setJour_disp(String jour_disp) {
        this.jour_disp = jour_disp;
    }

    public double getNote() {
        return note;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNote(double note) {
        this.note = note;
    }

    void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    void setId_prest(String id_prest) {
        this.id_prest = Integer.parseInt(id_prest);
    }

    void setTel(String tel) {
        this.tel = Integer.parseInt(tel);
    }

    void setPrix_serv(String prix_serv) {
        this.prix_serv = Double.parseDouble(prix_serv);
    }

    void setNote(String note) {
        this.note = Double.parseDouble(note);
    }
}
