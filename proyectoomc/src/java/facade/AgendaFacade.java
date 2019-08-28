/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Agenda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stephi
 */
@Stateless
public class AgendaFacade extends AbstractFacade<Agenda> {

    @PersistenceContext(unitName = "proyectoOMCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgendaFacade() {
        super(Agenda.class);
    }
    
        public String cargaArchivos(String archivo, String tabla) {
        String resultado = "";
        try {
            Query query = em.createNativeQuery("LOAD DATA LOCAL INFILE" + archivo + "REPLACE INTO TABLE" + tabla + "FIELDS TERMINATED BY ';' LINES TERMINATED BY '\\r\\n'");
            resultado = Integer.toString(query.executeUpdate());

            return resultado;
        } catch (Exception e) {

            System.out.println("Error: " + e);

        }
        return "resultado";
    }
    
    
}
