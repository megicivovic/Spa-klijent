/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.modeltabele;

import domen.GenerickiDomenskiObjekat;
import domen.Preparat;
import domen.TretmanPreparati;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author student1
 */
public class PModelTabele extends AbstractTableModel {

    List<GenerickiDomenskiObjekat> lp;

    public PModelTabele(List<GenerickiDomenskiObjekat> lp) {
        this.lp = lp;
    }

    @Override
    public int getRowCount() {
        return this.lp.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Preparat p = null;
        try {
            p = (Preparat) lp.get(rowIndex);
        } catch (Exception ex) {
            Logger.getLogger(PModelTabele.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (columnIndex) {
            case 0:
                return p.getPreparatID();
            case 1:
                return p.getNaziv();
            case 2:
                return p.getCena();
            case 3:
                return p.getProizvodjac().getNaziv();
            default:
                return "Greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Naziv";
            case 2:
                return "Cena";
            case 3:
                return "Proizvođač";
            default:
                return "Greska";
        }
    }

    public void obrisiRed(int red) {
        this.lp.remove(red);
        fireTableDataChanged();
    }

    public List<GenerickiDomenskiObjekat> vratiListu() {
        return this.lp;
    }

}
