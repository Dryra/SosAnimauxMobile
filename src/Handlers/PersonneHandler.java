package Handlers;

import Entity.Personne;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Wael Mallek
 */
public class PersonneHandler extends DefaultHandler{
    private Vector personnes;
    String idTag = "close";
    String nomTag = "close";
    String prenTag = "close";
    String villeTag = "close";
    String telTag = "close" ;
    String ageTag = "close";
    String mdpTag = "close";

    public PersonneHandler() {
        personnes = new Vector();
    }

    public Personne[] getPersonne() {
        Personne[] personness = new Personne[personnes.size()];
        personnes.copyInto(personness);
        return personness;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Personne currentPersonne;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("personne")) {

            if (currentPersonne != null) {
                throw new IllegalStateException("already processing a personnes");
            }
            currentPersonne = new Personne();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("prenom")) {
            prenTag = "open";
        
        } else if (qName.equals("ville")) {
            villeTag = "open";
        }
        else if (qName.equals("telephone")) {
            telTag = "open";
        }
        else if (qName.equals("age")) {
            ageTag = "open";
        }
        else if (qName.equals("mdp")) {
            mdpTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("personne")) {
            // we are no longer processing a <reg.../> tag
            personnes.addElement(currentPersonne);
            currentPersonne = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("prenom")) {
            prenTag = "close";
        }
        else if (qName.equals("ville")) {
            villeTag = "close";
        }
        else if (qName.equals("telephone")) {
            telTag = "close";
        }
        
        else if (qName.equals("age")) {
            ageTag = "close";
        }
        else if (qName.equals("mdp")) {
            mdpTag = "close";
        }
        
        
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPersonne != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentPersonne.setId(Integer.parseInt(id));
            } else
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentPersonne.setNom(nom);
            } else
                    if (prenTag.equals("open")) {
                String desc = new String(ch, start, length).trim();
                currentPersonne.setPrenom(desc);
            }
           else
                    if (villeTag.equals("open")) {
                String ville = new String(ch, start, length).trim();
                currentPersonne.setVille(ville);
            }
            else
                    if (telTag.equals("open")) {
                String telephone = new String(ch, start, length).trim();
                int tel = Integer.parseInt(telephone);
                currentPersonne.setTelephone(tel);
            }
            
            else
                    if (ageTag.equals("open")) {
                String age = new String(ch, start, length).trim();
                 int ag = Integer.parseInt(age);
                currentPersonne.setAge(ag);
            }
            else
                    if (mdpTag.equals("open")) {
                String mdp = new String(ch, start, length).trim();
                 
                currentPersonne.setMdp(mdp);
            }
        }
    }
    
}
