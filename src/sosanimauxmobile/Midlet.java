/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sosanimauxmobile;

import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;

/**
 * @author Dryra
 */
public class Midlet extends MIDlet implements CommandListener, ItemStateListener, Runnable {

    private ChoiceGroup radioButtons = new ChoiceGroup("Sexe", Choice.EXCLUSIVE);
    private ChoiceGroup combo = new ChoiceGroup("Ville", Choice.EXCLUSIVE);
    Display disp = Display.getDisplay(this);
    Command cmdConnect = new Command("Se Connecter", Command.SCREEN, 0);
    Command cmdInscription = new Command("Inscription", Command.EXIT, 0);
    Command cmdValiderInscri = new Command("Valider", Command.BACK, 0);
    Form f = new Form("Connexion");
    Form f2 = new Form("Accueil");
    Form f3 = new Form("Inscription");
    Ticker tk = new Ticker("Bienvenue Dans SOSAnimaux");
    TextField txtlogin = new TextField("Login", null, 50, TextField.ANY);
    TextField txtPassword = new TextField("Mot de passe", null, 50, TextField.PASSWORD);
    Alert alt = new Alert("Error", "Vous devez entrer votre login", null, AlertType.ERROR);
    Alert alt2 = new Alert("Error", "Vous devez entrer votre login", null, AlertType.ERROR);
    Image image;
    //inscription
    TextField txtnom = new TextField("Nom :*", null, 50, TextField.ANY);
    TextField txtprenom = new TextField("Prenom :*", null, 50, TextField.ANY);
    TextField txtmail = new TextField("Email :*", null, 50, TextField.ANY);
    TextField txtpass = new TextField("Mot de passe :*", null, 50, TextField.ANY);
    TextField txtadr = new TextField("Adresse :*", null, 50, TextField.ANY);
    TextField txtage = new TextField("Age :*", null, 50, TextField.ANY);
    TextField Telephone = new TextField("Téléphone :*", null, 50, TextField.ANY);
    Alert alert = new Alert("Error", "Veuillez saisie tous les champs obligatoires", null, AlertType.ERROR);

    //Inscription
    public void startApp() {
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

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {

        if (c == cmdConnect) {
            if (txtlogin.getString().equals("") || txtPassword.getString().equals("")) {
                alt.setTimeout(2000);//Alert.FOREVER pour rester forever
                disp.setCurrent(alt);
            } else {

                disp.setCurrent(f2);

            }
        } else if (c == cmdInscription) {
            disp.setCurrent(f3);
        }
        if (c == cmdValiderInscri) {
            if (txtnom.getString().equals("") || txtprenom.getString().equals("")
                    || txtmail.getString().equals("") || txtPassword.getString().equals("") || txtadr.getString().equals("")) {
                disp.setCurrent(alert);
            }
        }
    }

    public void itemStateChanged(Item item) {
    }

    public void run() {
    }
}
