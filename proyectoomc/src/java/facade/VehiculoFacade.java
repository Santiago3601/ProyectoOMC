/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Vehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stephi
 */
@Stateless
public class VehiculoFacade extends AbstractFacade<Vehiculo> {

    @PersistenceContext(unitName = "proyectoOMCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculoFacade() {
        super(Vehiculo.class);
    }
        public String cargaArchivos(String archivo, String tabla) {
        String resultado = "";
        try {
            Query query = em.createNativeQuery("LOAD DATA INFILE '" + archivo + "' IGNORE INTO TABLE '"+ tabla +"' FIELDS TERMINATED BY ',' ENCLOSED BY '\\\"' ESCAPED BY '\\\\' LINES TERMINATED BY '\\r\\n'");
            resultado = Integer.toString(query.executeUpdate());

            return resultado;
        } catch (Exception e) {

            System.out.println("Error: " + e);

        }
        return resultado;
        }
}  
