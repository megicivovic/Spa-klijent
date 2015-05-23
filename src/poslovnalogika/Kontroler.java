/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika;

import domen.Kompanija;
import domen.Korisnik;
import domen.Preparat;
import domen.Raspored;
import domen.Rezervacija;
import domen.Tretman;
import domen.TretmanPreparati;
import domen.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import protokol.Protokol;
import protokol.Status;
import protokol.objekti.KlijentZahtev;
import protokol.objekti.ServerOdgovor;

/**
 *
 * @author Ivana
 */
public class Kontroler {

    private Korisnik aktivniKlijent;
    private Socket socket;
//    private LogInForm logInForm;
//    private SlucajeviKoriscenja slucajeviKoriscenja;
    private List<Zaposleni> listaZaposlenih;
    private List<Tretman> listaTretmana;
    private List<Raspored> listaRasporeda;
    private List<TretmanPreparati> listaPreparataTretmana;
    private Preparat preparat;
    private List<Preparat> listaPreparata;
    private List<Kompanija> listaKompanija;

    private Kontroler() {
    }

    public List<Preparat> getListaPreparata() {
        return listaPreparata;
    }

    public void setListaPreparata(List<Preparat> listaPreparata) {
        this.listaPreparata = listaPreparata;
    }

    public List<TretmanPreparati> getListaPreparataTretmana() {
        return listaPreparataTretmana;
    }

    public void setListaPreparataTretmana(List<TretmanPreparati> listaPreparataTretmana) {
        this.listaPreparataTretmana = listaPreparataTretmana;
    }

    private static class KontrolerHolder {

        private static final Kontroler INSTANCE = new Kontroler();
    }

    public static Kontroler getInstance() {
        return KontrolerHolder.INSTANCE;
    }

    public Korisnik getAktivniKlijent() {
        return aktivniKlijent;
    }

    public void setAktivniKlijent(Korisnik aktivniKlijent) {
        this.aktivniKlijent = aktivniKlijent;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

//    public LogInForm getLogInForm() {
//        return logInForm;
//    }
//
//    public void setLogInForm(LogInForm logInForm) {
//        this.logInForm = logInForm;
//    }
//
//    public SlucajeviKoriscenja getSlucajeviKoriscenja() {
//        return slucajeviKoriscenja;
//    }
//
//    public void setSlucajeviKoriscenja(SlucajeviKoriscenja slucajeviKoriscenja) {
//        this.slucajeviKoriscenja = slucajeviKoriscenja;
//    }
  

    private Object posaljiPrimi(KlijentZahtev klijentZahtev) throws Exception {
        ServerOdgovor serverOdgovor = null;
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(klijentZahtev);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        serverOdgovor = (ServerOdgovor) ois.readObject();
        Status status = serverOdgovor.getStatus();
        if (status == Status.IZUZETAK) {
            throw (Exception) serverOdgovor.getObjekat();
        }
        return serverOdgovor.getObjekat();
    }

    public boolean ulogujSe(String korisnickoIme, String sifra) throws Exception {
        Korisnik klijent = new Korisnik();
        klijent.setKorisnickoIme(korisnickoIme);
        klijent.setKorisnickaSifra(sifra);
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(klijent);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_ULOGUJ_SE);
        klijent = (Korisnik) posaljiPrimi(klijentZahtev);
        if (klijent != null) {
            setAktivniKlijent(klijent);
            return true;
        }
        return false;
    }

    public void registrujSe(Korisnik klijent) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(klijent);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_REGISTRUJ_SE);
        klijent = (Korisnik) posaljiPrimi(klijentZahtev);
        setAktivniKlijent(klijent);
    }

    public boolean validirajKorisnickoIme(String korisnickoIme) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(korisnickoIme);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VALIDIRAJ_KORISNICKO_IME);
        return (boolean) posaljiPrimi(klijentZahtev);
    }

    public boolean validirajIme(String ime) {
        if (ime == null || ime.isEmpty()) {
            return false;
        }
        if ((int) ime.charAt(0) < 65 || (int) ime.charAt(0) > 90) {
            return false;
        }
        for (int i = 1; i < ime.length(); i++) {
            if ((int) ime.charAt(i) < 97 || (int) ime.charAt(i) > 122) {
                return false;
            }
        }
        return true;
    }

    public Date validirajDatum(String sVreme) throws Exception {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sVreme);
        } catch (ParseException ex) {
            throw new Exception("Datum nije ispravno unet!");
        }
    }

    public boolean validirajSifru(String sifra) {
        if (sifra == null) {
            return false;
        }
        return sifra.length() >= 8;
    }

    public List<Zaposleni> vratiSveZaposlene() throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VRATI_SVE_ZAPOSLENE);
        listaZaposlenih = (List<Zaposleni>) posaljiPrimi(klijentZahtev);
        return listaZaposlenih;
    }

    public List<Tretman> vratiSveTretmane() throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VRATI_SVE_TRETMANE);
        listaTretmana = (List<Tretman>) posaljiPrimi(klijentZahtev);
        return listaTretmana;
    }

    public List<Raspored> vratiSveRasporede() throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VRATI_SVE_RASPOREDE);
        listaRasporeda = (List<Raspored>) posaljiPrimi(klijentZahtev);
        return listaRasporeda;
    }

    public List<Preparat> vratiSvePreparate() throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VRATI_SVE_PREPARATE);
        listaPreparata = (List<Preparat>) posaljiPrimi(klijentZahtev);
        return listaPreparata;
    }
    public List<Kompanija> vratiSveKompanije() throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VRATI_SVE_KOMPANIJE);
        listaKompanija = (List<Kompanija>) posaljiPrimi(klijentZahtev);
        return listaKompanija;
    }

    public void dodajRezervaciju(Rezervacija r) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(r);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_DODAJ_REZERVACIJU);
        posaljiPrimi(klijentZahtev);
    }
    public int dodajTretman(Tretman t) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(t);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_DODAJ_TRETMAN);
       return (int) posaljiPrimi(klijentZahtev);
    }

    public void stampajPDF(Rezervacija r) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(r);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_STAMPAJ_PDF);
        posaljiPrimi(klijentZahtev);
    }

    public List<TretmanPreparati> vratiSvePreparateTretmana(Tretman t) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(t);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VRATI_SVE_PREPARATE_TRETMANA);
        listaPreparataTretmana = (List<TretmanPreparati>) posaljiPrimi(klijentZahtev);
        return listaPreparataTretmana;
    }

    public void obrisiPreparateTretmana(Tretman t) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(t);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_OBRISI_PREPARATE_TRETMANA);
        posaljiPrimi(klijentZahtev);

    }
    public void obrisiRasporedeTretmana(Tretman t) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(t);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_OBRISI_RASPOREDE_TRETMANA);
        posaljiPrimi(klijentZahtev);

    }
    public void obrisiTretman(Tretman t) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(t);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_OBRISI_TRETMAN);
        posaljiPrimi(klijentZahtev);

    }

    public Preparat vratiPreparatPoID(Preparat p) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(p);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VRATI_SVE_PREPARATE_PO_ID);
        preparat = (Preparat) posaljiPrimi(klijentZahtev);
        return preparat;
    }

    public void dodajRaspored(Raspored r) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(r);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_DODAJ_RASPORED);
        posaljiPrimi(klijentZahtev);
    }

    public void dodajKompaniju(Kompanija kompanija) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(kompanija);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_DODAJ_KOMPANIJU);
        posaljiPrimi(klijentZahtev);
    }
    
    public void dodajZaposlenog(Zaposleni zaposleni) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(zaposleni);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_DODAJ_ZAPOSLENOG);
        posaljiPrimi(klijentZahtev);
    }
    
    public void dodajPreparat(Preparat preparat) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(preparat);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_DODAJ_PREPARAT);
        posaljiPrimi(klijentZahtev);
    }

    public void dodajPreparateTretmana() throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(listaPreparataTretmana);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_DODAJ_PREPARATE_TRETMANA);
        posaljiPrimi(klijentZahtev);
    }
    public void izmeniTretman(Tretman t) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(t);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_IZMENI_TRETMAN);
        posaljiPrimi(klijentZahtev);
    }
        
}
