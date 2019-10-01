/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Cilindro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stephi
 */
@Stateless
public class CilindroFacade extends AbstractFacade<Cilindro> {

    @PersistenceContext(unitName = "proyectoOMCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CilindroFacade() {
        super(Cilindro.class);
    }
    public List<Object[]> calcularTotal() {
        Query query = em.createNativeQuery("SELECT\n" +
"     cilindro.`id_cilindro` AS cilindro_id_cilindro,\n" +
"     cilindro.`estado` AS cilindro_estado,\n" +
"     cilindro.`tamanio` AS cilindro_tamanio,\n" +
"     cilindro.`lote` AS cilindro_lote,\n" +
"     cilindro.`fecha_de_creacion` AS cilindro_fecha_de_creacion\n" +
"FROM\n" +
"     `cilindro` cilindro");
        //List<Object[]>  result=em.createQuery("SELECT p.idProducto,p.producto,(p.cantidad*p.precio) as Total  FROM  Producto p").getResultList();
        List<Object[]> result = query.getResultList();
        /*for(Object[] object : result) {
            System.out.println("Codigo: "+object[0]+ ", Nombre: "+object[1]+ ", Total: "+object[2]);
        }*/
        return result;
    }
}
