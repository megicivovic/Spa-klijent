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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    boolean povezanNaServer;
//    private LogInForm logInForm;
//    private SlucajeviKoriscenja slucajeviKoriscenja;
    private List<Zaposleni> listaZaposlenih;
    private List<Tretman> listaTretmana;
    private List<Raspored> listaRasporeda;
    private List<TretmanPreparati> listaPreparataTretmana;
    private Preparat preparat;
    private List<Preparat> listaPreparata;
    private List<Kompanija> listaKompanija;
    private List<Rezervacija> listaRezervacija;
    
    private static final String IPADDRESS_PATTERN
            = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    private Pattern pattern;
    private Matcher matcher;
    
    private Kontroler() {
    }

    public boolean isPovezanNaServer() {
        return povezanNaServer;
    }

    public void setPovezanNaServer(boolean povezanNaServer) {
        this.povezanNaServer = povezanNaServer;
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
    
    public List<Raspored> getListaRasporeda() {
        return listaRasporeda;
    }
    
    public void setListaRasporeda(List<Raspored> listaRasporeda) {
        this.listaRasporeda = listaRasporeda;
    }
    
    public List<Rezervacija> getListaRezervacija() {
        return listaRezervacija;
    }
    
    public void setListaRezervacija(List<Rezervacija> listaRezervacija) {
        this.listaRezervacija = listaRezervacija;
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
        Korisnik k = new Korisnik();
        k.setKorisnickoIme(korisnickoIme);
        klijentZahtev.setObjekat(k);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VALIDIRAJ_KORISNICKO_IME);
        return (boolean) posaljiPrimi(klijentZahtev);
    }
    
    public void validirajIPAdresu(String ip) throws Exception {
        pattern = Pattern.compile(IPADDRESS_PATTERN);
        matcher = pattern.matcher(ip);
        if (!matcher.matches()) {
            throw new Exception("Format IP adrese nije ispravan!");
        }
    }
    
    public int validirajBrojPorta(String brojPorta) throws Exception {
        try {
            return Integer.parseInt(brojPorta);
        } catch (Exception e) {
            throw new Exception("Neispravan broj porta!");
        }
        
    }
    
    public void validirajIme(String ime) throws Exception {
        if (ime == null || ime.isEmpty()) {
            throw new Exception("Morate uneti ime");
            
        }
        if ((int) ime.charAt(0) < 65 || (int) ime.charAt(0) > 90) {
            throw new Exception("Ime mora pocinjati velikim slovom!");
        }
        for (int i = 1; i < ime.length(); i++) {
            if ((int) ime.charAt(i) < 65 || (int) ime.charAt(i) > 122) {
                // throw new Exception("Ime mora sadrzati samo slova!");
            }
        }
        
    }
    
    public Date validirajVreme(String sVreme) throws Exception {
        try {
            return new SimpleDateFormat("HH:mm").parse(sVreme);
        } catch (ParseException ex) {
            throw new Exception("Vreme rezervacije nije ispravno uneto!");
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
    
    public List<Rezervacija> vratiSveRezervacije() throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VRATI_SVE_REZERVACIJE);
        listaRezervacija = (List<Rezervacija>) posaljiPrimi(klijentZahtev);
        return listaRezervacija;
    }
    
    public List<Rezervacija> vratiSveRezervacijeDana(GregorianCalendar dan) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(dan);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VRATI_SVE_REZERVACIJE_DANA);
        listaRezervacija = (List<Rezervacija>) posaljiPrimi(klijentZahtev);
        return listaRezervacija;
    }

    public List<Rezervacija> vratiSveRezervacijeRasporeda(Raspored r) throws Exception {
        KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(r);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_VRATI_SVE_REZERVACIJE_RASPOREDA);
        listaRezervacija = (List<Rezervacija>) posaljiPrimi(klijentZahtev);
        return listaRezervacija;
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
    
    
    public void dodajKorisnika(Korisnik k) throws Exception {
       KlijentZahtev klijentZahtev = new KlijentZahtev();
        klijentZahtev.setObjekat(k);
        klijentZahtev.setProtokol(Protokol.OPERACIJA_DODAJ_KORISNIKA);
        posaljiPrimi(klijentZahtev);
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
