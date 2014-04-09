/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sosanimauxmobile;

import Entity.Animal;
import Entity.Pension;
import Entity.Personne;
import Entity.Prestataire;
import Handlers.AnimalHandler;
import Handlers.PensionHandler;
import Handlers.PersonneHandler;
import Handlers.PrestataireHandler;
import com.jappit.midmaps.googlemaps.GoogleMaps;
import com.jappit.midmaps.googlemaps.GoogleMapsCoordinates;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import com.jappit.midmaps.googlemaps.GoogleStaticMapHandler;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;

import javax.microedition.lcdui.Canvas;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;

import javax.microedition.lcdui.StringItem;

import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import Video.DisplayVideoMIDle;
import sosanimauxmobile.Midlet.CanvasList;
import java.io.*;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Spacer;
import javax.microedition.media.*;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 * @author Dryra
 */
public class Midlet extends MIDlet implements CommandListener, ItemStateListener, Runnable {
//Hamza
    int id_adher ;
    int id_presta ;
    String urlauth = "http://127.0.0.1/zerzer/authentification.php";
    Alert altCon = new Alert("Error", "Login et/ou mot de passe incorrecte(s)", null, AlertType.ERROR);
    Command cmdParser = new Command("Rechercher", Command.SCREEN, 0);
    Command cmdBackr = new Command("Back", Command.BACK, 0);
    Command cmdBack2r = new Command("Back", Command.BACK, 0);
    Command cmdBackinfosr = new Command("Back", Command.BACK, 0);
    Animal[] animals;
    List lstr = new List("Animals", List.IMPLICIT);
    Form forminfosr = new Form("Infos Animal");
    Form loadingDialogr = new Form("Please Wait");
    
    List listchoixr = new List("Select one", List.EXCLUSIVE);
    Form imageAnr = new Form("Photo");
    Command AfficherImgr = new Command("Visualiser image", Command.SCREEN, 1);
    Command AfficherImgrretour = new Command("Retour", Command.EXIT, 1);
    Image imager;
    Image PhotoAnr;
    HttpConnection htImgr;
    DataInputStream dtImgr;
    int sureintr=0;
    int loadanr=0;
    
    int idimgr;
    
    
//Hamza    
    int k = 0;
    //Donia
    TextField nomAna = new TextField("nom", null, 50, TextField.ANY);
    TextField especea = new TextField("espece", null, 50, TextField.ANY);
    TextField racea = new TextField("race", null, 50, TextField.ANY);
    TextField couleura = new TextField("couleur", null, 50, TextField.ANY);
    String[] sexea = {"male", "femelle"};
    ChoiceGroup chSexea = new ChoiceGroup("Sexe", ChoiceGroup.EXCLUSIVE, sexea, null);
    TextField poidsa = new TextField("poids", null, 50, TextField.ANY);
    TextField ageAna = new TextField("age", null, 50, TextField.ANY);
    String[] vaccina = {"oui", "non"};
    ChoiceGroup chVaccina = new ChoiceGroup("Vaccin", ChoiceGroup.EXCLUSIVE, vaccina, null);
    TextField autrea = new TextField("autre", null, 50, TextField.ANY);
    String[] etata = {"sans_refuge", "sans_foyer", "perdu"};
    ChoiceGroup chEtata = new ChoiceGroup("etat", ChoiceGroup.POPUP, etata, null);
    String urlajoutanimal = "http://127.0.0.1/zerzer/ajoutanimal.php";
    Command cmdValiderAjout = new Command("Valider", Command.SCREEN, 0);
    Command cmdAnnulerAjoutAn = new Command("Annuler", Command.BACK, 0);
    Command cmdRetourMenu = new Command("Retour", Command.BACK, 0);
    Alert alertChampAjout = new Alert("Error", "veuillez saisir tout les champs", null, AlertType.ERROR);
    StringItem msgAjoutAn = new StringItem("l'operation d'ajout a été effectué avec succés   ", null);
    Spacer sp3 = new Spacer(100, 100);
    Alert alertDeclarerAn = new Alert("Error", "une erreur s'est produite, l'animal n'est pas ajouté", null, AlertType.ERROR);
    Form formDeclarerAn = new Form("Declarer animal");
    Form formconfirmationAjout = new Form("message de confirmation");
    TextField nomAndon = new TextField("nom", null, 50, TextField.ANY);
    TextField especedon = new TextField("espece", null, 50, TextField.ANY);
    TextField racedon = new TextField("race", null, 50, TextField.ANY);
    TextField couleurdon = new TextField("couleur", null, 50, TextField.ANY);
    String[] sexedon = {"male", "femelle"};
    ChoiceGroup chSexedon = new ChoiceGroup("Sexe", ChoiceGroup.EXCLUSIVE, sexedon, null);
    TextField poidsdon = new TextField("poids", null, 50, TextField.ANY);
    TextField ageAndon = new TextField("age", null, 50, TextField.ANY);
    String[] vaccindon = {"oui", "non"};
    ChoiceGroup chVaccindon = new ChoiceGroup("Vaccin", ChoiceGroup.EXCLUSIVE, vaccindon, null);
    String[] etatdon = {"sans_refuge", "sans_foyer", "perdu"};
    ChoiceGroup chEtatdon = new ChoiceGroup("etat", ChoiceGroup.POPUP, etatdon, null);
    Command Validerdon = new Command("Valider", Command.SCREEN, 0);
    Command Annulerdon = new Command("Annuler", Command.BACK, 0);
    Form fdon = new Form("form");
    String urldon = "http://127.0.0.1/zerzer/modifanimal.php";
    int idimg;
    int adopter = 0;
    Form fadop = new Form("Adoption");
    Form imageAn = new Form("Photo");
    Form AnSansFoyerloading = new Form("animaux a adopter");
    Form InfoAn = new Form("informations animal");
    Form confirmAdop = new Form("Conditions d'adoption");
    Form msgconfirm = new Form("message de confirmation");
    Spacer sp1 = new Spacer(50, 50);
    TextField nomAn = new TextField("nom", null, 50, TextField.UNEDITABLE);
    TextField espece = new TextField("espece", null, 50, TextField.UNEDITABLE);
    TextField race = new TextField("race", null, 50, TextField.UNEDITABLE);
    TextField couleur = new TextField("couleur", null, 50, TextField.UNEDITABLE);
    TextField sexe = new TextField("sexe", null, 50, TextField.UNEDITABLE);
    TextField poids = new TextField("poids", null, 50, TextField.UNEDITABLE);
    TextField ageAn = new TextField("age", null, 50, TextField.UNEDITABLE);
    TextField vaccin = new TextField("vaccin", null, 50, TextField.UNEDITABLE);
    TextField etat = new TextField("etat", null, 50, TextField.UNEDITABLE);
    TextField autre = new TextField("autre", null, 50, TextField.UNEDITABLE);
    StringItem stmsg = new StringItem(
            "Un animal est un être vivant qui mérite du temps, de l’attention et de l’amour.\n \n"
            + "Adopter est un acte responsable, il nécessite un budget pour la nourriture,"
            + " les soins mais aussi un choix de vie (le sortir plusieurs fois par jour, prévoir une solution pour les vacances). \n \n"
            + "le message que vous recevrez sur votre telephone lors de la confirmation de votre demande est obligatoire pour recuperer votre animal. \n \n"
            + "vous etes prié de venir chercher votre nouvel ami dans un delai de deux jours apres votre confirmation d'adoption.", null);
    Command Afficher = new Command("Afficher", Command.SCREEN, 0);
    Command AfficherImg = new Command("Visualiser image", Command.SCREEN, 1);
    Command Adopter = new Command("Adopter", Command.SCREEN, 0);
    Command Confirmer = new Command("Confirmer", Command.SCREEN, 0);
    Command Annuler = new Command("Annuler", Command.BACK, 0);
    Command RetourInfAn = new Command("Retour", Command.BACK, 0);
    Command Retour = new Command("Retour", Command.BACK, 0);
    Command cmdParsean = new Command("Animaux", Command.SCREEN, 0);
//    Animal[] animaux;
    List lstAnSansFoyer = new List("Animaux", List.IMPLICIT);
    Image imagean;
    Image PhotoAn;
    HttpConnection htImg;
    DataInputStream dtImg;
    Alert alertUpdate = new Alert("Error", "la mise a jour n'a pas ete effectue", null, AlertType.ERROR);
    HttpConnection hcAdopt;
    DataInputStream disAdopt;
    String urlAdopt = "http://127.0.0.1/zerzer/modifEtatAnimalAdpt.php";
    StringBuffer sbAdopt = new StringBuffer();
    int chAdopt;
    MessageConnection clientConn;
    //modifanimal
    //Donia  
    //Amal
    Form form_reclamation = new Form("Réclamation");
    TextField txtsender = new TextField("Votre nom :", null, 300, TextField.ANY);
    String[] itemSujet = {"Probléme avec l'application ", "Problème avec mon compte", "Signaler un abus"};
    ChoiceGroup chsujet = new ChoiceGroup("Sujet", ChoiceGroup.EXCLUSIVE, itemSujet, null);
    TextField txtmessage = new TextField("Message", null, 300, TextField.ANY);
    Command cmdEnvoyeram = new Command("Envoyer", Command.SCREEN, 0);
    Command cmdBackam = new Command("Retour", Command.EXIT, 0);
    Alert alertesucca = new Alert("Bravo", "Votre mail a été envoyé", null, AlertType.CONFIRMATION);
    Alert alertecheca = new Alert("Erreur", "Erreur lors de l'envoi de la réclamation", null, AlertType.ERROR);
    Spacer espam = new Spacer(20, 20);
    String urlmail = "http://127.0.0.1/zerzer/mailer.php";
    
    
    Form form_inscription_prest = new Form("Inscription prestataire");
    Alert alertesucc = new Alert("Bravo", "Vous êtes désormais inscrit", null, AlertType.CONFIRMATION);
    String[] itemmetier = {"Vétérinaire", "Dresseur", "Toiletteur"};
    TextField tnom = new TextField("Nom :", null, 50, TextField.ANY);
    TextField tprenom = new TextField("Prenom :", null, 50, TextField.ANY);
    TextField tmp = new TextField("Mot de passe:", null, 50, TextField.PASSWORD);
    TextField tadr = new TextField("Adresse :", null, 50, TextField.ANY);
    TextField tmail = new TextField("Adresse Email:", null, 50, TextField.ANY);
    TextField tprixser = new TextField("Prix Service:", null, 50, TextField.ANY);
    TextField ttel = new TextField("Telephone:", null, 50, TextField.NUMERIC);
    ChoiceGroup chdomaine = new ChoiceGroup("Domaine :", ChoiceGroup.POPUP, itemmetier, null);
    Command cmdInscriptionprest = new Command("S'inscrire", Command.SCREEN, 0);
    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);
    Alert alerte = new Alert("Error", "You must fill all the fields", null, AlertType.ERROR);
    Command cmdBack = new Command("Retour", Command.EXIT, 0);
    StringItem st = new StringItem("Veuillez remplir tous les champs", null);
    Spacer esp = new Spacer(10, 10);
    String urlajoutprest = "http://127.0.0.1/zerzer/ajoutprest.php";
    String urlajoutpension = "http://127.0.0.1/zerzer/slim/ajouterpension.php";
    
    Form form_modif_prest = new Form("Modification Profil");
    Alert alertesuccaa = new Alert("Bravo", "Vous avez changé vos informations", null, AlertType.CONFIRMATION);
       TextField tmpa = new TextField("Mot de passe:", null, 50, TextField.PASSWORD);
    TextField tadra = new TextField("Adresse :", null, 50, TextField.ANY);
    TextField tmaila = new TextField("Adresse Email:", null, 50, TextField.ANY);
    TextField tprixsera = new TextField("Prix Service:", null, 50, TextField.ANY);
    TextField tjdispa = new TextField("Jours Disponibilité:", null, 50, TextField.ANY);
    TextField ttela = new TextField("Telephone:", null, 50, TextField.ANY);
    Command cmdvlidermodifa = new Command("Valider", Command.SCREEN, 0);
    Alert alertama = new Alert("Error", "Sorry", null, AlertType.ERROR);
    Command cmdBackpra = new Command("Retour", Command.EXIT, 0);
    StringItem st2a = new StringItem("Mettez a jour vos informartions", null);
    String urlmodifpresta = "http://127.0.0.1/zerzer/modifprest.php?";
    
//Amal    
    //Slim
    Form AjouterPension = new Form("Ajout Pension");
    Command ValiderAjout = new Command("Valider", Command.SCREEN, 0);
    TextField txtadresse = new TextField("Adresse", null, 50, TextField.ANY);
    TextField txttel = new TextField("Telephone", null, 50, TextField.NUMERIC);
    TextField txtprix = new TextField("Prix", null, 50, TextField.NUMERIC);
    TextField txtjour = new TextField("Jours Diponibles", null, 50, TextField.ANY);
    TextField txtnompension = new TextField("Nom", null, 50, TextField.ANY);
    Alert alertError = new Alert("Error", "Veuillez saisir tous les champs obligatoires", null, AlertType.ERROR);
    Alert alertsuccee = new Alert("Sucées", "sucées", null, AlertType.INFO);
    //Noter Service
    Display d = Display.getDisplay(this);
    Gauge evaluation = new Gauge("Noter service", true, 10, 1);
    StringItem resultatpen = new StringItem("Note", "1");
    Command ValiderNote = new Command("Noter", Command.SCREEN, 0);
    int choix = 0;
    Form Note = new Form("Les Notes");
    Command AffcheNote = new Command("Afficher Note", Command.SCREEN, 0);
    //appel script ajouter note 
    Alert alertnote = new Alert("cebon", "cebon", null, AlertType.INFO);
    String urlajoutnote = "http://127.0.0.1/zerzer/slim/ajouternote.php";
    StringBuffer sbajout = new StringBuffer();
    String urlNote = "http://127.0.0.1/zerzer/slim/get_xml_note.php";
    Pension[] pensionnes;
    List lstpensions = new List("Pensions", List.IMPLICIT);
    Command cmdParse = new Command("Pensions", Command.SCREEN, 0);
    Command cmdBackpension = new Command("Back", Command.BACK, 0);
    Command cmdBackpensionss = new Command("Back", Command.BACK, 0);
    Form fpens = new Form("Cliquez");
    Form formpens = new Form("Pensions_");
    List lstpens = new List("Pensions", List.IMPLICIT);
    Command SelectPensions = new Command("Selectionner", Command.SCREEN, 0);
    Alert alt33 = new Alert("Sucée", "sucée", null, AlertType.INFO);
    String urlinscri = "http://127.0.0.1/zerzer/slim/ajout.php";
    //Slim//
    private ChoiceGroup radioButtons = new ChoiceGroup("Sexe", Choice.EXCLUSIVE);
    private ChoiceGroup combo = new ChoiceGroup("Ville", Choice.EXCLUSIVE);
    Thread t2 = null;
    Display disp = Display.getDisplay(this);
    Command cmdConnect = new Command("Se Connecter", Command.SCREEN, 0);
    Command cmdInscription = new Command("Inscription", Command.EXIT, 0);
    Command cmdValiderInscri = new Command("Valider", Command.BACK, 0);
    Command cmdValiderchangeranimal = new Command("Valider", Command.EXIT, 0);
    Command cmdRetourAffichanimal = new Command("Retour", Command.EXIT, 0);
    Command cmdRetourlisteprestataires = new Command("Retour", Command.EXIT, 0);
    Command cmdRetourListeAnim = new Command("Retour", Command.BACK, 0);
    Command cmdRetourchangermdp = new Command("Retour", Command.BACK, 0);
    Command cmdchangermdp = new Command("Valider", Command.EXIT, 0);
    Command surecommand = new Command("Oui :( ", Command.EXIT, 0);
    Command surecommandretour = new Command("Non ", Command.BACK, 0);
    Command RetourTypeInscri = new Command("Retour ", Command.BACK, 0);
    Command TypeInscri = new Command("Choisir ", Command.EXIT, 0);
    Command ValiderPresta = new Command("Choisir", Command.BACK, 0);
    Command Retourajoutpensions = new Command("Retour", Command.EXIT, 0);
    private Command selectionconsultation = new Command("CONSULTER", Command.ITEM, 1);
    private Command selectionservice = new Command("Afficher", Command.ITEM, 1);
    private Command changementmdp = new Command("Changer mot de passe", Command.ITEM, 1);
    Image img;
    byte[] data;
    int size;
    int j = 0;
    HttpConnection ht;
    DataInputStream dins;
    Form typeinscriform = new Form("Type d'inscription");
    Form f = new Form("Connexion");
    Form f2 = new Form("Accueil");
    Form f3 = new Form("Inscription");
    Form f4 = new Form("Mon Profil");
    Form f5 = new Form("Déclarations");
    Form f6 = new Form("Consultation");
    Form f7 = new Form("Adoption");
    Form f8 = new Form("Recherche");
    Form f9 = new Form("Pensions");
    Form f10 = new Form("Changer mot de passe");
    Form loadingDialog = new Form("Patientez svp..");
    Form InfosAnimaux = new Form("Infos Animal : ");
    Form InfosPrestataire = new Form("Infos Prestataire : ");
    Form sureform = new Form("Confirmation de Supression : ");
    TextField motdepasseadherant = new TextField("Nouveau Mot de passe", null, 50, TextField.ANY);
    TextField ancienmotadherant = new TextField("Ancien Mot de passe", null, 50, TextField.ANY);
    TextField nomadherant = new TextField("Nom", null, 50, TextField.ANY);
    TextField prenomadherant = new TextField("Prenom", null, 50, TextField.ANY);
    TextField ageadherant = new TextField("Age", null, 50, TextField.NUMERIC);
    TextField telephoneadherant = new TextField("Telephone", null, 50, TextField.PHONENUMBER);
    TextField villeadherant = new TextField("Ville", null, 50, TextField.ANY);
    TextField idanimal = new TextField("Id", null, 50, TextField.UNEDITABLE);
    TextField nomanimal = new TextField("Nom", null, 50, TextField.ANY);
    TextField raceanimal = new TextField("Race", null, 50, TextField.ANY);
    TextField especeanimal = new TextField("Espece", null, 50, TextField.ANY);
    TextField ageanimal = new TextField("Age", null, 50, TextField.ANY);
    TextField adresseanimal = new TextField("Adresse", null, 50, TextField.ANY);
    TextField sexeanimal = new TextField("Sexe", null, 50, TextField.ANY);
    TextField vaccinanimal = new TextField("Vaccinée", null, 50, TextField.ANY);
    TextField etatanimal = new TextField("Etat", null, 50, TextField.ANY);
    TextField poidsanimal = new TextField("Poids", null, 50, TextField.ANY);
    TextField couleuranimal = new TextField("Couleur", null, 50, TextField.ANY);
    //Textfields de consulter Prestataire 
    TextField Nomprest = new TextField("Nom :", null, 50, TextField.UNEDITABLE);
    TextField Prenomprest = new TextField("Prénom :", null, 50, TextField.UNEDITABLE);
    TextField Domaineprest = new TextField("Service :", null, 50, TextField.UNEDITABLE);
    TextField Jour_dispprest = new TextField("Jour de Dispo :", null, 50, TextField.UNEDITABLE);
    TextField Prix_serviceprest = new TextField("Prix :", null, 50, TextField.UNEDITABLE);
    TextField Emailprest = new TextField("E-Mail :", null, 50, TextField.UNEDITABLE);
    TextField Telephonepres = new TextField("N° Tél :", null, 50, TextField.UNEDITABLE);
    TextField Adresseprest = new TextField("Adresse :", null, 50, TextField.UNEDITABLE);
    //Choix de Inscription 
    String[] ListeChoixInscription = {"Adhérant", "Prestataire"};
    ChoiceGroup choicegroupInscription = new ChoiceGroup("Qui etes Vous ?", ChoiceGroup.POPUP, ListeChoixInscription, null);
    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://127.0.0.1/zerzer/updateprofil.php";
    String url2 = "http://127.0.0.1/zerzer/supprimercompte.php";
    String nomAnimal = "";
    int ch;
    int ch2;
    int ch3;
    int ch4;
    int ch5;
    int sureint = 0;
    int repcons = 0;
    int entiermdp = 0;
    Ticker tk = new Ticker("Bienvenue Dans SOSAnimaux");
    TextField txtlogin = new TextField("Login", null, 50, TextField.ANY);
    TextField txtPassword = new TextField("Mot de passe", null, 50, TextField.PASSWORD);
    Alert alt = new Alert("Error", "Vous devez entrer votre login", null, AlertType.ERROR);
    Alert alt2 = new Alert("Error", "Vous devez entrer votre Mot de passe", null, AlertType.ERROR);
    Alert alt3 = new Alert("Error", "Vous devez remplir les deux champs", null, AlertType.ERROR);
    Alert alt4 = new Alert("Error", "Mot de passe Incorrect", null, AlertType.ERROR);
    Alert alt5 = new Alert("C'est Fait !", "Votre mot de passe a été changée avec succes", null, AlertType.CONFIRMATION);
    Image image;
    List listeajoutsadherant = new List("Mes Ajouts", List.IMPLICIT);
    List listeservices = new List("Liste de Services", List.IMPLICIT);
    private List choixconsultattion = new List("Type de consultation", List.IMPLICIT, new String[]{"Animaux", "Services", "Pensions"}, null);
    String Pass = "";
    String[] Champs = {"Gérer mon profil \n ", "Déclaration", "Consultation", "Adoption", "Recherche", "Pensions", "Nos Réalisations","Réclamation", "Déconnexion"};
    String[] Champspresta = {"Mon Profil", "Ajouter", "Déconnexion"};
    Image[] imgListe = new Image[9];
    Image[] imgListePrest = new Image[3];
    List menu;
    List menupresta;
    Command Choix = new Command("Choisir", Command.SCREEN, 0);
    private Command selection = new Command("Choisir", Command.ITEM, 1);
    Personne[] personnes;
    Personne[] Personne;
    Animal[] animaux;
    Animal[] Animaux;
    Prestataire[] prestataires;
    Prestataire[] Prestataire;
    StringBuffer sb = new StringBuffer();
    StringBuffer sb1 = new StringBuffer();
    StringBuffer sb2 = new StringBuffer();
    StringBuffer sb3 = new StringBuffer();
    StringBuffer sb4 = new StringBuffer();
    
    
    
    Command cmdchangerprofil = new Command("Changer", Command.EXIT, 0);
    Command retourprofil = new Command("Retour", Command.BACK, 0);
    int reponsethread = 0;
    String[] ListeOptions = {"Changer mon mot de passe", "Afficher mes déclarations", "Supprimer mon compte"};
    ChoiceGroup cgautreop = new ChoiceGroup("Autres Options", ChoiceGroup.POPUP, ListeOptions, null);
    String[] Affichemplist = {"Modifier", "Afficher la carte"};
    ChoiceGroup cgautreInfoGmap = new ChoiceGroup("Options", ChoiceGroup.POPUP, Affichemplist, null);
    double latitude = 0;
    double longtitude = 0;

    public Midlet() {
        try {
            imgListe[0] = Image.createImage("/myprofile.png");
            imgListe[1] = Image.createImage("/add-button-hi.png");
            imgListe[2] = Image.createImage("/check.png");
            imgListe[3] = Image.createImage("/adopt.png");
            imgListe[4] = Image.createImage("/dogloupe.png");
            imgListe[5] = Image.createImage("/vet.png");
            imgListe[6] = Image.createImage("/bluelogo.png");
            imgListe[7] = Image.createImage("/reclam.png");
            imgListe[8] = Image.createImage("/logout.png");
            imgListePrest[0] = Image.createImage("/myprofile.png");
            imgListePrest[1] = Image.createImage("/add-button-hi.png");
            imgListePrest[2] = Image.createImage("/logout.png");
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        menu = new List("Acceuil", List.IMPLICIT, Champs, imgListe);
        menupresta = new List("Acceuil", List.IMPLICIT, Champspresta, imgListePrest);

    }
    //inscription
    TextField txtnom = new TextField("Nom :*", null, 50, TextField.ANY);
    TextField txtprenom = new TextField("Prenom :*", null, 50, TextField.ANY);
    TextField txtmail = new TextField("Email :*", null, 50, TextField.ANY);
    TextField txtpass = new TextField("Mot de passe :*", null, 50, TextField.ANY);
    TextField txtadr = new TextField("Adresse :*", null, 50, TextField.ANY);
    TextField txtage = new TextField("Age :*", null, 50, TextField.NUMERIC);
    TextField Telephone = new TextField("Téléphone :*", null, 50, TextField.PHONENUMBER);
    Alert alert = new Alert("Error", "Veuillez saisir tous les champs obligatoires", null, AlertType.ERROR);

    //Inscription
    public void startApp() {
        
        formconfirmationAjout.addCommand(cmdRetourMenu);
                    formconfirmationAjout.setCommandListener(this);
        //Hamza
        listchoixr.append("perdu", null);
        listchoixr.append("sans refuge", null);
        listchoixr.append("sans foyer", null);
        listchoixr.addCommand(cmdParser);
        listchoixr.setCommandListener(this);
        lstr.addCommand(cmdBack2r);
        lstr.setCommandListener(this);
        forminfosr.addCommand(cmdBackr);
        forminfosr.addCommand(AfficherImgr);
        forminfosr.setCommandListener(this);
        imageAnr.addCommand(cmdBackinfosr);
        imageAnr.setCommandListener(this);
        listchoixr.addCommand(AfficherImgrretour);
        listchoixr.setCommandListener(this);
        imageAnr.setCommandListener(this);
      
        //Hamza
        
        
        //Donia
        formDeclarerAn.append(nomAna);
        formDeclarerAn.append(especea);
        formDeclarerAn.append(racea);
        formDeclarerAn.append(couleura);
        formDeclarerAn.append(chSexea);
        formDeclarerAn.append(poidsa);
        formDeclarerAn.append(ageAna);
        formDeclarerAn.append(chVaccina);
        formDeclarerAn.append(chEtata);
        formDeclarerAn.append(autrea);
        formDeclarerAn.addCommand(cmdValiderAjout);
        formDeclarerAn.addCommand(cmdAnnulerAjoutAn);
        formDeclarerAn.setCommandListener(this);

        //liste des animaux sans foyer
        //formulaire d'information d'un animal a adopter
        fadop.addCommand(cmdParsean);

        fadop.setCommandListener(this);
        InfoAn.addCommand(Adopter);
        InfoAn.addCommand(AfficherImg);
        InfoAn.addCommand(Annuler);
        InfoAn.setCommandListener(this);
lstpens.addCommand(cmdBackpensionss);
lstpens.setCommandListener(this);

        AnSansFoyerloading.setCommandListener(this);

        confirmAdop.append(stmsg);
        confirmAdop.addCommand(Confirmer);
        confirmAdop.addCommand(Annuler);
        confirmAdop.setCommandListener(this);

        imageAn.addCommand(RetourInfAn);
        imageAn.setCommandListener(this);


        msgconfirm.addCommand(Retour);
        msgconfirm.setCommandListener(this);

        lstAnSansFoyer.addCommand(Afficher);
        lstAnSansFoyer.addCommand(Retour);
        lstAnSansFoyer.setCommandListener(this);


        //Donia



        //Amal
        form_modif_prest.append(st2a);
        form_modif_prest.append(tmpa);
        form_modif_prest.append(tjdispa);
        form_modif_prest.append(tadra);
        form_modif_prest.append(tmaila);
        form_modif_prest.append(ttela);
        form_modif_prest.append(tprixsera);
        form_modif_prest.addCommand(cmdvlidermodifa);
        form_modif_prest.addCommand(cmdBackpra);
        form_modif_prest.setCommandListener(this);
        
        
form_reclamation.append(txtsender);
      
        form_reclamation.append(chsujet);
        form_reclamation.append(espam);
        form_reclamation.append(txtmessage);
        form_reclamation.addCommand(cmdBackam);
        form_reclamation.addCommand(cmdEnvoyeram);
        form_reclamation.setCommandListener(this);
        
        form_inscription_prest.append(st);
        form_inscription_prest.append(esp);
        form_inscription_prest.append(tnom);
        form_inscription_prest.append(tprenom);
        form_inscription_prest.append(tmp);
        form_inscription_prest.append(tadr);
        form_inscription_prest.append(tmail);
        form_inscription_prest.append(ttel);
        form_inscription_prest.append(chdomaine);
        form_inscription_prest.append(tprixser);
        form_inscription_prest.addCommand(cmdInscriptionprest);
        form_inscription_prest.addCommand(cmdBack);
        form_inscription_prest.setCommandListener(this);
        //Amal

        //Slim


        AjouterPension.append(txtadresse);
        AjouterPension.append(txttel);
        AjouterPension.append(txtprix);
        AjouterPension.append(txtjour);
        AjouterPension.append(txtnompension);

        AjouterPension.addCommand(ValiderAjout);
        AjouterPension.setCommandListener(this);
        AjouterPension.addCommand(Retourajoutpensions);
        AjouterPension.setCommandListener(this);


        fpens.append("Click Pensions to get your pension_list");
        fpens.addCommand(cmdParse);
        fpens.setCommandListener(this);
        lstpens.addCommand(SelectPensions);
        lstpens.setCommandListener(this);
        formpens.addCommand(cmdBackpension);
        formpens.setCommandListener(this);

        formpens.addCommand(AffcheNote);
        formpens.setCommandListener(this);
        Note.addCommand(cmdBackpension);
        Note.setCommandListener(this);

        //Slim
        disp.setCurrent(new MIDPCanvas());
        try {
            InputStream is = getClass().getResourceAsStream("/kitten4.wav");
            Player p = Manager.createPlayer(is, "audio/X-wav");
            p.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }

        try {
            image = Image.createImage("/homesos.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }





        disp.setCurrent(f);
        f.setTicker(tk);
        f.append(image);

        f.append(txtlogin);
        f.append(txtPassword);
        f3.setCommandListener((CommandListener) this);
        f3.addCommand(cmdValiderInscri);
        f.setCommandListener((CommandListener) this);
        f.addCommand(cmdConnect);
        f.setCommandListener((CommandListener) this);
        f.addCommand(cmdInscription);
        f.setCommandListener((CommandListener) this);
        f4.addCommand(cmdchangerprofil);

        f4.addCommand(retourprofil);
        f4.setCommandListener((CommandListener) this);

        listeservices.addCommand(retourprofil);
        listeservices.setCommandListener(this);


        f10.append(ancienmotadherant);
        f10.append(motdepasseadherant);
        f10.addCommand(cmdRetourchangermdp);
        f10.setCommandListener(this);
        f10.addCommand(cmdchangermdp);
        f10.setCommandListener(this);

        typeinscriform.addCommand(RetourTypeInscri);
        typeinscriform.setCommandListener(this);
        typeinscriform.addCommand(TypeInscri);
        typeinscriform.setCommandListener(this);

        typeinscriform.append(choicegroupInscription);

        //menu prestataire
        menupresta.addCommand(ValiderPresta);
        menupresta.setCommandListener(this);

        //


        //Le menu (Accueil)
        choixconsultattion.setSelectCommand(selectionconsultation);
        choixconsultattion.setCommandListener((CommandListener) this);
//        disp.setCurrent(menu);
        choixconsultattion.addCommand(selectionconsultation);


        listeservices.setSelectCommand(selectionservice);
        listeservices.setCommandListener((CommandListener) this);
        listeservices.addCommand(selectionconsultation);



        f3.append(txtnom);
        f3.append(txtprenom);
        f3.append(txtmail);
        f3.append(txtpass);
        f3.append(txtadr);
        radioButtons.append("Male", null);
        radioButtons.append("Female", null);
        f3.append(radioButtons);
        f3.append(txtage);
        f3.append(Telephone);
        combo.append("Tunis", null);
        combo.append("Sousse", null);
        combo.append("Kairouen", null);
        f3.append(combo);

        listeajoutsadherant.setCommandListener(this);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
      if (c==AfficherImgrretour)
          forminfosr.deleteAll();
            lstr.deleteAll();
      {disp.setCurrent(new Midlet.CanvasList("Accueil", Champs, imgListe));
      
      
      
      }
        
        //Hamza
        if (c == cmdParser) {
            loadanr=1;
            disp.setCurrent(loadingDialogr);
            recherche();
        }

        if (c == List.SELECT_COMMAND) {
            forminfosr.append("Informations Animal: \n");
            forminfosr.append(showAnimalr(lstr.getSelectedIndex()));
            disp.setCurrent(forminfosr);
        }

        if (c == cmdBackr) {
            forminfosr.deleteAll();
            disp.setCurrent(lstr);
        }

        if (c == cmdBack2r) {
            forminfosr.deleteAll();
            lstr.deleteAll();
            disp.setCurrent(listchoixr);
        }
        
        if (c == cmdBackinfosr) {
            disp.setCurrent(forminfosr);
        }

        if (c == AfficherImgr) {
            
            
            sureintr = 1;
            recherche();
        
            
        }

        
        
        
        //Hamza
        
        
        
        
        
        //Donia
        if (c == cmdValiderAjout) {
            if ( (nomAna.getString().equals(""))  || especea.getString().equals("") || couleura.getString().equals("") || ageAna.getString().equals("") || racea.getString().equals("") || poidsa.getString().equals("") || autrea.getString().equals("")) {
                disp.setCurrent(alertChampAjout);

            } else {
                
                ajoutanimal();
            }
        }
        
        if (c == cmdAnnulerAjoutAn) {
       disp.setCurrent(new CanvasList("Acceuil",Champs,imgListe));     
   }
            
if (c == cmdRetourMenu) {
   disp.setCurrent(new CanvasList("Acceuil",Champs,imgListe));
            }
        
        
        if (c == cmdParsean) {
            disp.setCurrent(AnSansFoyerloading);
            afichansansfoyer();
        }

        if (c == Afficher) {
            showAnimalSansFoyer(lstAnSansFoyer.getSelectedIndex());
        }
        if (c == Adopter) {

            disp.setCurrent(confirmAdop);
        }

        if (c == Confirmer) {
            ChangerEtatAdopte();
            EnvoiSmsAdopte();
            try {
                image = Image.createImage("/kalb.jpg");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }





        if (c == AfficherImg) {
            try {
                System.out.println(idimg);
                htImg = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/" + idimg + ".jpg");
                dtImg = htImg.openDataInputStream();
                size = (int) htImg.getLength();
                data = new byte[size];
                dtImg.readFully(data);
                PhotoAn = Image.createImage(data, 0, size);
                imageAn.append(PhotoAn);
                disp.setCurrent(imageAn);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }




        if (c == Annuler) {
            InfoAn.deleteAll();
            lstAnSansFoyer.deleteAll();
            disp.setCurrent(new CanvasList("Acceuil", Champs, imgListe));
        }
        if (c == Retour) {
            lstAnSansFoyer.deleteAll();
            InfoAn.deleteAll();
            disp.setCurrent(new CanvasList("Acceuil", Champs, imgListe));
        }
        if (c == RetourInfAn) {
            imageAn.deleteAll();
            disp.setCurrent(InfoAn);
        }

        //Donia




        //Slim
        if (c == cmdParse) {
            disp.setCurrent(loadingDialog);
            formpens.deleteAll();
            getPensions();
        }
        if (c == cmdBackpension) {
            formpens.deleteAll();
            disp.setCurrent(lstpens);

        }
        
        if (c == cmdBackpensionss) {
            formpens.deleteAll();
            disp.setCurrent(new Midlet.CanvasList("Accueil", Champs, imgListe));

        }


        if (c == SelectPensions) {

            formpens.append("Informations pension: \n");
            formpens.append(showPension(lstpens.getSelectedIndex()));
            disp.setCurrent(formpens);
            formpens.append(evaluation);
            formpens.append(resultatpen);
            formpens.setItemStateListener(this);
            formpens.addCommand(ValiderNote);
        }

        if (c == cmdBack) {
            formpens.deleteAll();
            disp.setCurrent(lstpens);
        }
        if (c == ValiderNote) {
            choix = 1;
            getPensions();


        }
        if (c == AffcheNote) {
            choix = 2;
            getPensions();
            disp.setCurrent(Note);



        }




        if (c == ValiderPresta) {
            if (menupresta.getSelectedIndex() == 0) {
                disp.setCurrent(form_modif_prest);
            }

            if (menupresta.getSelectedIndex() == 1) {
                disp.setCurrent(AjouterPension);
            }
            
            if (menupresta.getSelectedIndex() == 2) {
                disp.setCurrent(f);
            }

        }

        if (c == ValiderAjout) {
            if (txtadresse.getString().equals("")) {
                disp.setCurrent(alertError);
            } else if (txttel.getString().equals("") || txtprix.getString().equals("") || txtjour.getString().equals("") || txtnompension.getString().equals("")) {
                disp.setCurrent(alertError);
            } else {

                AjouterPensions();
            }
        }
        if (c == Retourajoutpensions) {
            disp.setCurrent(menupresta);

        }


        //Slim



        //Amal
        if (c == cmdvlidermodifa) {
                       modifpresta();
        }
        if (c == cmdBackpra) {
           //retour a l'écran d'accueil
          disp.setCurrent(menupresta);
        }
        
        
        if (c == cmdEnvoyeram) {
            envoyerreclam();
        }
        //revenir a la premiere page de l'application
        if (c == cmdBackam) {
        disp.setCurrent(new Midlet.CanvasList("Acceuil",Champs,imgListe));
        }
        
        
        if (c == cmdInscriptionprest) {
            if (tnom.getString().equals("") || tprenom.getString().equals("")
                    || tmp.getString().equals("") || tadr.getString().equals("") || tmail.getString().equals("")
                    || ttel.getString().equals("") || tprixser.getString().equals("")) {
                disp.setCurrent(alerte);
            }
            inscriptionpresta();
        }
        if (c == cmdBack) { //retour a l'écran d'accueil
            disp.setCurrent(f);
        }

        if (c == TypeInscri) {
            if (choicegroupInscription.getSelectedIndex() == 0) {
                disp.setCurrent(f3);
            }

            if (choicegroupInscription.getSelectedIndex() == 1) {
                disp.setCurrent(form_inscription_prest);
            }
        }

        if (c == RetourTypeInscri) {
            disp.setCurrent(f);
        }

        //Amal

        if (c == cmdConnect) {
            if (txtlogin.getString().equals("") || txtPassword.getString().equals("")) {
                alt.setTimeout(2000);//Alert.FOREVER pour rester forever
                disp.setCurrent(alt);
            } else {
                authentification();


            }
        }
        //validation inscription //Slim
        if (c == cmdValiderInscri) {
            if (txtnom.getString().equals("")) {
                disp.setCurrent(alert);
            } else if (txtprenom.getString().equals("") || txtmail.getString().equals("") || txtpass.getString().equals("") || txtadr.getString().equals("") || txtage.getString().equals("") || Telephone.getString().equals("")) {
                disp.setCurrent(alert);

            } else {

                inscriptionadherant();
            }
        }
        //Inscription commande
        if (c == cmdInscription) {

            disp.setCurrent(typeinscriform);
        }
        //Mon profil Changer
        if (c == cmdchangerprofil) {
            if ((cgautreop.getSelectedIndex() == 1)) {
                getanimaux();

            }
            if (cgautreop.getSelectedIndex() == 0) {
                disp.setCurrent(f10);

            }
            if ((cgautreop.getSelectedIndex() == 2)) {
                sureform.deleteAll();
                sureform.append("Etes vous sure de vouloir supprimer votre compte ?? Vous allez nous manquer !");
                sureform.addCommand(surecommand);
                sureform.setCommandListener(this);
                sureform.addCommand(surecommandretour);
                sureform.setCommandListener(this);
                disp.setCurrent(sureform);

            }
            modifieradherant();
//            sureform.deleteAll();
//        reponsethread=1;

        }
        //Affichage du profil retour
        if (c == retourprofil) {
            repcons = 0;
            listeajoutsadherant.deleteAll();
            listeservices.deleteAll();

            disp.setCurrent(new CanvasList("Acceuil", Champs, imgListe));
        }
        // Selectionne l'animal a afficher
        if (c == List.SELECT_COMMAND) {

            showAnimal(listeajoutsadherant.getSelectedIndex());

        }
        //Selection des services
        if (c == selectionservice) {

            showPrestataire(listeservices.getSelectedIndex());

        }
        //Retour a la liste des animaux
        if (c == cmdRetourListeAnim) {
            InfosAnimaux.deleteAll();
            listeajoutsadherant.deleteAll();
            disp.setCurrent(f4);
        }
        //affichage de liste d'ajouts animals
        if (c == cmdRetourAffichanimal) {
            InfosAnimaux.deleteAll();
//            listeajoutsadherant.deleteAll();

            disp.setCurrent(listeajoutsadherant);

        }
        //Commande d'affichage de la map
        if (c == cmdValiderchangeranimal) {
            if (cgautreInfoGmap.getSelectedIndex() == 1) {
                disp.setCurrent(new GoogleMapsMarkerCanvas(this, d, latitude, longtitude));
            }
            if (cgautreInfoGmap.getSelectedIndex() == 0) {
                nomAndon.setString(nomanimal.getString());
                especedon.setString(especeanimal.getString());
                racedon.setString(raceanimal.getString());
                ageAndon.setString(ageanimal.getString());
                poidsdon.setString(poidsanimal.getString());
                couleurdon.setString(couleuranimal.getString());



                fdon.append(nomAndon);
                fdon.append(especedon);
                fdon.append(ageAndon);
                fdon.append(racedon);
                fdon.append(poidsdon);
                fdon.append(couleurdon);
                fdon.append(chSexedon);
                fdon.append(chVaccindon);
                fdon.append(chEtatdon);

                fdon.addCommand(Validerdon);
                fdon.addCommand(Annulerdon);

                fdon.setCommandListener(this);
                disp.setCurrent(fdon);

            }
        }
        //valider modification
        if (c == Validerdon) {
            modifanimal();
        }


        //Changer mot de passe
        if (c == cmdchangermdp) {
            if (ancienmotadherant.getString().equals("") || motdepasseadherant.getString().equals("")) {
                alt3.setTimeout(2000);//Alert.FOREVER pour rester forever
                disp.setCurrent(alt3);
            }
            if (Pass.equals(ancienmotadherant.getString())) {
                entiermdp = 1;
                Changermdp chgmdp = new Changermdp();
                chgmdp.Changermdp(motdepasseadherant.getString(), ancienmotadherant.getString(),id_adher);
                alt5.setTimeout(2000);//Alert.FOREVER pour rester forever
                disp.setCurrent(alt5);
            } else {
                alt4.setTimeout(2000);//Alert.FOREVER pour rester forever
                disp.setCurrent(alt4);
            }
        }
        //Retour au profil depuis le form de mot de passe
        if (c == cmdRetourchangermdp) {
            entiermdp = 0;
            disp.setCurrent(f4);
        }
        //Selectionne la chose a consulter 
        if (c == selectionconsultation) {
            listeservices.deleteAll();

            boolean selected[] = new boolean[choixconsultattion.size()];

            // Fill array indicating whether each element is checked
            choixconsultattion.getSelectedFlags(selected);


            if (selected[0]) {
            }
            if (selected[1]) {
                getprestataires();
            }
            if (selected[2]) {
            }

        }
        //Retour a la liste des prestataires
        if (c == cmdRetourlisteprestataires) {
            InfosPrestataire.deleteAll();
            disp.setCurrent(listeservices);

        }
        //Confirmation de Suppression
        if (c == surecommand) {
            Supprimercompte();
        }
        //Confirmation de Suppression de compte Retour
        if (c == surecommandretour) {
            sureint = 0;
            disp.setCurrent(f4);
        }
    }

    public void itemStateChanged(Item item) {
        if (item == evaluation) {
            resultatpen.setText(String.valueOf(evaluation.getValue()));
        }
    }

    public void run() {
    }

    //canvas
    public class MIDPCanvas extends Canvas implements CommandListener {

        public MIDPCanvas() {

            try {
// Set up this canvas to listen to command events
                setCommandListener(this);
// Add the Exit command
                addCommand(new Command("Exit", Command.EXIT, 1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * paint
         */
        public void paint(Graphics g) {
//Set the screen to Fullscreen
            setFullScreenMode(true);
            g.setColor(0x00FFFFFF);
            g.fillRect(0, 0, getWidth(), getHeight());
            Image img = null;
            try {
//Set the picture 
                img = Image.createImage("/splash2.png");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            g.drawImage(img, getWidth() / 2, getHeight() / 2 - 5, 3);
            try {
//Set the loading time here. I set it as 1 second (1000 milliseconds)
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println("Error");
            }
        }

        public void commandAction(Command c, Displayable d) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public class CanvasList extends Canvas implements Runnable {

        protected int linePadding = 2;
        protected int margin = 2;
        protected int padding = 2;
        protected Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        // Background color
        protected int bgColor = 0x000033;
        // Ecriture
        protected int foreColor = 0x000000;
        protected int foreSelectedColor = 0xffffff;
        protected int backColor = 0x006699;
        protected int backSelectedColor = 0xFF9900;
        protected int borderWidth = 2;
        protected int borderColor = 0x000000;
        protected int borderSelectedColor = 0xff0000;
        // will contain item splitted lines
        String[][] itemLines = null;
        // will hold items image parts
        Image[] images = null;
        // will hold selected item index
        public int selectedItem = 0;
        // these will hold item graphical properties
        int[] itemsTop = null;
        int[] itemsHeight = null;
        // these will hold List vertical scrolling
        int scrollTop = 0;
        final int SCROLL_STEP = 40;

        public CanvasList(String title, String[] items, Image[] imageElements) {
            setTitle(title);

            this.images = imageElements;

            itemLines = new String[items.length][];

            itemsTop = new int[itemLines.length];
            itemsHeight = new int[itemLines.length];

            for (int i = 0; i < itemLines.length; i++) {
                // get image part of this item, if available
                Image imagePart = getImage(i);

                // get avaiable width for text
                int w = getItemWidth() - (imagePart != null ? imagePart.getWidth() + padding : 0);

                // and split item text into text rows, to fit available width 
                itemLines[i] = getTextRows((String) items[i], font, w);
            }
        }

        public int getItemWidth() {
            return getWidth() - 2 * borderWidth - 2 * padding - 2 * margin;
        }

        protected void keyPressed(int key) {
            int keyCode = getGameAction(key);

            // is there 1 item at least?
            if (itemLines.length > 0) {
                // going up
                if (keyCode == Canvas.UP) {
                    // current item is clipped on top, so can scroll up
                    if (itemsTop[selectedItem] < scrollTop) {
                        scrollTop -= SCROLL_STEP;

                        repaint();
                    } // is there a previous item?
                    else if (selectedItem > 0) {
                        selectedItem--;

                        repaint();
                    }
                } //going down
                else if (keyCode == Canvas.DOWN) {
                    // current item is clipped on bottom, so can scroll down
                    if (itemsTop[selectedItem] + itemsHeight[selectedItem] >= scrollTop + getHeight()) {
                        scrollTop += SCROLL_STEP;

                        repaint();
                    } // is there a following item?
                    else if (selectedItem < itemLines.length - 1) {
                        selectedItem++;

                        repaint();
                    }
                }

                if (keyCode == Canvas.FIRE) {
                    if (selectedItem == 0) {
                        Thread th = new Thread(this);
                        th.start();
                    }
                    if (selectedItem == 1) {
                        disp.setCurrent(formDeclarerAn);

                    }
                    if (selectedItem == 2) {
                        disp.setCurrent(choixconsultattion);
                    }
                    if (selectedItem == 3) {
                        disp.setCurrent(fadop);
                    }
                    if (selectedItem == 4) {
                        
                       disp.setCurrent(listchoixr);
                    }
                    if (selectedItem == 5) {
                        disp.setCurrent(fpens);
                    }
                    if (selectedItem == 6) {
                        disp.setCurrent(new DisplayVideoMIDle("Acceuil", Champs, imgListe));
                    }
                    if (selectedItem == 7) {
                        disp.setCurrent(form_reclamation);
                    }
                    if (selectedItem == 8) {
                        disp.setCurrent(f);
                    }



                }
            }
        }

        Image getImage(int index) {
            return images != null && images.length > index ? images[index] : null;
        }

        protected void paint(Graphics g) {
            // paint List background
            g.setColor(bgColor);
            g.fillRect(0, 0, getWidth(), getHeight());

            // translate accordingly to current List vertical scroll
            g.translate(0, -scrollTop);

            int top = 0;

            g.setFont(font);

            // loop List items
            for (int i = 0; i < itemLines.length; i++) {
                int itemRows = itemLines[i].length;

                Image imagePart = getImage(i);

                int itemHeight = itemRows * font.getHeight() + linePadding * (itemRows - 1);

                itemsTop[i] = top;
                itemsHeight[i] = itemHeight;

                // is image part higher than the text part?
                if (imagePart != null && imagePart.getHeight() > itemHeight) {
                    itemHeight = imagePart.getHeight();
                }
                itemHeight += 2 * padding + 2 * borderWidth;

                g.translate(0, top);

                if (borderWidth > 0) {
                    // paint item border
                    g.setColor(i == selectedItem ? borderSelectedColor : borderColor);
                    g.fillRect(margin, margin, getWidth() - 2 * margin, itemHeight);
                }

                // paint item background
                g.setColor(i == selectedItem ? backSelectedColor : backColor);
                g.fillRect(margin + borderWidth, margin + borderWidth, getWidth() - 2 * margin - 2 * borderWidth, itemHeight - 2 * borderWidth);

                // has this item an image part?
                if (imagePart != null) {
                    g.drawImage(imagePart, margin + borderWidth + padding, margin + borderWidth + padding, Graphics.TOP | Graphics.LEFT);
                }

                // paint item text rows
                g.setColor(i == selectedItem ? foreSelectedColor : foreColor);

                int textLeft = margin + borderWidth + padding + (imagePart != null ? imagePart.getWidth() + padding : 0);

                for (int j = 0; j < itemRows; j++) {
                    g.drawString(itemLines[i][j], textLeft, margin + borderWidth + padding + j * (linePadding + font.getHeight()), Graphics.TOP | Graphics.LEFT);
                }

                g.translate(0, -top);

                top += itemHeight + 2 * margin;
            }
            // finally, translate back
            g.translate(0, scrollTop);
        }

        public String[] getTextRows(String text, Font font, int width) {
            char SPACE_CHAR = ' ';
            String VOID_STRING = "";

            int prevIndex = 0;
            int currIndex = text.indexOf(SPACE_CHAR);

            Vector rowsVector = new Vector();

            StringBuffer stringBuffer = new StringBuffer();

            String currentToken;

            String currentRowText = VOID_STRING;

            while (prevIndex != -1) {
                int startCharIndex = prevIndex == 0 ? prevIndex : prevIndex + 1;

                if (currIndex != -1) {
                    currentToken = text.substring(startCharIndex, currIndex);
                } else {
                    currentToken = text.substring(startCharIndex);
                }

                prevIndex = currIndex;

                currIndex = text.indexOf(SPACE_CHAR, prevIndex + 1);

                if (currentToken.length() == 0) {
                    continue;
                }

                if (stringBuffer.length() > 0) {
                    stringBuffer.append(SPACE_CHAR);
                }

                stringBuffer.append(currentToken);

                if (font.stringWidth(stringBuffer.toString()) > width) {
                    if (currentRowText.length() > 0) {
                        rowsVector.addElement(currentRowText);
                    }
                    stringBuffer.setLength(0);

                    currentRowText = VOID_STRING;

                    stringBuffer.append(currentToken);

                    currentRowText = stringBuffer.toString();
                } else {
                    currentRowText = stringBuffer.toString();
                }
            }
            if (currentRowText.length() > 0) {
                rowsVector.addElement(currentRowText);
            }
            String[] rowsArray = new String[rowsVector.size()];

            rowsVector.copyInto(rowsArray);

            return rowsArray;
        }

        public void run() {
            if (selectedItem == 0) {
                try {
                    // this will handle our XML
                    PersonneHandler personnesHandler = new PersonneHandler();
                    // get a parser object
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/getXmlPersonnes.php?id=" + id_adher);
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    parser.parse(dis, personnesHandler);
                    // display the result
                    personnes = personnesHandler.getPersonne();

                    if (personnes.length > 0) {
                        for (int i = 0; i < personnes.length; i++) {
                            j = i;
                            nomadherant.setString(personnes[i].getNom());
                            prenomadherant.setString(personnes[i].getPrenom());
                            villeadherant.setString(personnes[i].getVille());
                            ageadherant.setString(personnes[i].getAge() + "");
                            telephoneadherant.setString(personnes[i].getTelephone() + "");
                            Pass = personnes[i].getMdp();
                            t2 = new Thread(
                                    new Runnable() {
                                public void run() {
                                    try {
                                        ht = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/" + personnes[j].getId() + ".jpg");
                                        dins = ht.openDataInputStream();
                                        size = (int) ht.getLength();
                                        data = new byte[size];

                                        dins.readFully(data);
                                        img = Image.createImage(data, 0, size);
                                        f4.append(img);
                                        f4.append(nomadherant);
                                        f4.append(prenomadherant);
                                        f4.append(villeadherant);
                                        f4.append(ageadherant);
                                        f4.append(telephoneadherant);

                                        f4.append(cgautreop);
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            t2.start();



//                    lst.append(personnes[i].getNom(), null);
//                    lst.append(personnes[i].getPrenom(), null);
//                    lst.append(personnes[i].getTelephone()+"", null);
//                    lst.append(personnes[i].getVille(), null);
//                    lst.append(personnes[i].getAge()+"", null);

                        }
                    }

                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
                disp.setCurrent(f4);






            }

        }
    }

    private void showAnimal(int i) {

        if (Animaux.length > 0) {
            idanimal.setString(Animaux[i].getId() + "");
            nomanimal.setString(Animaux[i].getNom() + "");
            raceanimal.setString(Animaux[i].getRace() + "");
            especeanimal.setString(Animaux[i].getEspece() + "");
            ageanimal.setString(Animaux[i].getAge() + "");
            etatanimal.setString(Animaux[i].getEtat() + "");
            poidsanimal.setString(Animaux[i].getPoids() + "");
            couleuranimal.setString(Animaux[i].getCouleur() + "");
            vaccinanimal.setString(Animaux[i].getVaccin() + "");
            sexeanimal.setString(Animaux[i].getSexe() + "");
            adresseanimal.setString(Animaux[i].getAdresse() + "");
            latitude = Animaux[i].getLat();
            longtitude = Animaux[i].getLon();
            InfosAnimaux.append("Infos Animal : " + Animaux[listeajoutsadherant.getSelectedIndex()].getNom());
            InfosAnimaux.append(idanimal);
            InfosAnimaux.append(nomanimal);
            InfosAnimaux.append(raceanimal);
            InfosAnimaux.append(especeanimal);
            InfosAnimaux.append(ageanimal);
            InfosAnimaux.append(etatanimal);
            InfosAnimaux.append(poidsanimal);
            InfosAnimaux.append(couleuranimal);
            InfosAnimaux.append(vaccinanimal);
            InfosAnimaux.append(sexeanimal);
            InfosAnimaux.append(adresseanimal);
            InfosAnimaux.addCommand(cmdRetourAffichanimal);
            InfosAnimaux.setCommandListener((CommandListener) this);
            InfosAnimaux.addCommand(cmdValiderchangeranimal);
            InfosAnimaux.setCommandListener((CommandListener) this);
            InfosAnimaux.append(cgautreInfoGmap);

            disp.setCurrent(InfosAnimaux);
        }

    }

    private void showPrestataire(int i) {

        if (prestataires.length > 0) {

            Nomprest.setString(prestataires[i].getNom() + "");
            Prenomprest.setString(prestataires[i].getPrenom() + "");
            Domaineprest.setString(prestataires[i].getDomaine() + "");
            Jour_dispprest.setString(prestataires[i].getJour_disp() + "");
            Prix_serviceprest.setString(prestataires[i].getPrix_serv() + "");
            Telephonepres.setString(prestataires[i].getTelephone() + "");
            Adresseprest.setString(prestataires[i].getAdresse() + "");

            InfosPrestataire.append("Informations Service : " + prestataires[listeservices.getSelectedIndex()].getDomaine());
            InfosPrestataire.append(Nomprest);
            InfosPrestataire.append(Prenomprest);
            InfosPrestataire.append(Domaineprest);
            InfosPrestataire.append(Jour_dispprest);
            InfosPrestataire.append(Prix_serviceprest);
            InfosPrestataire.append(Telephonepres);
            InfosPrestataire.append(Adresseprest);


            InfosPrestataire.addCommand(cmdRetourlisteprestataires);
            InfosPrestataire.setCommandListener((CommandListener) this);
            disp.setCurrent(InfosPrestataire);
        }

    }

    public void getanimaux() {
        t2 = new Thread(
                new Runnable() {
            public void run() {


                try {
                    // this will handle our XML
                    AnimalHandler animalHandler = new AnimalHandler();
                    // get a parser object
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/getXmlAnimaux.php?id="+id_adher);
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    parser.parse(dis, animalHandler);
                    // display the result
                    Animaux = animalHandler.getAnimal();

                    if (Animaux.length > 0) {
                        for (int i = 0; i < Animaux.length; i++) {
                            k = i;
                            listeajoutsadherant.append(Animaux[i].getNom(), null);


                        }
                    }


                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
                listeajoutsadherant.addCommand(cmdRetourListeAnim);

                disp.setCurrent(listeajoutsadherant);


            }
        });
        t2.start();
    }

    public void Supprimercompte() {
        t2 = new Thread(
                new Runnable() {
            public void run() {
                int idd = 0125;
                try {
                    hc = (HttpConnection) Connector.open(url2 + "?id=" + idd);
                    dis = new DataInputStream(hc.openDataInputStream());
                    while ((ch = dis.read()) != -1) {
                        sb.append((char) ch);
                    }
                    if ("successfully added".equalsIgnoreCase(sb.toString().trim())) {
                    } else {
                        disp.setCurrent(loadingDialog);
                        disp.setCurrent(f4);
                        sureint = 0;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        t2.start();
    }

    public void getprestataires() {
        t2 = new Thread(
                new Runnable() {
            public void run() {
                try {
                    // this will handle our XML
                    PrestataireHandler prestaHandler = new PrestataireHandler();
                    // get a parser object
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/getXmlPrestataires.php");
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    parser.parse(dis, prestaHandler);
                    // display the result
                    prestataires = prestaHandler.getPrestataire();

                    if (prestataires.length > 0) {
                        for (int i = 0; i < prestataires.length; i++) {

                            listeservices.append(prestataires[i].getDomaine(), null);


                        }
                    }

                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
                listeservices.addCommand(retourprofil);
                disp.setCurrent(listeservices);

            }
        });
        t2.start();
    }

    public void modifieradherant() {
        t2 = new Thread(
                new Runnable() {
            public void run() {
                try {
                    hc = (HttpConnection) Connector.open(url + "?nom=" + nomadherant.getString() + "&prenom=" + prenomadherant.getString() + "&ville=" + villeadherant.getString() + "&age=" + ageadherant.getString() + "&telephone=" + telephoneadherant.getString()+"&id="+id_adher);
                    dis = new DataInputStream(hc.openDataInputStream());
                    while ((ch = dis.read()) != -1) {
                        sb.append((char) ch);
                    }
                    if ("successfully added".equalsIgnoreCase(sb.toString().trim())) {
                        disp.setCurrent(loadingDialog);
                        disp.setCurrent(f4);
                    } else {
                        System.out.println("Probléme au niveau de l'insertion");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });
        t2.start();


    }

    public void inscriptionpresta() {
        t2 = new Thread(
                new Runnable() {
            public void run() {
                try {
                    hc = (HttpConnection) Connector.open(urlajoutprest + "?nom=" + tnom.getString() + "&prenom=" + tprenom.getString()
                            + "&prixser=" + Integer.parseInt(tprixser.getString()) + "&mdp=" + tmp.getString() + "&adresse=" + tadr.getString() + "&email=" + tmail.getString()
                            + "&telephone=" + Integer.parseInt(ttel.getString()) + "&domaine=" + chdomaine.getString(chdomaine.getSelectedIndex()));
                    dis = new DataInputStream(hc.openDataInputStream());
                    while ((ch = dis.read()) != -1) {
                        sb.append((char) ch);
                    }
                    if ("successfully added".equalsIgnoreCase(sb.toString().trim())) {
                        //informe l'utilisateur que son inscrption a été effectué
                        disp.setCurrent(alertesucc);
                        //renvoie l'utilisateur a l'accueil
                        disp.setCurrent(f);
                    } else {
                        disp.setCurrent(alerta);
                        disp.setCurrent(form_inscription_prest);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t2.start();

    }

    public void AjouterPensions() {
        t2 = new Thread(
                new Runnable() {
            public void run() {
                try {
                    hc = (HttpConnection) Connector.open(urlajoutpension + "?adr=" + txtadresse.getString() + "&tel=" + Integer.parseInt(txttel.getString()) + "&prix_serv=" + Double.parseDouble(txtprix.getString()) + "&jour_disp=" + txtjour.getString() + "&nom=" + txtnompension.getString());
                    dis = new DataInputStream(hc.openDataInputStream());
                    while ((ch = dis.read()) != -1) {
                        sb.append((char) ch);
                    }
                    if ("successfully added".equalsIgnoreCase(sb.toString().trim())) {
                        disp.setCurrent(alertError);

                    } else {

                        disp.setCurrent(alertsuccee);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        t2.start();

    }

    public void afichansansfoyer() {
        t2 = new Thread(
                new Runnable() {
            public void run() {
                try {
                    //afficher liste animaux sans foyer
                    // this will handle our XML
                    AnimalHandler animauxHandler = new AnimalHandler();
                    // get a parser object
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/getXmlAnimal.php");
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    parser.parse(dis, animauxHandler);
                    // display the result
                    animaux = animauxHandler.getAnimal();

                    if (animaux.length > 0) {
                        for (int i = 0; i < animaux.length; i++) {
                            lstAnSansFoyer.append(animaux[i].getNom() + " :  " + animaux[i].getEspece(), null);

                        }
                    }

                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
                disp.setCurrent(lstAnSansFoyer);
            }
        });
        t2.start();

    }

    public void EnvoiSmsAdopte() {//methode d'envoi d'sms de confirmation de demande d'adoption
        Thread th1;
        th1 = new Thread(
                new Runnable() {
            public void run() {
                try {
                    clientConn = (MessageConnection) Connector.open("sms://5550000");
                } catch (Exception e) {
                    Alert alertSendMsgCon = new Alert("Alert");
                    alertSendMsgCon.setString("Unable to connect to Station because of network problem");
                    alertSendMsgCon.setTimeout(2000);
                    disp.setCurrent(alertSendMsgCon);
                }
                try {
                    TextMessage textmessage = (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
                    textmessage.setAddress("sms://5550000");

                    textmessage.setPayloadText("votre animal vous attend dans Notre refuge SoS Animaux , son id est " + idimg);
                    clientConn.send(textmessage);
                } catch (Exception e) {
                    Alert alertSendMsg = new Alert("Alert", "", null, AlertType.INFO);
                    alertSendMsg.setTimeout(Alert.FOREVER);
                    alertSendMsg.setString("Unable to send");
                    disp.setCurrent(alertSendMsg);
                }
            }
        });
        th1.start();


    }

    public void ChangerEtatAdopte() {
        //methode pour changer l'etat de lanimal sans foyer en adopte
        Thread th = new Thread(
                new Runnable() {
            public void run() {


                try {
                    // this will handle our XML
                    AnimalHandler animauxHandler = new AnimalHandler();
                    // get a parser object
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/getXmlAnimal.php");
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    parser.parse(dis, animauxHandler);
                    // display the result
                    animaux = animauxHandler.getAnimal();
                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }


                try {

                    String url1 = urlAdopt + "?id=" + animaux[lstAnSansFoyer.getSelectedIndex()].getId();
                    hcAdopt = (HttpConnection) Connector.open(url1.trim());
                    disAdopt = new DataInputStream(hcAdopt.openDataInputStream());
                    while ((chAdopt = disAdopt.read()) != -1) {
                        sbAdopt.append((char) chAdopt);
                    }
                    if ("successfully updated".equalsIgnoreCase(sbAdopt.toString().trim())) {
                        msgconfirm.append(image);
                        disp.setCurrent(msgconfirm);
                    } else {
                        disp.setCurrent(alertUpdate);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        th.start();


    }

    private void showAnimalSansFoyer(int i) {


        if (animaux.length > 0) {
            idimg = animaux[i].getId();
            nomAn.setString(animaux[i].getNom());
            espece.setString(animaux[i].getEspece());
            race.setString(animaux[i].getRace());
            ageAn.setString(animaux[i].getAge());
            poids.setString(animaux[i].getPoids());
            couleur.setString(animaux[i].getCouleur());
            etat.setString(animaux[i].getEtat());
            vaccin.setString(animaux[i].getVaccin());
            sexe.setString(animaux[i].getSexe());
            autre.setString(animaux[i].getAutre());

            InfoAn.append(nomAn);
            InfoAn.append(espece);
            InfoAn.append(ageAn);
            InfoAn.append(race);
            InfoAn.append(poids);
            InfoAn.append(couleur);
            InfoAn.append(etat);
            InfoAn.append(vaccin);
            InfoAn.append(sexe);
            InfoAn.append(autre);

            disp.setCurrent(InfoAn);

        }

    }

    public void getPensions() {
        t2 = new Thread(
                new Runnable() {
            public void run() {
                try {
                    if (choix == 0) {
                        // this will handle our XML
                        PensionHandler PensionHandler = new PensionHandler();
                        // get a parser object
                        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                        // get an InputStream from somewhere (could be HttpConnection, for example)
                        HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/slim/get_xml_personnes.php");
                        DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                        parser.parse(dis, PensionHandler);
                        // display the result
                        pensionnes = PensionHandler.getPension();
                        disp.setCurrent(alertnote);
                        disp.setCurrent(lstpens);
                        if (pensionnes.length > 0) {
                            for (int i = 0; i < pensionnes.length; i++) {
                                lstpens.append(pensionnes[i].getNom(), null);

                            }

                        }
                    }
                    if (choix == 1) {
                        hc = (HttpConnection) Connector.open(urlajoutnote + "?id_pension=" + pensionnes[lstpens.getSelectedIndex()].getId() + "&note=" + evaluation.getValue());
                        dis = new DataInputStream(hc.openDataInputStream());
                        while ((ch = dis.read()) != -1) {
                            sbajout.append((char) ch);
                        }
                        if ("successfully added".equalsIgnoreCase(sbajout.toString().trim())) {
                            disp.setCurrent(alertnote);

                        }

                    }
                    if (choix == 2) {
                        // this will handle our XML
                        PensionHandler PensionHandler = new PensionHandler();
                        // get a parser object
                        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                        // get an InputStream from somewhere (could be HttpConnection, for example)
                        HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/slim/get_xml_note.php");
                        DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                        parser.parse(dis, PensionHandler);
                        // display the result
                        pensionnes = PensionHandler.getPension();

                        if (pensionnes.length > 0) {
                            for (int i = 0; i < pensionnes.length; i++) {
                                Note.append("" + (pensionnes[i].getNb() * 100) / pensionnes[i].getNote() + "% des Gens");

                            }

                        }

                    }

                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }


            }
        });
        t2.start();
    }

    public void inscriptionadherant() {
        t2 = new Thread(
                new Runnable() {
            public void run() {
                try {
                    hc = (HttpConnection) Connector.open(urlinscri + "?nom=" + txtnom.getString() + "&prenom=" + txtprenom.getString() + "&email=" + txtmail.getString() + "&mdp=" + txtpass.getString() + "&telephone=" + Integer.parseInt(Telephone.getString()) + "&ville=" + combo.getString(combo.getSelectedIndex()) + "&adresse=" + txtadr.getString() + "&age=" + Integer.parseInt(txtage.getString()) + "&sexe=" + radioButtons.getString(radioButtons.getSelectedIndex()));
                    dis = new DataInputStream(hc.openDataInputStream());
                    while ((ch2 = dis.read()) != -1) {
                        sb3.append((char) ch2);
                    }
                    if ("successfully added".equalsIgnoreCase(sb3.toString().trim())) {
                        disp.setCurrent(alt33);
                        disp.setCurrent(f);

                    } else {
                        disp.setCurrent(alerta);
                        disp.setCurrent(f3);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        t2.start();
    }

    public void modifanimal() {
        t2 = new Thread(
                new Runnable() {
            public void run() {
                try {

                    String valsexe = chSexedon.getString(chSexedon.getSelectedIndex());
                    String valetat = chEtatdon.getString(chEtatdon.getSelectedIndex());
                    String valvaccin = chVaccindon.getString(chVaccindon.getSelectedIndex());
                    String url1 = urldon + "?nom=" + nomAndon.getString() + "&espece=" + especedon.getString() + "&age=" + ageAndon.getString() + "&race=" + racedon.getString() + "&poids=" + poidsdon.getString() + "&couleur=" + couleurdon.getString() + "&vaccin=" + valvaccin + "&etat=" + valetat + "&sexe=" + valsexe + "&id=" + Integer.parseInt(idanimal.getString());
                    hc = (HttpConnection) Connector.open(url1.trim());
                    dis = new DataInputStream(hc.openDataInputStream());
                    while ((ch = dis.read()) != -1) {
                        sb.append((char) ch);
                    }
                    if ("successfully updated".equalsIgnoreCase(sb.toString().trim())) {
                        disp.setCurrent(alerta);
                    } else {
                        disp.setCurrent(fdon);

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });
        t2.start();
    }
    public void envoyerreclam()
    {
        t2 = new Thread(
                new Runnable() {
            public void run() {
         try {
            String sender = Util.EncodeURL.encode(txtsender.getString());
            String sujet = Util.EncodeURL.encode(chsujet.getString(chsujet.getSelectedIndex()));
            String message = Util.EncodeURL.encode(txtmessage.getString());
            System.out.println(urlmail + "?sender=" + sender + "&subject=" + sujet + "&body=" + message);
            hc = (HttpConnection) Connector.open(url + "?sender=" + sender + "&subject=" + sujet + "&body=" + message);
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
                System.out.println("sb : " + sb);
            }
            if (sb.toString().length()!=0) {
               
                disp.setCurrent(alertesucca);
                disp.setCurrent(new Midlet.CanvasList("Accueil", Champs, imgListe));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
             
        });
        t2.start();
    
}
    
    
    public void ajoutanimal()
    {
        t2 = new Thread(
                new Runnable() {
            public void run() {
     try {
            String valsexe=chSexea.getString(chSexea.getSelectedIndex());
            String valetat=chEtata.getString(chEtata.getSelectedIndex());
            String valvaccin=chVaccina.getString(chVaccina.getSelectedIndex());
            String url1=urlajoutanimal+"?nom="+nomAna.getString()+"&espece="+especea.getString()+"&age="+ageAna.getString()+"&race="+racea.getString()+"&poids="+poidsa.getString()+"&couleur="+couleura.getString()+"&vaccin="+valvaccin+"&etat="+valetat+"&autre="+autrea.getString()+"&sexe="+valsexe+"&id="+id_adher;
                hc = (HttpConnection) Connector.open(url1.trim());               
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {                    
                    sb.append((char)ch);
                }
                if ("successfully added".equalsIgnoreCase(sb.toString().trim())) {
                    
                    formconfirmationAjout.append(sp3);
                    formconfirmationAjout.append(msgAjoutAn);
                    disp.setCurrent(formconfirmationAjout);
                }else{
                     
                    disp.setCurrent(alertDeclarerAn);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            }
        });
        t2.start();
    
                } 
    
    public void modifpresta()
    {
        t2 = new Thread(
                new Runnable() {
            public void run() {
        //update pretataire informations
        String str= "prixser=" + Integer.parseInt(tprixsera.getString()) + "&mdp=" + tmpa.getString() + "&adresse=" + tadra.getString() + "&email=" + tmaila.getString()
                  + "&telephone=" + Integer.parseInt(ttela.getString()) + "&diponibilite=" + tjdispa.getString()+"&id="+id_presta;
         System.out.println(str); 
        try {
            hc = (HttpConnection) Connector.open(urlmodifpresta +str);
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            System.out.println(sb);
            if ("successfully updated".equalsIgnoreCase(sb.toString().trim())) {
                disp.setCurrent(alertesuccaa);
                //retour a l'accueil
                disp.setCurrent(new Midlet.CanvasList("Accueil",Champs,imgListe));
            } else {
                disp.setCurrent(alertama);
                 //retour a l'accueil
                disp.setCurrent(new Midlet.CanvasList("Accueil",Champs,imgListe));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         }
        });
        t2.start();
        
    }
    
    public void getidbymailadh()
    {
        t2 = new Thread(
                new Runnable() {
            public void run() {
    try {
                    // this will handle our XML
                    PersonneHandler personnesHandler = new PersonneHandler();
                    // get a parser object
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/getidbyemail.php?email=" + txtlogin.getString());
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    parser.parse(dis, personnesHandler);
                    // display the result
                    Personne = personnesHandler.getPersonne();

                    if (Personne.length > 0) {
                        for (int i = 0; i < Personne.length; i++) {
                           
                            id_adher=(Personne[i].getId());
                            
                          

                        }
                        System.out.println(id_adher);
                disp.setCurrent(new Midlet.CanvasList("Accueil", Champs, imgListe));
                    }

                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
        





}
        });
        t2.start();
            

                    }
    
    public void getidbymailpresta()
    {
        t2 = new Thread(
                new Runnable() {
            public void run() {
    try {
                    // this will handle our XML
                    PrestataireHandler prestaHandler = new PrestataireHandler();
                    // get a parser object
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/getidbyemailprest.php?email=" + txtlogin.getString());
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    parser.parse(dis, prestaHandler);
                    // display the result
                    Prestataire = prestaHandler.getPrestataire();

                    if (Prestataire.length > 0) {
                        for (int i = 0; i < Prestataire.length; i++) {
                           
                            id_presta=(Prestataire[i].getId());
                            
                          

                        }
                        System.out.println(id_presta);
                disp.setCurrent(menupresta);
                    }

                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
        System.out.println(id_presta);
                disp.setCurrent(menupresta);

                    }
    
        });
        t2.start();
}
    
    
    public void authentification()
    {
        t2 = new Thread(
                new Runnable() {
            public void run() {
        
        try {
            
                hc = (HttpConnection) Connector.open(urlauth+"?login="+ txtlogin.getString() + "&mp=" + txtPassword.getString());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch2 = dis.read()) != -1) {
                    sb1.append((char) ch2);
                }
            //System.out.println(sb.toString());
                if ("existe_adh".equalsIgnoreCase(sb1.toString().trim())) {
                    System.out.println("adher");
                   getidbymailadh();
                }else
                    if ("existe_pre".equalsIgnoreCase(sb1.toString().trim())) {
                        System.out.println("prest");
                   getidbymailpresta();
                }
                
                    
                
                else{
                    disp.setCurrent(altCon);
                }
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
            }
    });
        t2.start();
    
    }
    
    
    public void recherche()
    { 
        t2 = new Thread(
                new Runnable() {
            public void run() {
        if (loadanr==1){
            
        
        String choix;

        if (listchoixr.getString(listchoixr.getSelectedIndex()).equals("sans refuge")) {
            choix = "sans_refuge";
        } else if (listchoixr.getString(listchoixr.getSelectedIndex()).equals("sans foyer")) {
            choix = "sans_foyer";
        } else {
            choix = listchoixr.getString(listchoixr.getSelectedIndex());
        }


        try {
            // this will handle our XML
            AnimalHandler animalsHandler = new AnimalHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/getXmlAnimals.php?etat=" + choix);
            System.out.println("appel effectué");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());

            parser.parse(dis, animalsHandler);
            // display the result
            animals = animalsHandler.getAnimal();

            if (animals.length > 0) {
                for (int i = 0; i < animals.length; i++) {
                    lstr.append(animals[i].getNom(), null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lstr);
        
        loadanr=0;
        }
    
        
        if (sureintr == 1) {
            try {

                htImgr = (HttpConnection) Connector.open("http://127.0.0.1/zerzer/"+idimgr+".jpg");
                dtImgr = htImgr.openDataInputStream();
                size = (int) htImgr.getLength();
                data = new byte[size];
                dtImgr.readFully(data);
                PhotoAnr = Image.createImage(data, 0, size);
                imageAnr.append(PhotoAnr);
                disp.setCurrent(imageAnr);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("impossible d'afficher l'image");
            }
            sureintr=0;
        }
    }
    });
        t2.start();
    }
           
    
   private String showAnimalr(int i) {
        String res = "";
        if (animals.length > 0) {
            sb.append("Espece: ");
            sb.append(animals[i].getEspece());
            sb.append("\n");
            sb.append("Etat: ");
            sb.append(animals[i].getEtat());
            sb.append("\n");
            sb.append("Couleur: ");
            sb.append(animals[i].getCouleur());
            sb.append("\n");
            sb.append("Vaccin: ");
            sb.append(animals[i].getVaccin());
            sb.append("\n");
            
            idimgr=animals[i].getId();
            System.out.println("image id = "+ idimg);
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    } 

    public void retour(String titre, String[] list, Image[] imglist) {
        disp.setCurrent(new CanvasList(titre, list, imglist));

    }

    private String showPension(int i) {
        String res = "";
        if (pensionnes.length > 0) {

            sb.append(pensionnes[i].getAdr());
            sb.append("\n");
            sb.append(pensionnes[i].getPrix_serv());
            sb.append("\n");
            sb.append(pensionnes[i].getNb());
            sb.append("\n");

        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
}
