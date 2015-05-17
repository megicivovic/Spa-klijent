/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author student1
 */
public class Komunikacija {

    private Socket soket;
    private static Komunikacija instanca;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    public static Komunikacija getInstanca() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

    public void setSoket(Socket soket) throws IOException {
        this.soket = soket;        
        out = new ObjectOutputStream(soket.getOutputStream());
        in = new ObjectInputStream(soket.getInputStream());
    }
    public void posaljiZahtev(KlijentTransferObjekat kto) throws IOException{
        out.writeObject(kto);
    }
    
    public ServerTransferObjekat procitajOdgovor() throws IOException, ClassNotFoundException{
        return (ServerTransferObjekat) in.readObject();
    }

}
