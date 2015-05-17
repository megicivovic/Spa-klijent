/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import forme.klijent.FKlijentLogin;
import java.io.IOException;
import java.net.Socket;
import komunikacija.Komunikacija;

/**
 *
 * @author Megi
 */
public class KlijentStart {

    public static void main(String[] args) {
        try {
            Socket soket = new Socket("127.0.0.1", 9000);
            System.out.println("Klijent se uspesno povezao sa serverom");
            Komunikacija.getInstanca().setSoket(soket);
            FKlijentLogin fkl = new FKlijentLogin();
            fkl.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
