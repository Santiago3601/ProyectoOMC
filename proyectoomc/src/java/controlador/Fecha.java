package controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {

    private Date objDate;
    private String strDateFormato;
    private String strHoraFormato;
    private SimpleDateFormat soyLaFecha;
    private SimpleDateFormat soyLaHora;

    public Fecha() {
        this.objDate = new Date();
        this.strDateFormato = "dd-MM-aaaa";
        this.strHoraFormato = "hh:mm";
        this.soyLaFecha = new SimpleDateFormat(strDateFormato);
        this.soyLaHora = new SimpleDateFormat(strHoraFormato);
        
        System.out.println("Fecha"+soyLaFecha);
        System.out.println("Hora"+soyLaHora.toString());
        System.out.println("fecha 2    "+objDate.getHours()+":"+objDate.getMinutes());
    }

    public Date getObjDate() {
        return objDate;
    }

    public void setObjDate(Date objDate) {
        this.objDate = objDate;
    }

    public String getStrDateFormato() {
        return strDateFormato;
    }

    public void setStrDateFormato(String strDateFormato) {
        this.strDateFormato = strDateFormato;
    }

    public String getStrHoraFormato() {
        return strHoraFormato;
    }

    public void setStrHoraFormato(String strHoraFormato) {
        this.strHoraFormato = strHoraFormato;
    }

    public SimpleDateFormat getSoyLaFecha() {
        return soyLaFecha;
    }

    public void setSoyLaFecha(SimpleDateFormat soyLaFecha) {
        this.soyLaFecha = soyLaFecha;
    }

    public SimpleDateFormat getSoyLaHora() {
        return soyLaHora;
    }

    public void setSoyLaHora(SimpleDateFormat soyLaHora) {
        this.soyLaHora = soyLaHora;
    }
    
    
    
}
