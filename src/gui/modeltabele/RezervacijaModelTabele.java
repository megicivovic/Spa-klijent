/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.modeltabele;

import domen.Rezervacija;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class RezervacijaModelTabele extends AbstractTableModel {

    List<Rezervacija> lr;

    public RezervacijaModelTabele(List<Rezervacija> lr) {
        this.lr = lr;

    }

    @Override
    public int getRowCount() {
        return this.lr.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija r = (Rezervacija) this.lr.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return r.getTretman();
            case 1:
                return r.getZaposleni();
            case 2:{
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                return sdf.format(r.getVreme());
            }
            case 3: {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date d = new Date(r.getVreme().getTime() + r.getTretman().getTrajanjeUMin() * 60 * 1000);

                return sdf.format(d);
            }

            default:
                return "Greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Tretman";
            case 1:
                return "Zaposleni";
            case 2:
                return "Vreme pocetka";
            case 3:
                return "Vreme zavrsetka";
            default:
                return "Greska";
        }
    }

    public void obrisiRed(int red) {
        this.lr.remove(red);
        fireTableDataChanged();
    }

    public List<Rezervacija> vratiListu() {
        return this.lr;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;

    }

}
