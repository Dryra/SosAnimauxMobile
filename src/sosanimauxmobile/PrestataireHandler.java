/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sosanimauxmobile;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Dryra
 */
public class PrestataireHandler extends DefaultHandler{
    private Vector prestataires;
    String idTag = "close";
    String nomTag = "close";
    String prenTag = "close";
    String adrTag = "close";
    String jourdispTag = "close" ;
    String prixTag = "close";
    String domaineTag = "close";
String telephoneTag = "close" ;

    public PrestataireHandler() {
        prestataires = new Vector();
    }

    public Prestataire[] getPrestataire() {
        Prestataire[] prestatairess = new Prestataire[prestataires.size()];
        prestataires.copyInto(prestatairess);
        return prestatairess;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Prestataire currentPrestataire;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("prestataire")) {

            if (currentPrestataire != null) {
                throw new IllegalStateException("already processing a prestataire");
            }
            currentPrestataire = new Prestataire();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("prenom")) {
            prenTag = "open";
        
        } else if (qName.equals("adr")) {
            adrTag = "open";
        }
        else if (qName.equals("jour_disp")) {
            jourdispTag = "open";
        }
        else if (qName.equals("prix_pens")) {
            prixTag = "open";
        }
        else if (qName.equals("domaine")) {
            domaineTag = "open";
        }
        else if (qName.equals("tel")) {
            telephoneTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("prestataire")) {
            // we are no longer processing a <reg.../> tag
            prestataires.addElement(currentPrestataire);
            currentPrestataire = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("prenom")) {
            prenTag = "close";
        
        } else if (qName.equals("adr")) {
            adrTag = "close";
        }
        else if (qName.equals("jour_disp")) {
            jourdispTag = "close";
        }
        else if (qName.equals("prix_pens")) {
            prixTag = "close";
        }
        else if (qName.equals("domaine")) {
            domaineTag = "close";
        }
        else if (qName.equals("tel")) {
            telephoneTag = "close";
        }
        
        
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPrestataire != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                
                currentPrestataire.setId(Integer.parseInt(id));
            } else
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentPrestataire.setNom(nom);
            } else
                    if (prenTag.equals("open")) {
                String prenom = new String(ch, start, length).trim();
                currentPrestataire.setPrenom(prenom);
            }
           else
                    if (adrTag.equals("open")) {
                String ville = new String(ch, start, length).trim();
                currentPrestataire.setAdresse(ville);
            }
            else
                    if (jourdispTag.equals("open")) {
                String jourdisp = new String(ch, start, length).trim();
                
                currentPrestataire.setJour_disp(jourdisp);
            }
            
            else
                    if (prixTag.equals("open")) {
                String prix = new String(ch, start, length).trim();
                 
                currentPrestataire.setPrix_serv(Double.valueOf(prix));
            }
            else
                    if (domaineTag.equals("open")) {
                String domaine = new String(ch, start, length).trim();
                 
                currentPrestataire.setDomaine(domaine);
            }
            else
                    if (telephoneTag.equals("open")) {
                String telephone = new String(ch, start, length).trim();
                 
                currentPrestataire.setTelephone(Integer.parseInt(telephone));
            }
        }
    }
    
}
