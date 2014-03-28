/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sosanimauxmobile;

import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;

/**
 * @author Dryra
 */
public class Midlet extends MIDlet implements CommandListener, ItemStateListener, Runnable {

    Display disp = Display.getDisplay(this);
    Command cmdConnect = new Command("Se Connecter", Command.SCREEN, 0);
    Command cmdInscription = new Command("Annuler", Command.BACK, 0);
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
        f3.addCommand(cmdValiderInscri);
f.setCommandListener((CommandListener) this); 
        f.addCommand(cmdConnect);
        f.setCommandListener((CommandListener) this);
        f.addCommand(cmdInscription);
        f.setCommandListener((CommandListener) this);
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
        }
    }

    public void itemStateChanged(Item item) {
    }

    public void run() {
    }
}
