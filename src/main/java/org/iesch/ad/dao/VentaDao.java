package org.iesch.ad.dao;

import jakarta.persistence.*;
import org.iesch.ad.models.Producto;
import org.iesch.ad.models.Venta;

import java.util.List;

public class VentaDao {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction transaction;

    public VentaDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ventas");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    public void insert(Venta venta) {
        try {
            transaction.begin();
            entityManager.persist(venta);
            transaction.commit();
            System.out.println("\tSe ha insertado correctamente");
        } catch (Exception e) {
            System.out.println("\tNo se ha insertado correctamente");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void buscaVentaYSuClienteYProducto(int id) {
        Venta venta = entityManager.find(Venta.class, id);
        System.out.println(venta.toString());
    }


    public List<Venta> VentasRealizadasUltimosDias(int dias) {
        Query queryVenta = entityManager.createQuery("select v from Venta v where DATEDIFF(now(),v.fecha) <= :dias").setParameter("dias", dias);
        return queryVenta.getResultList();
    }

    public List<Venta> VentasOrdenadasPorIdClienteDeUnProducto(int idProducto) {
        Producto producto = entityManager.find(Producto.class, idProducto);
        Query queyVenta = entityManager.createQuery("select v from Venta v where v.id_producto = :idProducto order by v.id_cliente").setParameter("idProducto", producto);
        return queyVenta.getResultList();
    }
}
