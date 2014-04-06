/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sosanimauxmobile;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Dryra
 */
public class Changermdp implements Runnable{
    public static String ancien ;
    public static String nouveau ;
    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost:8888/zerzer/updatepassword.php";
    int ch;
    StringBuffer sb = new StringBuffer();
    public void Changermdp(String nouveau,String ancien)
            
    {
        System.out.println(Changermdp.nouveau);
    Changermdp.ancien=ancien;
    Changermdp.nouveau=nouveau;
    changer(Changermdp.nouveau);
    }
    
    public void changer (String nouveau )
    {
    Thread th=new Thread(this);
           th.start();
    }

    public void run() {
        
        try {
            hc = (HttpConnection) Connector.open(url + "?password=" + Changermdp.nouveau );
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("successfully added".equalsIgnoreCase(sb.toString().trim())) {
                 
            } else {
                System.out.println("Probléme au niveau de l'insertion du mot de passe");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
