package org.iesch.ad.dao;

import jakarta.persistence.*;
import org.iesch.ad.models.Producto;
import org.iesch.ad.models.Venta;

import java.util.List;

public class ProductoDao {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction transaction;

    public ProductoDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ventas");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    public void insert(Producto producto) {
        try {
            transaction.begin();
            entityManager.persist(producto);
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

    public void borrarProductoYSusVentas(Producto producto) {
        try {
            transaction.begin();
            Producto delete = entityManager.find(Producto.class, producto.getId());
            Query ventasDelete = entityManager.createQuery("select v from Venta v where id_producto = :borrarProducto")
                    .setParameter("borrarProducto", delete);

            List<Venta> lstVentas = ventasDelete.getResultList();
            for (Venta v :
                    lstVentas) {
                entityManager.remove(v);
            }
            entityManager.remove(delete);
            transaction.commit();
            System.out.println("Se ha borrado el producto con id: " + producto.getId() + " y sus ventas");
        } catch (Exception e) {
            System.out.println("No se ha borrado el producto con id: " + producto.getId() + " y sus ventas");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public List<Producto> buscarProductosPrecioInferior(float precio) {
        Query queryPrecio = entityManager.createQuery("select p from Producto p where p.precio > :precio").setParameter("precio", precio);
        return queryPrecio.getResultList();
    }

    public List<Producto> buscarProductoStockactualIgualStockminimo() {
        Query queryPrecio = entityManager.createQuery("select p from Producto p where p.stockminimo = p.stockactual");
        return queryPrecio.getResultList();
    }

    public List<Producto> buscaTodosPorductos() {
        Query qTodosP = entityManager.createQuery("select p from Producto p ");
        return qTodosP.getResultList();
    }

    public void updateImpuestos(float impuesto) {
        List<Producto> lstProducto = buscaTodosPorductos();
        for (Producto p : lstProducto
        ) {
            p.setPrecio(p.getPrecio() * (impuesto / 100 + 1));
            try {
                transaction.begin();
                entityManager.merge(p);
                transaction.commit();
                System.out.println("Update realizado con exito");
            } catch (Exception e) {
                System.out.println("Update fallido");
            } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
    }
}
