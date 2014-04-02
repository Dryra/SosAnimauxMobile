package sosanimauxmobile;

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
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPersonne != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentPersonne.setId(id);
            } else
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentPersonne.setNom(nom);
            } else
                    if (prenTag.equals("open")) {
                String desc = new String(ch, start, length).trim();
                currentPersonne.setPrenom(desc);
            }
        }
    }
    
}
