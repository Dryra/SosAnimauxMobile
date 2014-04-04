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
public class AnimalHandler extends DefaultHandler{
    private Vector animaux;
    String idTag = "close";
    String nomTag = "close";
    String especeTag = "close";
    String ageTag = "close";
    String raceTag = "close" ;
    String poidsTag = "close";
    String couleurTag = "close";
    String vaccinTag = "close";
    String etatTag = "close";
    String sexeTag = "close";
    String adresseTag = "close";

    public AnimalHandler() {
        animaux= new Vector();
    }

    public Animal[] getAnimal() {
        Animal[] animauxx = new Animal[animaux.size()];
        animaux.copyInto(animauxx);
        return animauxx;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Animal currentAnimal;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("animal")) {

            if (currentAnimal != null) {
                throw new IllegalStateException("already processing an animal");
            }
            currentAnimal = new Animal();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("espece")) {
            especeTag = "open";
        
        } else if (qName.equals("age")) {
            ageTag = "open";
        }
        else if (qName.equals("race")) {
            raceTag = "open";
        }
        else if (qName.equals("poids")) {
            poidsTag = "open";
        }
        else if (qName.equals("couleur")) {
            couleurTag = "open";
        }
        else if (qName.equals("vaccin")) {
            vaccinTag = "open";
        }
        else if (qName.equals("etat")) {
            etatTag = "open";
        }
        else if (qName.equals("sexe")) {
            sexeTag = "open";
        }
        else if (qName.equals("adr")) {
            adresseTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("animal")) {
            // we are no longer processing a <reg.../> tag
            animaux.addElement(currentAnimal);
            currentAnimal = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("espece")) {
            especeTag = "close";
        
        } else if (qName.equals("age")) {
            ageTag = "close";
        }
        else if (qName.equals("race")) {
            raceTag = "close";
        }
        else if (qName.equals("poids")) {
            poidsTag = "close";
        }
        else if (qName.equals("couleur")) {
            couleurTag = "close";
        }
        else if (qName.equals("vaccin")) {
            vaccinTag = "close";
        }
        else if (qName.equals("etat")) {
            etatTag = "close";
        }
        else if (qName.equals("sexe")) {
            sexeTag = "close";
        }
        else if (qName.equals("adr")) {
            adresseTag = "close";
        }
        
        
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentAnimal != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentAnimal.setId(Integer.parseInt(id));
            } else
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentAnimal.setNom(nom);
            } else
                    if (especeTag.equals("open")) {
                String espece = new String(ch, start, length).trim();
                currentAnimal.setEspece(espece);
            }
           else
                    if (ageTag.equals("open")) {
                String Age = new String(ch, start, length).trim();
                currentAnimal.setAge(Age);
            }
            else
                    if (raceTag.equals("open")) {
                String race = new String(ch, start, length).trim();
                
                currentAnimal.setRace(race);
            }
            
            else
                    if (poidsTag.equals("open")) {
                String poids = new String(ch, start, length).trim();
                 
                currentAnimal.setPoids(poids);
            }
            else
                    if (couleurTag.equals("open")) {
                String couleur = new String(ch, start, length).trim();
                 
                currentAnimal.setCouleur(couleur);
            }
            else
                    if (vaccinTag.equals("open")) {
                String vaccin = new String(ch, start, length).trim();
                 
                currentAnimal.setVaccin(vaccin);
            }
            else
                    if (etatTag.equals("open")) {
                String etat = new String(ch, start, length).trim();
                 
                currentAnimal.setEtat(etat);
            }
            else
                    if (sexeTag.equals("open")) {
                String sexe = new String(ch, start, length).trim();
                 
                currentAnimal.setSexe(sexe);
            }
            else
                    if (adresseTag.equals("open")) {
                String adresse = new String(ch, start, length).trim();
                 
                currentAnimal.setAdresse(adresse);
            }
        }
    }
    
}

