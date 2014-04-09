package Handlers;
import Entity.Pension;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author slim
 */
public class PensionHandler extends DefaultHandler {

    private Vector pensionnes;
    String idTag = "close";
    String id_prestTag = "close";
    String adrTag = "close";
    String telTag = "close";
    String prix_servTag = "close";
    String jour_dispTag = "close";
    String noteTag = "close";
    String nomTag = "close";
    String nbTag = "close";

    public PensionHandler() {
        pensionnes = new Vector();
    }

    public Pension[] getPension() {
        Pension[] pensionness = new Pension[pensionnes.size()];
        pensionnes.copyInto(pensionness);
        return pensionness;
    }
    private Pension currentPensions;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("pension")) {

            if (currentPensions != null) {
                throw new IllegalStateException("already processing a pension");
            }
            currentPensions = new Pension();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("id_prest")) {
            id_prestTag = "open";
        } else if (qName.equals("adr")) {
            adrTag = "open";
        } else if (qName.equals("tel")) {
            telTag = "open";
        } else if (qName.equals("prix_serv")) {
            prix_servTag = "open";
        } else if (qName.equals("jour_disp")) {
            jour_dispTag = "open";
        } else if (qName.equals("note")) {
            noteTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("nb")) {
            nbTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("pension")) {
            // we are no longer processing a <reg.../> tag
            pensionnes.addElement(currentPensions);
            currentPensions = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("id_prest")) {
            id_prestTag = "close";
        } else if (qName.equals("adr")) {
            adrTag = "close";
        } else if (qName.equals("tel")) {
            telTag = "close";
        } else if (qName.equals("prix_serv")) {
            prix_servTag = "close";
        } else if (qName.equals("jour_disp")) {
            jour_dispTag = "close";
        } else if (qName.equals("note")) {
            noteTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("nb")) {
            nbTag = "close";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPensions != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentPensions.setId(Integer.parseInt(id));
            } else if (id_prestTag.equals("open")) {
                String id_prest = new String(ch, start, length).trim();
                currentPensions.setId_prest(Integer.parseInt(id_prest));
            } else if (adrTag.equals("open")) {
                String adr = new String(ch, start, length).trim();
                currentPensions.setAdr(adr);
            } else if (telTag.equals("open")) {
                String tel = new String(ch, start, length).trim();
                currentPensions.setTel(Integer.parseInt(tel));

            } else if (prix_servTag.equals("open")) {
                String prix_serv = new String(ch, start, length).trim();
                currentPensions.setPrix_serv(Double.parseDouble(prix_serv));
            } else if (jour_dispTag.equals("open")) {
                String jour_disp = new String(ch, start, length).trim();
                currentPensions.setJour_disp(jour_disp);
            } else if (noteTag.equals("open")) {
                String note = new String(ch, start, length).trim();
                currentPensions.setNote(Integer.parseInt(note));
            } else if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentPensions.setNom(nom);

            } else if (nbTag.equals("open")) {
                String nb = new String(ch, start, length).trim();
                currentPensions.setNb(Integer.parseInt(nb));

            }


        }

    }
}
