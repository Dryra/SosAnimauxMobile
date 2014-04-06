/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sosanimauxmobile;

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
    Command cmdValiderchangeranimal = new Command("Valider", Command.EXIT, 0);
    Command cmdRetourAffichanimal = new Command("Retour", Command.EXIT, 0);
    Command cmdRetourlisteprestataires = new Command("Retour", Command.EXIT, 0);
    Command cmdRetourListeAnim = new Command("Retour", Command.BACK, 0);
    Command cmdRetourchangermdp = new Command("Retour", Command.BACK, 0);
    Command cmdchangermdp = new Command("Valider", Command.EXIT, 0);
    private Command selectionconsultation=new Command("CONSULTER", Command.ITEM, 1);
    private Command selectionservice=new Command("Afficher", Command.ITEM, 1);
    
    
    Form f = new Form("Connexion");
    Form f2 = new Form("Accueil");
    Form f3 = new Form("Inscription");
    Form f4=new Form("Mon Profil");
    Form f5=new Form("Déclarations");
    Form f6=new Form("Consultation");
    Form f7=new Form("Adoption");
    Form f8=new Form("Recherche");
    Form f9=new Form("Pensions");
    Form f10=new Form("Changer mot de passe");
    Form loadingDialog = new Form("Patientez svp..");
    Form InfosAnimaux=new Form("Infos Animal : ");
    Form InfosPrestataire=new Form("Infos Prestataire : ");
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
    
    
    
    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost:8888/zerzer/updateprofil.php";
    String nomAnimal ="";
            
    int ch;
    
    int repcons=0;
    
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
private List choixconsultattion = new List("Type de consultation", List.IMPLICIT, new String[] {"Animaux", "Services","Pensions"}, null);
    
    String Pass="" ;
    String[] Champs={"Gérer mon profil \n ","Déclaration","Consultation","Adoption","Recherche","Pensions","Déconnexion"} ;
    Image[] imgListe = new Image[7];
    List menu;
    Command Choix = new Command("Choisir",Command.SCREEN , 0) ;
    
    private Command selection=new Command("Choisir", Command.ITEM, 1);
    
    Personne[] personnes;
    Animal[] Animaux;
    Prestataire[] prestataires;
    StringBuffer sb = new StringBuffer();
    Command cmdchangerprofil = new Command("Changer", Command.EXIT, 0);
        Command retourprofil = new Command("Retour", Command.BACK, 0);
int reponsethread = 0 ;
String[] ListeOptions={"Changer mon mot de passe","Afficher mes déclarations"};
ChoiceGroup cgautreop = new ChoiceGroup("Autres Options", ChoiceGroup.POPUP,ListeOptions,null);
String[] Affichemplist={"Afficher la carte"};

ChoiceGroup cgautreInfoGmap = new ChoiceGroup("Afficher l'emplacement", ChoiceGroup.POPUP,Affichemplist,null);
    double latitude = 0;
    double longtitude=0;

public Midlet()
    {  
        try {
            imgListe[0] = Image.createImage("/myprofile.png");
            imgListe[1] = Image.createImage("/add-button-hi.png");
            imgListe[2] = Image.createImage("/check.png");
            imgListe[3] = Image.createImage("/adopt.png");
            imgListe[4] = Image.createImage("/dogloupe.png");
            imgListe[5] = Image.createImage("/vet.png");
            imgListe[6] = Image.createImage("/logout.png");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        menu = new List("Acceuil", List.IMPLICIT, Champs,imgListe) ;
        
        
}
    
    
    

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
       disp.setCurrent(new MIDPCanvas() ); 
        
    
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
        
        
        
       f10.append(ancienmotadherant);
       f10.append(motdepasseadherant);
       f10.addCommand(cmdRetourchangermdp);
       f10.setCommandListener(this);
       f10.addCommand(cmdchangermdp);
       f10.setCommandListener(this);
       
       
        
        
        
        
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

        if (c == cmdConnect) {
            if (txtlogin.getString().equals("") || txtPassword.getString().equals("")) {
                alt.setTimeout(2000);//Alert.FOREVER pour rester forever
                disp.setCurrent(alt);
            } else {

                disp.setCurrent(new CanvasList("Acceuil",Champs,imgListe));

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
        if(c==cmdInscription)
        {
        
        disp.setCurrent(f3);
        }
        
        if(c==cmdchangerprofil)
        {
        reponsethread=1;
        Thread th=new Thread(this);
           th.start();
        }
        if(c==retourprofil)
        {
        
        disp.setCurrent(new CanvasList("Acceuil",Champs,imgListe));
        }
        
        if (c == List.SELECT_COMMAND) {
            
            showAnimal(listeajoutsadherant.getSelectedIndex());
            
        }
        
        if (c == selectionservice) {
            
            showPrestataire(listeservices.getSelectedIndex());
            
        }
        if(c==cmdRetourListeAnim)
            
        { InfosAnimaux.deleteAll();
            listeajoutsadherant.deleteAll();
            disp.setCurrent(f4);
        }
        
        if(c==cmdRetourAffichanimal)
            
        { InfosAnimaux.deleteAll();
//            listeajoutsadherant.deleteAll();
            
            disp.setCurrent(listeajoutsadherant);
        
        }
        if(c==cmdValiderchangeranimal)
        { if (cgautreInfoGmap.getSelectedIndex()==0)
        { disp.setCurrent(new GoogleMapsMarkerCanvas(this, d,latitude,longtitude));
        }
        }
        
        if(c==cmdchangermdp)
        { 
            if (ancienmotadherant.getString().equals("") || motdepasseadherant.getString().equals("")) {
                alt3.setTimeout(2000);//Alert.FOREVER pour rester forever
                disp.setCurrent(alt3);
            }
            if (Pass.equals(ancienmotadherant.getString()))
        {
            Changermdp chgmdp = new Changermdp();
            chgmdp.Changermdp(motdepasseadherant.getString(), ancienmotadherant.getString());
             alt5.setTimeout(2000);//Alert.FOREVER pour rester forever
                disp.setCurrent(alt5);
        }
            else 
            { alt4.setTimeout(2000);//Alert.FOREVER pour rester forever
                disp.setCurrent(alt4);
            }
        }
        
         if(c==cmdRetourchangermdp)
         { disp.setCurrent(f10);
         }
        
         if (c==selectionconsultation)
        {

            boolean selected[] = new boolean[choixconsultattion.size()];

      // Fill array indicating whether each element is checked
      choixconsultattion.getSelectedFlags(selected);


        if (  selected[0] )
        {
        
        }
      if (  selected[1] )
        {
      repcons=1;
        Thread th=new Thread(this);
           th.start();
        }
      if (  selected[2] )
        {
       
        }

        }
         
         if(c==cmdRetourlisteprestataires)
         {
             disp.setCurrent(listeservices);
         }
        
    }

    public void itemStateChanged(Item item) {
    }

    public void run() {
        if ((cgautreop.getSelectedIndex()==0))
                {
                   disp.setCurrent(f10);
                    
                }
        
        if((cgautreop.getSelectedIndex()==1))
        {
            try {
            // this will handle our XML
            AnimalHandler animalHandler = new AnimalHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:8888/zerzer/getXmlAnimaux.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, animalHandler);
            // display the result
            Animaux = animalHandler.getAnimal();

            if (Animaux.length > 0) {
                for (int i = 0; i < Animaux.length; i++) {
                     
                    listeajoutsadherant.append(Animaux[i].getNom(), null);
                   

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
            listeajoutsadherant.addCommand(cmdRetourListeAnim);
        disp.setCurrent(listeajoutsadherant);
        
        }
        
        if((repcons==1))
        {
            try {
            // this will handle our XML
            PrestataireHandler prestaHandler = new PrestataireHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:8888/zerzer/getXmlPrestataires.php");
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
            listeservices.addCommand(cmdRetourListeAnim);
        disp.setCurrent(listeservices);
        
        }
        
        
if(reponsethread==1)
            {
            try {
            hc = (HttpConnection) Connector.open(url + "?nom=" + nomadherant.getString() + "&prenom=" + prenomadherant.getString()+ "&ville=" + villeadherant.getString()+ "&age=" + ageadherant.getString()+ "&telephone=" + telephoneadherant.getString());
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
    
    
    }

    
    
     
    
    
    
    
    //canvas
    public class MIDPCanvas extends Canvas implements CommandListener {

public MIDPCanvas() {
try {
// Set up this canvas to listen to command events
setCommandListener(this);
// Add the Exit command
addCommand(new Command("Exit", Command.EXIT, 1));
} catch(Exception e) {
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
Image img=null;
try {
//Set the picture 
img = Image.createImage("/splash2.png");
} catch (IOException ex) {
ex.printStackTrace();
}
g.drawImage(img, getWidth() / 2, getHeight() / 2 - 5, 3);
try{
//Set the loading time here. I set it as 1 second (1000 milliseconds)
Thread.sleep(3000);
}
catch(Exception e){
System.out.println("Error");
}
}

        

        public void commandAction(Command c, Displayable d) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
 
    
    }
    
    public class CanvasList extends Canvas implements Runnable
{
	protected int linePadding = 2;
	protected int margin = 2;
	protected int padding = 2;
	protected Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
	// Background color
	protected int bgColor = 0x000033;
	// Ecriture
	protected int foreColor = 0x000000;
	protected int foreSelectedColor = 0xffffff;
        
        
	protected int backColor =0x006699;
        
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
	
	public CanvasList(String title, String[] items, Image[] imageElements)
	{
		setTitle(title);
		
		this.images = imageElements;
		
		itemLines = new String[items.length][];
		
		itemsTop = new int[itemLines.length];
		itemsHeight = new int[itemLines.length];
		
		for(int i = 0; i < itemLines.length; i++)
		{
			// get image part of this item, if available
			Image imagePart = getImage(i);
			
			// get avaiable width for text
			int w = getItemWidth() - (imagePart != null ? imagePart.getWidth() + padding : 0);
			
			// and split item text into text rows, to fit available width 
			itemLines[i] = getTextRows((String) items[i], font, w);
		}
	}

        
	public int getItemWidth()
	{
		return getWidth() - 2 * borderWidth - 2 * padding - 2 * margin;
	}
	protected void keyPressed(int key) 
	{
		int keyCode = getGameAction(key);
		
		// is there 1 item at least?
		if(itemLines.length > 0)
		{
			// going up
			if(keyCode == Canvas.UP)
			{
				// current item is clipped on top, so can scroll up
				if(itemsTop[selectedItem] < scrollTop)
				{
					scrollTop -= SCROLL_STEP;
					
					repaint();
				}
				// is there a previous item?
				else if(selectedItem > 0)
				{
					selectedItem--;
					
					repaint();
				}
			}
			//going down
			else if(keyCode == Canvas.DOWN)
			{
				// current item is clipped on bottom, so can scroll down
				if(itemsTop[selectedItem] + itemsHeight[selectedItem] >= scrollTop + getHeight())
				{
					scrollTop += SCROLL_STEP;
					
					repaint();
				}
				// is there a following item?
				else if(selectedItem < itemLines.length - 1)
				{
					selectedItem++;
					
					repaint();
				}
			}
                        
                        if (keyCode==Canvas.FIRE)
                        {
                           if (selectedItem==0)
                           { Thread th=new Thread(this);
           th.start();
                           }    
                        if (selectedItem==1)
                            
                           {
                              Thread th=new Thread(this);
           th.start();
                               
                           }
                        if (selectedItem==2)
                           { disp.setCurrent(choixconsultattion);
                           }
                        if (selectedItem==3)
                           { disp.setCurrent(f7);
                           }
                        if (selectedItem==4)
                           { disp.setCurrent(f8);
                           }
                        if (selectedItem==5)
                           { 
                               
                           }
                        if (selectedItem==6)
                           { 
                             disp.setCurrent(f);  
                           }
                        
                           
                           
                        }
		}
	}
	
	Image getImage(int index)
	{
		return images != null && images.length > index ? images[index] : null;
	}
	
	protected void paint(Graphics g)
	{
		// paint List background
		g.setColor(bgColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// translate accordingly to current List vertical scroll
		g.translate(0, - scrollTop);
		
		int top = 0;
		
		g.setFont(font);
		
		// loop List items
		for(int i = 0; i < itemLines.length; i++)
		{
			int itemRows = itemLines[i].length;
			
			Image imagePart = getImage(i);
			
			int itemHeight = itemRows * font.getHeight() + linePadding * (itemRows - 1);
			
			itemsTop[i] = top;
			itemsHeight[i] = itemHeight;
			
			// is image part higher than the text part?
			if(imagePart != null && imagePart.getHeight() > itemHeight)
			{
				itemHeight = imagePart.getHeight();
			}
			itemHeight += 2 * padding + 2 * borderWidth;
			
			g.translate(0, top);
			
			if(borderWidth > 0)
			{
				// paint item border
				g.setColor(i == selectedItem ? borderSelectedColor : borderColor);
				g.fillRect(margin, margin, getWidth() - 2 * margin, itemHeight);
			}
			
			// paint item background
			g.setColor(i == selectedItem ? backSelectedColor : backColor);
			g.fillRect(margin + borderWidth, margin + borderWidth, getWidth() - 2 * margin - 2 * borderWidth, itemHeight - 2 * borderWidth);
			
			// has this item an image part?
			if(imagePart != null)
			{
				g.drawImage(imagePart, margin + borderWidth + padding, margin + borderWidth + padding, Graphics.TOP | Graphics.LEFT);
			}
			
			// paint item text rows
			g.setColor(i == selectedItem ? foreSelectedColor : foreColor);
			
			int textLeft = margin + borderWidth + padding + (imagePart != null ? imagePart.getWidth() + padding : 0);
			
			for(int j = 0; j < itemRows; j++)
			{
				g.drawString(itemLines[i][j], textLeft, margin + borderWidth + padding + j * (linePadding + font.getHeight()), Graphics.TOP | Graphics.LEFT);
			}
			
			g.translate(0, - top);
			
			top += itemHeight + 2 * margin;
		}
		// finally, translate back
		g.translate(0, scrollTop);
	}
	
	public String[] getTextRows(String text, Font font, int width)
	{
		char SPACE_CHAR = ' ';
		String VOID_STRING = "";
		
		int prevIndex = 0;
		int currIndex = text.indexOf(SPACE_CHAR);
	
		Vector rowsVector = new Vector();
		
		StringBuffer stringBuffer = new StringBuffer();
		
		String currentToken;
		
		String currentRowText = VOID_STRING;
	
		while(prevIndex != -1)
		{
			int startCharIndex = prevIndex == 0 ? prevIndex : prevIndex + 1;
			
			if(currIndex != -1)
				currentToken = text.substring(startCharIndex, currIndex);
			else
				currentToken = text.substring(startCharIndex);
			
			prevIndex = currIndex;
			
			currIndex = text.indexOf(SPACE_CHAR, prevIndex + 1);
			
			if(currentToken.length() == 0)
			{
				continue;
			}
			
			if(stringBuffer.length() > 0)
	    		stringBuffer.append(SPACE_CHAR);
			
			stringBuffer.append(currentToken);
				
		    if(font.stringWidth(stringBuffer.toString()) > width) 
		    {
		    	if(currentRowText.length() > 0)
		    	{
		    		rowsVector.addElement(currentRowText);
		    	}
		        stringBuffer.setLength(0);
		        
		        currentRowText = VOID_STRING;
		        
		        stringBuffer.append(currentToken);
		        	
		        currentRowText = stringBuffer.toString();
		    }
		    else
		    {
		    	currentRowText = stringBuffer.toString();
		    }
		}
		if(currentRowText.length() > 0)
	    {
	    	rowsVector.addElement(currentRowText);
	    }
		String[] rowsArray = new String[rowsVector.size()];
		
		rowsVector.copyInto(rowsArray);
	
		return rowsArray;
	}

        public void run() {
            if (selectedItem==0) 
            {
          try {
            // this will handle our XML
            PersonneHandler personnesHandler = new PersonneHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:8888/zerzer/getXmlPersonnes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
            personnes = personnesHandler.getPersonne();

            if (personnes.length > 0) {
                for (int i = 0; i < personnes.length; i++) {
                    nomadherant.setString(personnes[i].getNom());
                    prenomadherant.setString(personnes[i].getPrenom());
                    villeadherant.setString(personnes[i].getVille());
                    ageadherant.setString(personnes[i].getAge()+"");
                    telephoneadherant.setString(personnes[i].getTelephone()+"");
                    Pass=personnes[i].getMdp();
                    
                    
                    
                    f4.append(nomadherant);
                    f4.append(prenomadherant);
                    f4.append(villeadherant);
                    f4.append(ageadherant);
                    f4.append(telephoneadherant);
                    
                    f4.append(cgautreop);
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
            idanimal.setString(Animaux[i].getId()+"") ;
            nomanimal.setString(Animaux[i].getNom()+"") ;
            raceanimal.setString(Animaux[i].getRace()+"") ;
            especeanimal.setString(Animaux[i].getEspece()+"") ;
            ageanimal.setString(Animaux[i].getAge()+"") ;
            etatanimal.setString(Animaux[i].getEtat()+"") ;
            poidsanimal.setString(Animaux[i].getPoids()+"") ;
            couleuranimal.setString(Animaux[i].getCouleur()+"") ;
            vaccinanimal.setString(Animaux[i].getVaccin()+"") ;
            sexeanimal.setString(Animaux[i].getSexe()+"") ;
            adresseanimal.setString(Animaux[i].getAdresse()+"") ;
            latitude=Animaux[i].getLat();
            longtitude=Animaux[i].getLon();
 InfosAnimaux.append("Infos Animal : "+ Animaux[listeajoutsadherant.getSelectedIndex()].getNom());
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
            
            Nomprest.setString(prestataires[i].getNom()+"") ;
            Prenomprest.setString(prestataires[i].getPrenom()+"") ;
            Domaineprest.setString(prestataires[i].getDomaine()+"") ;
            Jour_dispprest.setString(prestataires[i].getJour_disp()+"") ;
            Prix_serviceprest.setString(prestataires[i].getPrix_serv()+"") ;
            Telephonepres.setString(prestataires[i].getTelephone()+"") ;
            Adresseprest.setString(prestataires[i].getAdresse()+"") ;
            
 InfosPrestataire.append("Informations Service : "+ prestataires[listeservices.getSelectedIndex()].getDomaine());
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
 
    
   
   
   
    
}
